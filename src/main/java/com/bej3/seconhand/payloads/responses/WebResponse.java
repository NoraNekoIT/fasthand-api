package com.bej3.seconhand.payloads.responses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WebResponse <M,T> {
    int code;

    String status;

    M message;

    T data ;
}