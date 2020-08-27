package br.com.rentacar.paymentserviceapplication.entities;

import br.com.rentacar.paymentserviceapplication.types.PaymentStatus;
import br.com.rentacar.paymentserviceapplication.types.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    UUID paymentTransactionId;
    UUID reserveTransactionId;
    Date processedAt;
    Date createdAt = new Date();
    Long locatorId;
    Long carId;
    double amount;
    PaymentType paymentType;
    PaymentStatus paymentStatus = PaymentStatus.NEW;
    String messageGateway;

    @PrePersist
    protected void onCreate() {
        processedAt = new Date();
    }

    public Payment(UUID reserveTransactionId, Long locatorId, Long carId, double amount, PaymentType paymentType) {
        this.reserveTransactionId = reserveTransactionId;
        this.locatorId = locatorId;
        this.carId = carId;
        this.amount = amount;
        this.paymentType = paymentType;
    }
}
