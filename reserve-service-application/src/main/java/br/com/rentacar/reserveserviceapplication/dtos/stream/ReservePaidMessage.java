package br.com.rentacar.reserveserviceapplication.dtos.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ReservePaidMessage {
    UUID reserveTransactionId;
    UUID paymentTransactionId;
    Boolean processed;
    String errorReason;
}
