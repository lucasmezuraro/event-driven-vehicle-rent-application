package br.com.rentacar.preparationserviceapplication.producers;

import br.com.rentacar.preparationserviceapplication.bindings.PreparationFinishSource;
import br.com.rentacar.preparationserviceapplication.dtos.PreparationMessageDTO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(PreparationFinishSource.class)
public class PreparationFinishProducer {

    private static final Log log = LogFactory.getLog(PreparationFinishProducer.class);

    @Autowired
    PreparationFinishSource PreparationFinishSource;

    @Publisher(channel = br.com.rentacar.preparationserviceapplication.bindings.PreparationFinishSource.PREPARATION_FINISH_CHANNEL)
    public void sendMessage(PreparationMessageDTO preparationMessageDTO) {
        log.info("sending message for reserve service");
        System.out.println("SENDING THE MESSAGE:"+ preparationMessageDTO.getReserveTransactionId() + " car: "+ preparationMessageDTO.getCarId());
        Message<PreparationMessageDTO> preparationMessageDTOMessage = MessageBuilder.withPayload(preparationMessageDTO).build();
        PreparationFinishSource.sendMessage().send(preparationMessageDTOMessage);
    }
}
