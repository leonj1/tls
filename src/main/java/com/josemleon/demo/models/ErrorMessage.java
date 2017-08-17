package com.josemleon.demo.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created for K and M Consulting LLC.
 * Created by Jose M Leon 2017
 **/
public class ErrorMessage {
    @SerializedName("err_msg")
    private String errorMessage;

    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
