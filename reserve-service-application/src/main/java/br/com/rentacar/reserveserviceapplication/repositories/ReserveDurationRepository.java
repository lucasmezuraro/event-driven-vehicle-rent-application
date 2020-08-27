package br.com.rentacar.reserveserviceapplication.repositories;

import br.com.rentacar.reserveserviceapplication.entities.ReserveDuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveDurationRepository extends JpaRepository<ReserveDuration, Long> {
}
