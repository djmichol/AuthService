package com.michol.auth.impl;

import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.auth.TokenService;
import com.michol.dao.model.User;

public class TokenServiceImpl implements TokenService{
    @Override
    public VerifyUserResponseModel validateToken(String token) {
        //TODO
        if(token.equals("token")) {
            return VerifyUserResponseModel.verified();
        }
        return VerifyUserResponseModel.notVerified();
    }

    @Override
    public String generateToken(User user) {
        //TODO
        return "token";
    }
}
