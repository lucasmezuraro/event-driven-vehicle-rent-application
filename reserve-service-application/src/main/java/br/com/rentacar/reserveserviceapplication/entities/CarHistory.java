package br.com.rentacar.reserveserviceapplication.entities;

import br.com.rentacar.reserveserviceapplication.types.CarSituation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CarHistory.class)
public class CarHistory extends JdkSerializationRedisSerializer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long carId;
    @NonNull
    @Enumerated(EnumType.ORDINAL)
    private CarSituation carSituation;
    @NonNull
    private String message;
    private Date createdAt = new Date();

}
