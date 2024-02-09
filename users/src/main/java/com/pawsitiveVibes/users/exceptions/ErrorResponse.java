package com.pawsitiveVibes.users.exceptions;

import java.util.Date;

public class ErrorResponse {
    private int statusCode;
    private String message;
    private Date date;

    public ErrorResponse(int statusCode, String message, Date date) {
        this.statusCode = statusCode;
        this.message = message;
        this.date = date;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }
}
