package com.michol.api.usecase.impl;

import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.api.model.request.CreateUserModel;
import com.michol.api.model.response.SignResponseModel;
import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.api.usecase.RequestHandler;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import com.michol.dao.model.User;

public class SignHandler implements RequestHandler<CreateUserModel, SignResponseModel> {

    private UserDao userDao;
    private LoginUserModelToUserConverter loginUserModelToUserConverter;
    private Authenticator authenticator;

    public SignHandler(UserDao userDao, LoginUserModelToUserConverter loginUserModelToUserConverter, Authenticator authenticator) {
        this.userDao = userDao;
        this.loginUserModelToUserConverter = loginUserModelToUserConverter;
        this.authenticator = authenticator;
    }

    @Override
    public SignResponseModel handle(CreateUserModel createUserModel) {
        User createdUser = userDao.create(loginUserModelToUserConverter.convert(createUserModel));
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(createdUser.getLogin(), createdUser.getPassword());
        SignResponseModel signResponseModel = new SignResponseModel(createdUser.getToken(), verifyPasswordModel, true);
        return signResponseModel;
    }
}
