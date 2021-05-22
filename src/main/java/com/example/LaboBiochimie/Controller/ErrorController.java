package com.example.LaboBiochimie.Controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.LaboBiochimie.exception.ApplicationTokenExpired;
import com.example.LaboBiochimie.exception.UserException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {
    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<String> userExist(UserException ex){
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = { ApplicationTokenExpired.class})
    public ResponseEntity<String> tokenExpiredExecption(ApplicationTokenExpired ex){
        return new ResponseEntity<String>("Le token est expiré", HttpStatus.FORBIDDEN);
    }

}
