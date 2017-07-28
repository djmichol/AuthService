package com.michol.api.usecase.impl;

import com.michol.api.model.request.ChangePasswordModel;
import com.michol.api.model.response.ChangePasswordResponseModel;
import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.api.usecase.RequestHandler;
import com.michol.auth.Authenticator;
import com.michol.auth.HashService;
import com.michol.dao.UserDao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ChangePasswordHandler implements RequestHandler<ChangePasswordModel, ChangePasswordResponseModel> {

    private Authenticator authenticator;
    private UserDao userDao;
    private HashService hashService;

    public ChangePasswordHandler(Authenticator authenticator, UserDao userDao, HashService hashService) {
        this.authenticator = authenticator;
        this.userDao = userDao;
        this.hashService = hashService;
    }

    @Override
    public ChangePasswordResponseModel handle(ChangePasswordModel changePasswordModel) {
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(changePasswordModel.getLogin(), changePasswordModel.getOldPassword());
        if (verifyPasswordModel.isAuthenticated()) {
            try {
                userDao.changePassword(changePasswordModel.getLogin(), hashService.generateStrongPasswordHash(changePasswordModel.getNewPassword()));
            } catch (Exception e) {
                return ChangePasswordResponseModel.notChanged();
            }
            return ChangePasswordResponseModel.changed();
        } else {
            return ChangePasswordResponseModel.notChanged();
        }
    }
}
