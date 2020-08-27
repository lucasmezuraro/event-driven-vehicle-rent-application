package br.com.rentacar.paymentserviceapplication.dtos;

import br.com.rentacar.paymentserviceapplication.types.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcessPaymentGateway {
    Boolean approved;
    String message;
    PaymentStatus paymentStatus;
}
