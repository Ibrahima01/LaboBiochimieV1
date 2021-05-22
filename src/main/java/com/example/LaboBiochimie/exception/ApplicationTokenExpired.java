package com.example.LaboBiochimie.exception;

public class ApplicationTokenExpired extends RuntimeException {
    public ApplicationTokenExpired(String message){
        super(message);
    }
}
