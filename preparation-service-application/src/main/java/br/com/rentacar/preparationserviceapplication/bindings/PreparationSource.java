package br.com.rentacar.preparationserviceapplication.bindings;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PreparationSource {

    public String PREPARATION_CHANNEL = "preparation-channel";

    @Output(PREPARATION_CHANNEL)
    MessageChannel sendMessage();
}
