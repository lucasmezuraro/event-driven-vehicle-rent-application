package br.com.rentacar.reserveserviceapplication.repositories;

import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    Optional<Reserve> findOneByTransactionId(UUID transactionId);
}
