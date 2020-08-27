package br.com.rentacar.preparationserviceapplication.dtos;

import br.com.rentacar.preparationserviceapplication.types.PreparationPhrase;
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
