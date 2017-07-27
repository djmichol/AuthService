package com.michol.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Utils {

    private static final SecureRandom random = new SecureRandom();

    public static String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

}
