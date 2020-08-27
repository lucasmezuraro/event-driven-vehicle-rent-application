package br.com.rentacar.reserveserviceapplication.producers;

import br.com.rentacar.reserveserviceapplication.bindings.ReserveSource;
import br.com.rentacar.reserveserviceapplication.dtos.PmtInfo;
import br.com.rentacar.reserveserviceapplication.dtos.stream.NewReserveMessage;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(ReserveSource.class)
@AllArgsConstructor
public class ReserveProducer {

    @Autowired
    private ReserveSource source;

   @Publisher(channel = ReserveSource.NEW_RESERVE)
   @HystrixCommand(defaultFallback = "fallbackSendPreparationStartMessage")
    public void sendMessage(Reserve reserve, Car car, PmtInfo pmtInfo) {
       NewReserveMessage reserveCreateMessageDTO = new NewReserveMessage(reserve, car, pmtInfo);
        Message<NewReserveMessage> reserveCreateMessageDTOMessage = MessageBuilder.withPayload(reserveCreateMessageDTO).build();
        source.sendMessage().send(reserveCreateMessageDTOMessage);
    }

    public void fallbackSendPreparationStartMessage() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Fallback is dispatched");
    }
}
