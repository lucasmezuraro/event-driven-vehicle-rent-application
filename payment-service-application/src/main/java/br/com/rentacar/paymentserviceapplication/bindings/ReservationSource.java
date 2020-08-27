package br.com.rentacar.paymentserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface ReservationSource {

    public String NEW_RESERVE = "reserve-channel";

    @Input(NEW_RESERVE)
    public MessageChannel readMessage();

}
