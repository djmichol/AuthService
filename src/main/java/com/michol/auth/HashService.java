package com.michol.auth;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface HashService {

    boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException;

    String generateStrongPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException;

}
