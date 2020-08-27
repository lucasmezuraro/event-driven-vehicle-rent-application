package br.com.rentacar.preparationserviceapplication.entities;

import br.com.rentacar.preparationserviceapplication.types.PreparationPhrase;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class CarPreparation {

   private UUID preparationId = UUID.randomUUID();
   @NonNull
   private String name;
   @NonNull
   private Long carId;
   @NonNull
   private UUID reserveTransactionId;
   private PreparationPhrase preparationPhrase = PreparationPhrase.PENDING;
   private Date startAt = new Date();

}
