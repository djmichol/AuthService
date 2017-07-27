package com.michol.api;

import com.michol.api.model.request.LoginUserModel;
import com.michol.api.model.response.LoginResponseModel;
import com.michol.api.model.response.SignResponseModel;
import com.michol.api.model.response.VerifyPasswordModel;
import com.michol.auth.Authenticator;
import com.michol.dao.UserDao;
import com.michol.dao.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.SecureRandom;


@RestController
@RequestMapping(LoginServiceHttp.PATH)
public class LoginServiceHttp {

    public static final String PATH = "login";

    private Authenticator authenticator;
    private UserDao userDao;
    private SecureRandom random = new SecureRandom();

    public LoginServiceHttp(Authenticator authenticator, UserDao userDao) {
        this.authenticator = authenticator;
        this.userDao = userDao;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseModel> login(@RequestBody LoginUserModel loginUserModel) {
        VerifyPasswordModel verifyPasswordModel = authenticator.verifyPassword(loginUserModel.getUserName(), loginUserModel.getPassword());
        if(verifyPasswordModel.isAuthenticated()){
            String token = authenticator.generateToken(loginUserModel.getUserName(), nextSessionId());
            LoginResponseModel loginResponseModel = new LoginResponseModel(token, verifyPasswordModel);
            return new ResponseEntity<>(loginResponseModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "sign", method = RequestMethod.POST)
    public ResponseEntity<SignResponseModel> sign(@RequestBody LoginUserModel loginUserModel) {
        String token = authenticator.generateToken(loginUserModel.getUserName(), nextSessionId());
        User user = new User();
        user.setLogin(loginUserModel.getUserName());
        user.setPassword(loginUserModel.getPassword());
        user.setToken(token);
        userDao.create(user);
        SignResponseModel signResponseModel = new SignResponseModel(token, null, true);
        return new ResponseEntity<>(signResponseModel, HttpStatus.UNAUTHORIZED);
    }

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
