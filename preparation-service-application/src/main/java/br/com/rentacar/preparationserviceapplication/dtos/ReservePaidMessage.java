package br.com.rentacar.preparationserviceapplication.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ReservePaidMessage {
    UUID reserveTransactionId;
    UUID paymentTransactionId;
    Boolean processed;
    String errorReason;
    Long carId;
}

