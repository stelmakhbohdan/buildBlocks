package com.simplerest.buildblocks.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
@Data
@AllArgsConstructor
public class CustomErrorDetails {
    private Date timestamp;
    private String message;
    private String errorMessage;
}
