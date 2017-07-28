package com.michol.api;

import com.michol.api.model.request.ChangePasswordModel;
import com.michol.api.model.request.CreateUserModel;
import com.michol.api.model.request.LoginUserModel;
import com.michol.api.model.response.ChangePasswordResponseModel;
import com.michol.api.model.response.LoginResponseModel;
import com.michol.api.model.response.SignResponseModel;
import com.michol.api.usecase.HandlerExecutor;
import com.michol.api.usecase.impl.ChangePasswordHandler;
import com.michol.api.usecase.impl.LoginHandler;
import com.michol.api.usecase.impl.SignHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(LoginServiceHttp.PATH)
public class LoginServiceHttp {

    public static final String PATH = "login";

    private HandlerExecutor handlerExecutor;

    public LoginServiceHttp(HandlerExecutor handlerExecutor) {
        this.handlerExecutor = handlerExecutor;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseModel> login(@RequestBody @Valid LoginUserModel loginUserModel) {
        LoginResponseModel loginResponseModel = handlerExecutor.execute(LoginHandler.class, loginUserModel);
        if (loginResponseModel.getVerifyPasswordModel().isAuthenticated()) {
            return new ResponseEntity<>(loginResponseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(loginResponseModel, HttpStatus.UNAUTHORIZED);
        }
    }

    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public ResponseEntity<SignResponseModel> sign(@RequestBody @Valid CreateUserModel createUserModel) {
        SignResponseModel signResponseModel = handlerExecutor.execute(SignHandler.class, createUserModel);
        return new ResponseEntity<>(signResponseModel, HttpStatus.CREATED);
    }

    @RequestMapping(value = "password", method = RequestMethod.PATCH)
    public ResponseEntity<ChangePasswordResponseModel> changePassword(@RequestBody @Valid ChangePasswordModel changePasswordModel) {
        ChangePasswordResponseModel changePasswordResponseModel = handlerExecutor.execute(ChangePasswordHandler.class, changePasswordModel);
        if (changePasswordResponseModel.isChanged()) {
            return new ResponseEntity<>(ChangePasswordResponseModel.changed(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ChangePasswordResponseModel.notChanged(), HttpStatus.UNAUTHORIZED);
        }
    }
}
