package com.michol.api;

import com.michol.api.converter.LoginUserModelToUserConverter;
import com.michol.api.model.request.CreateUserModel;
import com.michol.api.model.request.LoginUserModel;
import com.michol.api.model.response.LoginResponseModel;
import com.michol.api.model.response.SignResponseModel;
import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import com.michol.dao.model.User;
import com.michol.utils.Utils;
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

    private Authenticator authenticator;
    private UserDao userDao;
    private LoginUserModelToUserConverter loginUserModelToUserConverter;

    public LoginServiceHttp(Authenticator authenticator, UserDao userDao, LoginUserModelToUserConverter loginUserModelToUserConverter) {
        this.authenticator = authenticator;
        this.userDao = userDao;
        this.loginUserModelToUserConverter = loginUserModelToUserConverter;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseModel> login(@RequestBody @Valid LoginUserModel loginUserModel) {
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(loginUserModel.getUserName(), loginUserModel.getPassword());
        if(verifyPasswordModel.isAuthenticated()){
            String token = authenticator.generateToken(loginUserModel.getUserName(), Utils.nextSessionId());
            LoginResponseModel loginResponseModel = new LoginResponseModel(token, verifyPasswordModel);
            return new ResponseEntity<>(loginResponseModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public ResponseEntity<SignResponseModel> sign(@RequestBody @Valid CreateUserModel createUserModel) {
        User createdUser = userDao.create(loginUserModelToUserConverter.convert(createUserModel));
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(createdUser.getLogin(), createdUser.getPassword());
        SignResponseModel signResponseModel = new SignResponseModel(createdUser.getToken(), verifyPasswordModel, true);
        return new ResponseEntity<>(signResponseModel, HttpStatus.CREATED);
    }
}
