package br.com.rentacar.paymentserviceapplication.producers;

import br.com.rentacar.paymentserviceapplication.bindings.PaymentSource;
import br.com.rentacar.paymentserviceapplication.bindings.ReservationSource;
import br.com.rentacar.paymentserviceapplication.dtos.ProcessPaymentGateway;
import br.com.rentacar.paymentserviceapplication.dtos.ReservePaidMessage;
import br.com.rentacar.paymentserviceapplication.entities.Payment;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.Publisher;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(PaymentSource.class)
public class ReservePaymentProducer {

    private static final Log log = LogFactory.getLog(ReservePaymentProducer.class);

    @Autowired
    PaymentSource paymentSource;

    @Publisher(channel = PaymentSource.RESERVE_PAID)
    @HystrixCommand(defaultFallback = "fallbackProcessPayment")
    public void sendMessage(Payment payment, ProcessPaymentGateway processPaymentGateway) {
        log.info("Send message to payment service");
        ReservePaidMessage reservePaidMessage = new ReservePaidMessage(payment.getReserveTransactionId(), payment.getPaymentTransactionId(),processPaymentGateway.getApproved(), processPaymentGateway.getMessage(), payment.getCarId());
        Message<ReservePaidMessage> reservePaidMessageMessage = MessageBuilder.withPayload(reservePaidMessage).build();
        paymentSource.sendMessage().send(reservePaidMessageMessage);
    }

    public void fallbackProcessPayment() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("fallback is dispatched");
    }
}
