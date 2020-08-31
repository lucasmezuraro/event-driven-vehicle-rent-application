package br.com.rentacar.reserveserviceapplication.units;

import br.com.rentacar.reserveserviceapplication.controllers.ReserveController;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import br.com.rentacar.reserveserviceapplication.entities.ReserveDuration;
import br.com.rentacar.reserveserviceapplication.repositories.ReserveRepository;
import br.com.rentacar.reserveserviceapplication.services.ReserveService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.Optional;

import static reactor.core.publisher.Mono.when;
@DataJpaTest
public class TestReserveService {

    @Autowired
    ReserveRepository reserveRepository;

    ReserveDuration reserveDuration;
    Reserve reserve;
    Long id;

    @BeforeEach
    public void init() {
        reserveDuration = new ReserveDuration(5, new Date(2020, 8, 22),new Date(2020, 8, 27));
        reserve = new Reserve(Long.decode("1"), Long.decode("1"), reserveDuration);
        id = (long) 1;
    }

    @Test
    public void createAReserve() {
        Reserve reserveSaved = reserveRepository.save(reserve);
        Assertions.assertThat(reserveSaved).isEqualTo(reserve);
    }

    @Test
    public void updateAReserve() {
        Reserve reserveSaved = reserveRepository.save(reserve);
        ReserveDuration reserveDurationChange = new ReserveDuration(9, new Date(2020, 8, 22),new Date(2020, 8, 31));
        reserveSaved.setReserveDuration(reserveDurationChange);
        Reserve reserveUpdated = reserveRepository.save(reserveSaved);
        Assertions.assertThat(reserveUpdated).isEqualTo(reserveSaved);
    }

    @Test
    public void deleteAReserve() {
        Reserve reserveSaved = reserveRepository.save(reserve);
        Assertions.assertThat(reserveSaved).isEqualTo(reserve);
        reserveRepository.delete(reserveSaved);
        Optional<Reserve> isDeleted = reserveRepository.findById(reserveSaved.getId());
        Assertions.assertThat(isDeleted.isPresent()).isFalse();

    }

}
