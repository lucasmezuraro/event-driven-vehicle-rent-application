package br.com.rentacar.reserveserviceapplication.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class ExceptionTreatment {

    @NonNull
    private String message;
    private Date timestamp = new Date();
    @NonNull
    private int statusCode;
}
