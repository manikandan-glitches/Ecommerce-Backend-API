package com.example.ecommerceapi.customException;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class responceException {
    private String Error;
    private LocalDateTime Timestamp;
    private int status;
    private String message;

}
