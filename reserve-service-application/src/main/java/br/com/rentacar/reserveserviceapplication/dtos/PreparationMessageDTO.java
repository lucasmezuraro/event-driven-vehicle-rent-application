package br.com.rentacar.reserveserviceapplication.dtos;

import br.com.rentacar.reserveserviceapplication.types.PreparationPhrase;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class PreparationMessageDTO {
    UUID preparationTransactionId;
    UUID reserveTransactionId;
    PreparationPhrase preparationPhrase;
    Long carId;
}
