package com.balobanov.validators;

import com.balobanov.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User>{

    @Override
    public boolean validate(User user) {
        return false;
    }
}
