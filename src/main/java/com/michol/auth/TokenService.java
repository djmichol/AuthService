package com.michol.auth;

import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.dao.model.User;

public interface TokenService {

    VerifyUserResponseModel validateToken(String token);

    String generateToken(User user);

}
