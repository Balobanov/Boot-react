package com.balobanov.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordRecoveryUtils {

    public static String generateCode(){
        return RandomStringUtils.random(12, true, true);
    }
}
