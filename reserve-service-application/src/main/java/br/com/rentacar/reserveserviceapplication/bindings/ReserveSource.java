package br.com.rentacar.reserveserviceapplication.bindings;

import org.springframework.amqp.core.Message;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface ReserveSource {

    public String NEW_RESERVE = "reserve-channel";

    /*@Input(NEW_RESERVE)
    public MessageChannel readMessage();*/

    @Output(NEW_RESERVE)
    public MessageChannel sendMessage();
}
