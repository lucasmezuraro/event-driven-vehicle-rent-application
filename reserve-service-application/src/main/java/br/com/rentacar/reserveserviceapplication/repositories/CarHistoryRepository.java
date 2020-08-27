package br.com.rentacar.reserveserviceapplication.repositories;

import br.com.rentacar.reserveserviceapplication.entities.CarHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarHistoryRepository extends JpaRepository<CarHistory, Long> {
}
