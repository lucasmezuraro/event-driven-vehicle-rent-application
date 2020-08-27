package br.com.rentacar.paymentserviceapplication.listeners;

import br.com.rentacar.paymentserviceapplication.bindings.ReservationSource;
import br.com.rentacar.paymentserviceapplication.dtos.NewReserveMessage;
import br.com.rentacar.paymentserviceapplication.dtos.ProcessPaymentGateway;
import br.com.rentacar.paymentserviceapplication.entities.Payment;
import br.com.rentacar.paymentserviceapplication.producers.ReservePaymentProducer;
import br.com.rentacar.paymentserviceapplication.services.PaymentGateway;
import br.com.rentacar.paymentserviceapplication.types.PaymentType;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(ReservationSource.class)
public class NewReserveListener {

    private static final Log log = LogFactory.getLog(NewReserveListener.class);

    @Autowired
    private PaymentGateway paymentGateway;

    @Autowired
    private ReservePaymentProducer reservePaymentProducer;

    @StreamListener(ReservationSource.NEW_RESERVE)
    @HystrixCommand(defaultFallback = "fallbackReadReserveMessage")
    public void readMessage(NewReserveMessage newReserveMessage) throws Exception {
        log.info("Reading message from Reserve service");
        Payment payment = new Payment(newReserveMessage.getReserveTransactionId(), newReserveMessage.getLocatorId(), newReserveMessage.getCarId(), newReserveMessage.getAmountReserve(), PaymentType.CREDIT);
        Payment saved = paymentGateway.create(payment);
        ProcessPaymentGateway processPaymentGateway = paymentGateway.executePayment(payment);
        reservePaymentProducer.sendMessage(saved, processPaymentGateway);
    }

    public void fallbackReadReserveMessage() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("fallback is dispatched");
    }
}
