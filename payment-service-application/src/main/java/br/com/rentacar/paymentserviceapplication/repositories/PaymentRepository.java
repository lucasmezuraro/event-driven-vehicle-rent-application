package br.com.rentacar.paymentserviceapplication.repositories;

import br.com.rentacar.paymentserviceapplication.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findOneByReserveTransactionId(UUID reserveTransactionId);
}
