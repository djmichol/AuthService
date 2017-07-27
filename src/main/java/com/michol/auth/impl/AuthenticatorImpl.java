package com.michol.auth.impl;

import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import com.michol.dao.model.User;

public class AuthenticatorImpl implements Authenticator {

    private UserDao userDao;

    public AuthenticatorImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //TODO na razie mocki

    @Override
    public VerifyPasswordModel verifyPassword(String userName, String password) {
        User user = userDao.get(userName);
        VerifyPasswordModel verifyPasswordModel = new VerifyPasswordModel();
        verifyPasswordModel.setAuthenticated(true);
        verifyPasswordModel.setCanChangePassword(true);
        verifyPasswordModel.setUserLocked(false);
        return verifyPasswordModel;
    }

    @Override
    public boolean validateToken(String userName, String token) {
        return true;
    }

    @Override
    public String generateToken(String userName, String number) {
        return userName+number;
    }
}
