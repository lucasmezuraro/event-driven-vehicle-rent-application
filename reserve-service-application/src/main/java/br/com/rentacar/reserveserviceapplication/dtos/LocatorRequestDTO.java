package br.com.rentacar.reserveserviceapplication.dtos;

import br.com.rentacar.reserveserviceapplication.entities.Locator;
import lombok.Data;

@Data
public class LocatorRequestDTO {
    private Locator locator;
}
