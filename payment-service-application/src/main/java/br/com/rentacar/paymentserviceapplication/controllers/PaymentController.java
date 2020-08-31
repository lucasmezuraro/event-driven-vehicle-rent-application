package br.com.rentacar.paymentserviceapplication.controllers;

import br.com.rentacar.paymentserviceapplication.bindings.ReservationSource;
import br.com.rentacar.paymentserviceapplication.entities.Payment;
import br.com.rentacar.paymentserviceapplication.services.PaymentGateway;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private static final Log log = LogFactory.getLog(PaymentController.class);

    @Autowired
    PaymentGateway paymentGateway;

    @GetMapping("/{reserveTransactionId}")
    @ResponseBody()
    public ResponseEntity<Payment> find(@PathVariable("reserveTransactionId") UUID reserveTransactionId) {
        log.info("finding a reserve by transaction id");
        return ResponseEntity.ok(paymentGateway.findByReserveTransactionId(reserveTransactionId));
    }

    @GetMapping("/hello")
    @ResponseBody()
    public ResponseEntity<String> show() {
        return ResponseEntity.ok("its working");
    }
}
