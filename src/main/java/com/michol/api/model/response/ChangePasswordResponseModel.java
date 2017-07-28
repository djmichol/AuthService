package com.michol.api.model.response;

import java.io.Serializable;

public class ChangePasswordResponseModel implements Serializable{

    private boolean changed;

    public ChangePasswordResponseModel() {
    }

    public ChangePasswordResponseModel(boolean changed) {
        this.changed = changed;
    }

    public static ChangePasswordResponseModel changed(){
        return new ChangePasswordResponseModel(true);
    }

    public static ChangePasswordResponseModel notChanged(){
        return new ChangePasswordResponseModel(false);
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
