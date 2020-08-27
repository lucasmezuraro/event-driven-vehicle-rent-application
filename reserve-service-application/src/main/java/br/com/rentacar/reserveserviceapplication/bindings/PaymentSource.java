package br.com.rentacar.reserveserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface PaymentSource {

    public String RESERVE_PAID = "payment-channel";

    @Input(RESERVE_PAID)
    MessageChannel readMessage();
}
