package br.com.rentacar.reserveserviceapplication.dtos;

import br.com.rentacar.reserveserviceapplication.entities.Car;
import lombok.Data;

@Data
public class CarRequestDTO {
    private Car car;
}
