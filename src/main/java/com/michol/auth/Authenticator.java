package com.michol.auth;

import com.michol.api.model.response.VerifyPasswordModel;

public interface Authenticator {

    VerifyPasswordModel verifyPassword(String userName, String password);

    boolean validateToken(String userName, String token);

    String generateToken(String userName, String number);
}
