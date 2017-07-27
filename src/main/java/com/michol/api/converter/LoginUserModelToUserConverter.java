package com.michol.api.converter;

import com.michol.api.model.request.CreateUserModel;
import com.michol.auth.Authenticator;
import com.michol.dao.model.User;
import com.michol.utils.Utils;
import org.springframework.core.convert.converter.Converter;

public class LoginUserModelToUserConverter implements Converter<CreateUserModel, User> {

    private Authenticator authenticator;

    public LoginUserModelToUserConverter(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    @Override
    public User convert(CreateUserModel createUserModel) {
        if(createUserModel!=null){
            User user = new User();
            user.setLogin(createUserModel.getLogin());
            user.setPassword(createUserModel.getPassword());
            user.setToken(authenticator.generateToken(createUserModel.getLogin(), Utils.nextSessionId()));
            user.setBlocked(false);
            user.setEmail(createUserModel.getEmail());
            return user;
        }
        return null;
    }
}
