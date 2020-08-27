package br.com.rentacar.reserveserviceapplication.entities;

import br.com.rentacar.reserveserviceapplication.types.ReserveStatus;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Reserve.class)

public class Reserve extends JdkSerializationRedisSerializer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final UUID transactionId = UUID.randomUUID();
    private Date createdAt = new Date();

    @Enumerated(EnumType.ORDINAL)
    private ReserveStatus reserveStatus = ReserveStatus.NEW;

    @NonNull
    private Long carId;

    @NonNull
    private Long locatorId;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reserve_duration_id", referencedColumnName = "id")
    private ReserveDuration reserveDuration;

    public String toString() {
        return " Reserve: "+this.transactionId + " "
                + this.reserveStatus.toString() + " "
                + this.getCarId() + " "
                + this.getLocatorId();
    }
}
