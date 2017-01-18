package com.rushabh.chatapp.dataObject;

/**
 * Created by Rushabh on 1/18/2017.
 */

public class message {

    private String message, name;

    public message(String message, String name) {
        this.message = message;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
