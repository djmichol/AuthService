package com.michol.dao;

import com.michol.dao.model.User;

public interface UserDao {

    User get(String login);

    User create(User user);

    String getUserToken(String login);

    void changePassword(String login, String newPassword);

    void updateToken(String login, String token);

}
