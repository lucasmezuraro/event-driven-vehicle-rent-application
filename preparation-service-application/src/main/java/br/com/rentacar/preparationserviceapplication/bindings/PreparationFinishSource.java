package br.com.rentacar.preparationserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PreparationFinishSource {

    public String PREPARATION_FINISH_CHANNEL = "preparation-finish-channel";

    @Output(PREPARATION_FINISH_CHANNEL)
    MessageChannel sendMessage();
}
