package com.bej3.seconhand.controllers;

import com.bej3.seconhand.errors.NotFoundException;
import com.bej3.seconhand.payloads.responses.WebResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(value ={NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    WebResponse<String> notFoundError(){
        return new WebResponse<>(
                HttpStatus.NOT_FOUND.value(),
                "NOT FOUND",
                ""
        );
    }

    @ExceptionHandler(value ={Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    WebResponse<String> internalServerError(Exception e){
        return new WebResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "INTERNAL SERVER ERROR",
                e.getMessage()
        );
    }

//    @ExceptionHandler(value ={Exception.class})
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    WebResponse<String> unauthorizedError(){
//        return new WebResponse<>(
//                HttpStatus.UNAUTHORIZED.value(),
//                "UNAUTHORIZED",
//                ""
//        );
//    }

//    @ExceptionHandler(value ={UnknownException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    WebResponse<String> badRequestError(){
//        return new WebResponse<>(
//                HttpStatus.BAD_REQUEST.value(),
//                "BAD REQUEST",
//                ""
//        );
//    }

}