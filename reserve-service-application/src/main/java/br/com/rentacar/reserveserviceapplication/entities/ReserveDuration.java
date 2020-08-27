package br.com.rentacar.reserveserviceapplication.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = ReserveDuration.class)
public class ReserveDuration extends JdkSerializationRedisSerializer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    int daysReserve;
    @NonNull
    @JsonFormat(pattern="yyyy/MM/dd")
    Date startAt;
    @NonNull
    @JsonFormat(pattern="yyyy/MM/dd")
    Date endAt;
}
