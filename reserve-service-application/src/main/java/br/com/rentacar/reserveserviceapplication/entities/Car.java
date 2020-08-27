package br.com.rentacar.reserveserviceapplication.entities;

import br.com.rentacar.reserveserviceapplication.types.CarBrand;
import br.com.rentacar.reserveserviceapplication.types.CarSituation;
import lombok.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = "vehiclePlate")
})
public class Car extends JdkSerializationRedisSerializer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String year;
    @Enumerated(EnumType.ORDINAL)
    @NonNull
    private CarBrand carBrand;
    @NonNull
    private double dayPrice;
    @NonNull
    private Boolean available;
    @NonNull
    private String vehiclePlate;
    @Enumerated(EnumType.ORDINAL)
    private CarSituation carSituation = CarSituation.AVAILABLE;
}
