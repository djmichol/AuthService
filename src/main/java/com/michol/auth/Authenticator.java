package com.michol.auth;

import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.api.model.response.VerifyUserResponseModel;

public interface Authenticator {

    VerifyPasswordModel verifyPassword(String userName, String password);

    VerifyUserResponseModel validateToken(String userName, String token);

    String generateToken(String userName);
}
