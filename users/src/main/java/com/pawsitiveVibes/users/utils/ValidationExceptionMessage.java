package com.pawsitiveVibes.users.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationExceptionMessage {
    public ValidationExceptionMessage() {
    }
    public String makeMessage(String exceptionMessage){
        String[] validationsFailed = exceptionMessage.split("interpolatedMessage=");
        List<String> fields = new ArrayList<>();
        List<String> messages = new ArrayList<>();
        int i;
        for(i = 1; i < validationsFailed.length; i++){
            messages.add(validationsFailed[i].split(",")[0]);
            fields.add((validationsFailed[i].split("propertyPath=")[1]).split(",")[0]);
        }
        StringBuilder errorMessage = new StringBuilder();
        for(i = 0; i < fields.size(); i++){
            errorMessage.append("El campo [ ").append(fields.get(i)).append(" ] no es válido, ").append(messages.get(i)).append(". ");
        }
        return errorMessage.toString();
    }
}
