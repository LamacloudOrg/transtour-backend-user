package com.transtour.backend.user.util;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtil {

    private static final String VALID_PW_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+{}[]|:;<>?,./";
    private static final int DEFAULT_PASSWORD_LENGTH = 6;
    private static final Random RANDOM = new SecureRandom();


    public static String radomGenerator(){
        String pw = "";
        for (int i=0; i<DEFAULT_PASSWORD_LENGTH; i++) {
            int index = (int)(RANDOM.nextDouble()*VALID_PW_CHARS.length());
            pw += VALID_PW_CHARS.substring(index, index+1);
        }
        return pw;
    }
}
