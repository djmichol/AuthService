package com.michol.api.usecase.impl;

import com.michol.api.model.request.LoginUserModel;
import com.michol.api.model.response.LoginResponseModel;
import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.api.usecase.RequestHandler;
import com.michol.auth.Authenticator;

public class LoginHandler implements RequestHandler<LoginUserModel, LoginResponseModel> {

    private Authenticator authenticator;

    public LoginHandler(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public LoginResponseModel handle(LoginUserModel loginUserModel) {
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(loginUserModel.getUserName(), loginUserModel.getPassword());
        LoginResponseModel loginResponseModel = new LoginResponseModel(verifyPasswordModel);
        if (verifyPasswordModel.isAuthenticated()) {
            String token = authenticator.generateToken(loginUserModel.getUserName());
            loginResponseModel.setToken(token);
            return loginResponseModel;
        }
        return loginResponseModel;
    }
}
