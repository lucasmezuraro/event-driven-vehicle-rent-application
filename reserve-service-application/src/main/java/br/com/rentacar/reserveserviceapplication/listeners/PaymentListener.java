package br.com.rentacar.reserveserviceapplication.listeners;

import br.com.rentacar.reserveserviceapplication.services.ReserveService;
import br.com.rentacar.reserveserviceapplication.bindings.PaymentSource;
import br.com.rentacar.reserveserviceapplication.dtos.stream.ReservePaidMessage;
import br.com.rentacar.reserveserviceapplication.types.ReserveStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(PaymentSource.class)
@AllArgsConstructor
public class PaymentListener {

    private static final Log log = LogFactory.getLog(PaymentListener.class);

    @Autowired
    ReserveService reserveService;

    @StreamListener(PaymentSource.RESERVE_PAID)
    @HystrixCommand(defaultFallback = "fallbackSendMessage")
    public void readMessage(ReservePaidMessage reservePaidMessage) {
        log.info("reading a message from payment service");
        ReserveStatus reserveStatus;
        if (reservePaidMessage.getProcessed()) {
            reserveStatus = ReserveStatus.PAID;
        }else {
            reserveStatus = ReserveStatus.REJECTED;
        }
        reserveService.updateReserveStatus(reservePaidMessage.getReserveTransactionId(), reserveStatus);
    }

    public void fallbackSendMessage() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Fallback is dispatched");
    }
}
