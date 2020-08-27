package br.com.rentacar.paymentserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PaymentSource {

    public String RESERVE_PAID = "payment-channel";

    @Output(RESERVE_PAID)
    MessageChannel sendMessage();
}
