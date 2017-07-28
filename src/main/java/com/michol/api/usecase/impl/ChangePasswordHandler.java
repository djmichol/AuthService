package com.michol.api.usecase.impl;

import com.michol.api.model.request.ChangePasswordModel;
import com.michol.api.model.response.ChangePasswordResponseModel;
import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.api.usecase.RequestHandler;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;

public class ChangePasswordHandler implements RequestHandler<ChangePasswordModel, ChangePasswordResponseModel> {

    private Authenticator authenticator;
    private UserDao userDao;

    public ChangePasswordHandler(Authenticator authenticator, UserDao userDao) {
        this.authenticator = authenticator;
        this.userDao = userDao;
    }

    @Override
    public ChangePasswordResponseModel handle(ChangePasswordModel changePasswordModel) {
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(changePasswordModel.getLogin(), changePasswordModel.getOldPassword());
        if (verifyPasswordModel.isAuthenticated()) {
            userDao.changePassword(changePasswordModel.getLogin(), changePasswordModel.getNewPassword());
            return ChangePasswordResponseModel.changed();
        } else {
            return ChangePasswordResponseModel.notChanged();
        }
    }
}
