package br.com.rentacar.preparationserviceapplication.bindings;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PaymentSource {

    public String PAYMENT_SUCCESS = "payment-channel";

    @Input(PAYMENT_SUCCESS)
    MessageChannel readMessage();
}
