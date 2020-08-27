package br.com.rentacar.paymentserviceapplication.services;

import br.com.rentacar.paymentserviceapplication.bindings.ReservationSource;
import br.com.rentacar.paymentserviceapplication.dtos.ProcessPaymentGateway;
import br.com.rentacar.paymentserviceapplication.entities.Payment;
import br.com.rentacar.paymentserviceapplication.repositories.PaymentRepository;
import br.com.rentacar.paymentserviceapplication.types.PaymentStatus;
import com.rabbitmq.http.client.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentGateway {

    private static final Log log = LogFactory.getLog(PaymentGateway.class);

    @Autowired
    PaymentRepository paymentRepository;

    public ProcessPaymentGateway executePayment(Payment payment) throws Exception{

        log.info("send users pay infos for gateway");

        Boolean success = new Random().nextBoolean();

        PaymentStatus status = success ? PaymentStatus.PAID : PaymentStatus.REJECTED;

        Payment paymentSaved = paymentRepository.findById(payment.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_GATEWAY));
        paymentSaved.setPaymentStatus(status);
        paymentSaved.setPaymentTransactionId(UUID.randomUUID());
        paymentRepository.save(paymentSaved);
        String message = success ? "Payment approved" : "Out of limit";
        ProcessPaymentGateway processPaymentGateway = new ProcessPaymentGateway(success,message, status);
        payment = paymentProcess(payment.getId(),processPaymentGateway);
        System.out.println("******************************************************");
        System.out.println("******************************************************");
        System.out.println(payment.getMessageGateway() + " "+ payment.getPaymentStatus());
        System.out.println("******************************************************");
        System.out.println("******************************************************");
        return processPaymentGateway;
    }

    public Payment findById(Long id) throws ResponseStatusException {
        log.info("find payment by id");
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
    }

    public Payment findByReserveTransactionId(UUID reserveTransactionId) throws ResponseStatusException {
        log.info("find payment reserve by trasaction id");
        return paymentRepository.findOneByReserveTransactionId(reserveTransactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Payment not found"));
    }

    public Payment paymentProcess(Long id, ProcessPaymentGateway processPaymentGateway) throws Exception {
        log.info("payment process");
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new Exception("Error"));

        payment.setMessageGateway(processPaymentGateway.getMessage());
        paymentRepository.save(payment);
        return payment;
    }

    public Payment create(Payment payment) {
        return paymentRepository.save(payment);
    }
}
