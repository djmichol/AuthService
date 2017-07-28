package com.michol.api.converter;

import com.michol.api.model.request.CreateUserModel;
import com.michol.auth.Authenticator;
import com.michol.auth.HashService;
import com.michol.dao.model.User;
import com.michol.utils.Utils;
import org.springframework.core.convert.converter.Converter;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginUserModelToUserConverter implements Converter<CreateUserModel, User> {

    private HashService hashService;

    public LoginUserModelToUserConverter(HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    public User convert(CreateUserModel createUserModel) {
        if(createUserModel!=null){
            User user = new User();
            user.setLogin(createUserModel.getLogin());
            try {
                user.setPassword(hashService.generateStrongPasswordHash(createUserModel.getPassword()));
            } catch (Exception e) {
               return null;
            }
            user.setBlocked(false);
            user.setEmail(createUserModel.getEmail());
            return user;
        }
        return null;
    }
}
