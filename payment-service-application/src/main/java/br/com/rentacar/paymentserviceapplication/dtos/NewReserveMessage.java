package br.com.rentacar.paymentserviceapplication.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class NewReserveMessage {
    UUID reserveTransactionId;
    double amountReserve;
    Long locatorId;
    Long carId;
    int daysReserve;
    PmtInfo pmtInfo;
}

