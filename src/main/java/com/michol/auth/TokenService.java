package com.michol.auth;

import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.dao.model.User;

import java.io.UnsupportedEncodingException;

public interface TokenService {

    VerifyUserResponseModel validateToken(String token, String userName);

    String generateToken(User user);

}
