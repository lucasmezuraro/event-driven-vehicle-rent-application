package br.com.rentacar.reserveserviceapplication.types;

import org.springframework.stereotype.Component;

public enum ReserveStatus {
    NEW,
    CREATED,
    PAID,
    REFUNDED,
    REJECTED,
    DELIVERED,
    PREPARED
}
