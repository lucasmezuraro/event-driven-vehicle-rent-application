package br.com.rentacar.reserveserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PreparationFinishSource {

    public String PREPARATION_FINISH_CHANNEL = "preparation-finish-channel";

    @Input(PREPARATION_FINISH_CHANNEL)
    MessageChannel readMessage();
}
