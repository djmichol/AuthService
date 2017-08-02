package com.michol.auth.impl;

import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.api.model.response.VerifyUserResponseModel;
import com.michol.auth.Authenticator;
import com.michol.auth.HashService;
import com.michol.auth.TokenService;
import com.michol.dao.UserDao;
import com.michol.dao.model.User;

public class AuthenticatorImpl implements Authenticator {

    private UserDao userDao;
    private HashService hashService;
    private TokenService tokenService;

    public AuthenticatorImpl(UserDao userDao, HashService hashService, TokenService tokenService) {
        this.userDao = userDao;
        this.hashService = hashService;
        this.tokenService = tokenService;
    }

    @Override
    public VerifyPasswordModel verifyPassword(String userName, String password) {
        User user = userDao.get(userName);
        try {
            if(hashService.validatePassword(password, user.getPassword())){
                VerifyPasswordModel verifyPasswordModel = new VerifyPasswordModel();
                verifyPasswordModel.setAuthenticated(true);
                verifyPasswordModel.setCanChangePassword(true);
                verifyPasswordModel.setUserLocked(false);
                return verifyPasswordModel;
            }
        } catch (Exception e) {
            //DO NOTHING
            //RETURN UNAUTHORIZED
        }
        return VerifyPasswordModel.unauthorized();
    }

    @Override
    public VerifyUserResponseModel validateToken(String userName, String token) {
        String userToken = userDao.getUserToken(userName);
        if(userToken.equals(token)){
            return tokenService.validateToken(userToken, userName);
        }
        return VerifyUserResponseModel.notVerified();
    }

    @Override
    public String generateToken(String userName) {
        User user = userDao.get(userName);
        if(user!=null) {
            String token = tokenService.generateToken(user);
            userDao.updateToken(userName, token);
            return token;
        }
        return null;
    }
}
