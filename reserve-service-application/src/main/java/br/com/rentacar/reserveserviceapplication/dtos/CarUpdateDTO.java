package br.com.rentacar.reserveserviceapplication.dtos;

import br.com.rentacar.reserveserviceapplication.types.CarSituation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarUpdateDTO {

	private CarSituation carSituation;
	private String message;
	private Boolean available;
}
