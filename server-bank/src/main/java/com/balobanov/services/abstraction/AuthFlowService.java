package com.balobanov.services.abstraction;

import com.balobanov.exceptions.ApplicationException;
import com.balobanov.exceptions.EmailDoesNotExists;
import com.balobanov.models.PasswordRecovery;

import javax.mail.MessagingException;
import java.util.Map;

public interface AuthFlowService extends BaseService<PasswordRecovery>{
    PasswordRecovery getByEmail(String email);

    void newPasswordRequest(String email) throws EmailDoesNotExists, MessagingException;
    void newPasswordRestore(String code, String email) throws ApplicationException, MessagingException;
    void registration(Map<String, ?> params);
}
