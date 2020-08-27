package br.com.rentacar.reserveserviceapplication.dtos;

import br.com.rentacar.reserveserviceapplication.entities.ReserveDuration;
import org.springframework.stereotype.Component;

@Component
public class ReserveCreateRequestDTO {

    public Long carId;
    public Long locatorId;
    public ReserveDuration reserveDuration;
    public PmtInfo pmtInfo;
}
