package br.com.rentacar.preparationserviceapplication.listeners;

import br.com.rentacar.preparationserviceapplication.bindings.PaymentSource;
import br.com.rentacar.preparationserviceapplication.dtos.PreparationMessageDTO;
import br.com.rentacar.preparationserviceapplication.dtos.ReservePaidMessage;
import br.com.rentacar.preparationserviceapplication.entities.CarPreparation;
import br.com.rentacar.preparationserviceapplication.producers.PreparationFinishProducer;
import br.com.rentacar.preparationserviceapplication.producers.PreparationProducer;
import br.com.rentacar.preparationserviceapplication.types.PreparationPhrase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(PaymentSource.class)
public class PaymentListener {

    private static final Log log = LogFactory.getLog(PaymentListener.class);

    @Autowired
    PreparationProducer preparationProducer;

    @Autowired
    PreparationFinishProducer preparationFinishProducer;

    @StreamListener(PaymentSource.PAYMENT_SUCCESS)
    public void readMessage(ReservePaidMessage reservePaidMessage) {
        log.info("reading message from reserve service");
        if (reservePaidMessage.getProcessed()) {
            CarPreparation carPreparation = new CarPreparation("Preparando o carro",reservePaidMessage.getCarId(), reservePaidMessage.getReserveTransactionId());
            PreparationMessageDTO preparationMessageDTO = new PreparationMessageDTO(carPreparation.getPreparationId(), reservePaidMessage.getReserveTransactionId(), carPreparation.getPreparationPhrase(), reservePaidMessage.getCarId());
            preparationProducer.sendMessage(preparationMessageDTO);

            try {
                Thread.sleep(60000);
                System.out.println("FINISHED PREPARATION");
                CarPreparation carPreparationFinish = new CarPreparation("Preparando o carro", reservePaidMessage.getCarId(), reservePaidMessage.getReserveTransactionId());
                carPreparationFinish.setPreparationPhrase(PreparationPhrase.FINISHED);
                PreparationMessageDTO preparationMessageDTOFinish = new PreparationMessageDTO(carPreparation.getPreparationId(), reservePaidMessage.getReserveTransactionId(), carPreparation.getPreparationPhrase(), reservePaidMessage.getCarId());
                preparationFinishProducer.sendMessage(preparationMessageDTOFinish);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
