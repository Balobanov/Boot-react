package com.balobanov.services;

import com.balobanov.exceptions.ApplicationException;
import com.balobanov.exceptions.EmailDoesNotExists;
import com.balobanov.models.PasswordRecovery;
import com.balobanov.models.User;
import com.balobanov.repositories.AuthFlowRepository;
import com.balobanov.services.abstraction.AuthFlowService;
import com.balobanov.services.abstraction.RoleService;
import com.balobanov.services.abstraction.UserService;
import com.balobanov.utils.PasswordRecoveryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthFlowServiceImpl extends AbstractBaseService<PasswordRecovery, Long, AuthFlowRepository> implements AuthFlowService {

    private JavaMailSender emailSender;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private RoleService roleService;

    @Override
    public PasswordRecovery getByEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public void newPasswordRequest(String email) throws EmailDoesNotExists, MessagingException {
        UserDetails byEmail = userService.loadUserByUsername(email);

        if (byEmail == null) {
            throw new EmailDoesNotExists();
        }

        //Save to DB
        String code = PasswordRecoveryUtils.generateCode();
        PasswordRecovery passwordRecovery;

        passwordRecovery = dao.findByEmail(email);
        if (passwordRecovery == null) {
            passwordRecovery = new PasswordRecovery();
            passwordRecovery.setEmail(email);
        }

        passwordRecovery.setLocalDateTime(LocalDateTime.now());
        passwordRecovery.setApproveCode(code);
        dao.save(passwordRecovery);

        //Send email
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setText(String.format("Hi! Here your restore code: %s", code));
        helper.setSubject("Password recovery!");
        emailSender.send(message);
    }

    @Override
    public void newPasswordRestore(String code, String email) throws ApplicationException, MessagingException {
        PasswordRecovery byEmail = dao.findByEmail(email);

        if (byEmail == null) {
            throw new EmailDoesNotExists();
        }

        if (!byEmail.getApproveCode().equals(code)) {
            throw new ApplicationException();
        }

        User userDetails = (User) userService.loadUserByUsername(email);
        String password = PasswordRecoveryUtils.generateCode();
        userDetails.setPassword(passwordEncoder.encode(password));
        userService.update(userDetails);

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setText(String.format("Hi! Here your new password: %s", password));
        helper.setSubject("Password restored!");
        emailSender.send(message);
    }

    @Override
    public void registration(Map<String, ?> params) {
        String firstName = (String) params.get("firstName");
        String lastName = (String) params.get("lastName");
        String password = (String) params.get("password");
        String email = (String) params.get("email");

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);

        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        newUser.setRoles(roleService.getByRoles("ROLE_USER"));

        userService.save(newUser);
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
}
