package br.com.rentacar.reserveserviceapplication.dtos.stream;

import br.com.rentacar.reserveserviceapplication.dtos.PmtInfo;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.Locator;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import br.com.rentacar.reserveserviceapplication.repositories.CarRepository;
import br.com.rentacar.reserveserviceapplication.services.CarService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
public class NewReserveMessage {

    UUID reserveTransactionId;
    double amountReserve;
    Long locatorId;
    Long carId;
    int daysReserve;
    PmtInfo pmtInfo;

    @Autowired
    CarRepository carRepository;

    public NewReserveMessage(Reserve reserve, Car car, PmtInfo pmtInfo) {
        this.reserveTransactionId = reserve.getTransactionId();
        this.amountReserve = reserve.getReserveDuration().getDaysReserve() * car.getDayPrice();
        this.locatorId = reserve.getLocatorId();
        this.carId = reserve.getCarId();
        this.daysReserve = reserve.getReserveDuration().getDaysReserve();
        this.pmtInfo = pmtInfo;
    }
}
