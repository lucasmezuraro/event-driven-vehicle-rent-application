package br.com.rentacar.preparationserviceapplication.producers;

import br.com.rentacar.preparationserviceapplication.bindings.PreparationFinishSource;
import br.com.rentacar.preparationserviceapplication.bindings.PreparationSource;
import br.com.rentacar.preparationserviceapplication.dtos.PreparationMessageDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(PreparationSource.class)
public class PreparationProducer {

    private static final Log log = LogFactory.getLog(PreparationProducer.class);

    @Autowired
    PreparationSource preparationSource;

    @Publisher(channel = PreparationSource.PREPARATION_CHANNEL)
    public void sendMessage(PreparationMessageDTO preparationMessageDTO) {
        log.info("sending a message to reserve service");
        System.out.println(preparationMessageDTO.getReserveTransactionId());
        Message<PreparationMessageDTO> preparationMessageDTOMessage = MessageBuilder.withPayload(preparationMessageDTO).build();
        preparationSource.sendMessage().send(preparationMessageDTOMessage);
    }
}
