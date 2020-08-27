package br.com.rentacar.reserveserviceapplication.repositories;

import br.com.rentacar.reserveserviceapplication.entities.ReservationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReserveHistoryRepository extends JpaRepository<ReservationHistory, Long> {
    List<ReservationHistory> findByReserveTransactionId(UUID reserveTransactionId);
}
