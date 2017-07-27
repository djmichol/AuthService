package com.michol.api.model.response;

public class SignResponseModel extends LoginResponseModel{

    private boolean userCreated;

    public SignResponseModel(String token, VerifyPasswordModel verifyPasswordModel, boolean userCreated) {
        super(token, verifyPasswordModel);
        this.userCreated = userCreated;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }
}
