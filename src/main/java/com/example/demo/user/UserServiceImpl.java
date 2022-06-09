package com.example.demo.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private JavaMailSender emailSenderService;

    private final UserRepository repository;

    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(JavaMailSender emailSenderService, UserRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.emailSenderService = emailSenderService;
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User addUser(User user) {
        if(!repository.findByUsername(user.getUsername()).isPresent()){

            user.setId(0);
            String encodedPassword = this.passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            return this.repository.save(user);
        }

        return user;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public User updateUser(User user) {
        return this.repository.save(user);
    }

    @Override
    public List<LoginUserDto> getUsers() {
        return repository.getAllUsers().stream().map(this::convertToDtoLogin).collect(Collectors.toList());
    }

    @Override
    public UserDto setScoreOfLoggedUser(long score) {
        User user = this.getCurrentUser();
        user.setScore(score);

        return convertToDto(repository.save(user));
    }

    @Override
    public void setSkill(int skill) {
        User user = this.getCurrentUser();
        user.setSkill(skill);

        convertToDto(repository.save(user));
    }

    @Override
    public Integer getSkill() {
        User user = this.getCurrentUser();

        return user.getSkill();
    }

    @Override
    public User verifyUser(String userName) {
        User user = repository.getUserByName(userName);
        user.setVerifyRegistration(true);
        return repository.save(user);
    }

    @Override
    public void changePassword(String password, String email) {
        User user = repository.getUserByEmail(email);
        user.setPassword(password);

        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repository.save(user);

    }

    @Override
    public void resendEmail(String email) throws MessagingException, UnsupportedEncodingException {
        String toAddress = email;
        String fromAddress = "pavolhodas4@gmail.com";
        String senderName = "Quiz";
        String subject = "Potvrdte registraciu";
        String content = "Prosím potvrďte registráciu:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Ďakujeme,<br>"
                + "Guiz.";

        MimeMessage message = emailSenderService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);


        String verifyURL = "http://localhost:4200" + "/verify";

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        emailSenderService.send(message);
    }

    @Override
    public void supportEmail(String name, String text) throws MessagingException, UnsupportedEncodingException {
        String toAddress = "martin.21.krause@gmail.com";
        String fromAddress = "pavolhodas4@gmail.com";
        String senderName = "Quiz";
        String subject = "Nová pripomienka";
        String content = "Užívaťeľ: [[name]], má pripomienku ku quiz applikácií:<br><br>"
                + "[[text]]<br>";

        MimeMessage message = emailSenderService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", name);

        content = content.replace("[[text]]", text);

        helper.setText(content, true);

        emailSenderService.send(message);
    }

//    @Override
//    public User register(User user) throws UserAlreadyExistException {
//        return null;
//    }

    private UserDto convertToDto(User user){
        UserDto userDto = modelMapper.map(user, UserDto.class);
        userDto.setName(user.getUsername());
        userDto.setScore(user.getScore());
        return userDto;
    }

    private LoginUserDto convertToDtoLogin(User user){
        LoginUserDto loginuserDto = modelMapper.map(user, LoginUserDto.class);
        loginuserDto.setName(user.getUsername());
        loginuserDto.setScore(user.getScore());
        return loginuserDto;
    }


    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        return this.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private void createAndPersistUser(String username, String password) {
        String encodedPassword = this.passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, 0);
        this.addUser(user);
    }
}
