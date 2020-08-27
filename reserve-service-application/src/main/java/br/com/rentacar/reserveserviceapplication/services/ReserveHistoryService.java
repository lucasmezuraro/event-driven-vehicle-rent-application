package br.com.rentacar.reserveserviceapplication.services;

import br.com.rentacar.reserveserviceapplication.entities.ReservationHistory;
import br.com.rentacar.reserveserviceapplication.repositories.ReserveHistoryRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Service
public class ReserveHistoryService {

    private static final Log log = LogFactory.getLog(ReserveHistoryService.class);

    @Autowired
    private ReserveHistoryRepository reserveHistoryRepository;

    public ReservationHistory create(ReservationHistory reservationHistory) {
        log.info("creating a reserve history");
        System.out.println("Reservation History: "+ reservationHistory.getReserveTransactionId() + " message: "+ reservationHistory.getMessage());
        return reserveHistoryRepository.save(reservationHistory);
    }

    @Cacheable(value = "ReserveHistoryByTransactionId", key = "#reserveTransactionId")
    public List<ReservationHistory> findReserveHistory(UUID reserveTransactionId) {
        log.info("find reserve history");
        return this.reserveHistoryRepository.findByReserveTransactionId(reserveTransactionId);
    }
}
