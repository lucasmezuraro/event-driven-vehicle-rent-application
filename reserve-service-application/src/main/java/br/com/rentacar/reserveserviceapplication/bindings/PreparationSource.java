package br.com.rentacar.reserveserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PreparationSource {

    public String PREPARATION_CHANNEL = "preparation-channel";

    @Input(PREPARATION_CHANNEL)
    MessageChannel readMessage();
}
