package br.com.rentacar.reserveserviceapplication.services;

import br.com.rentacar.reserveserviceapplication.entities.ReservationHistory;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import br.com.rentacar.reserveserviceapplication.repositories.ReserveRepository;
import br.com.rentacar.reserveserviceapplication.types.ReserveStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReserveService {

    private static final Log log = LogFactory.getLog(ReserveService.class);

    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private ReserveHistoryService reserveHistoryService;

    public Reserve create(Reserve reserve) throws JDBCException {
        log.info("creating a reserve");
        ReservationHistory reservationHistory = new ReservationHistory(reserve.getTransactionId(), reserve.getReserveStatus(), "Created");
        reserveHistoryService.create(reservationHistory);
        return reserveRepository.save(reserve);
    }

    @Cacheable(value = "Reserve", key = "#id")
    public Reserve findOne(Long id) throws ResponseStatusException {
        log.info("find a reserve");
        return reserveRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Reserve is not found"));
    }

    @Cacheable(value = "ReserveByTransactionId", key = "#transactionId")
    public Reserve findOneByTransactionId(UUID transactionId) throws ResponseStatusException {
        log.info("find a reserve find by transaction id");
        return reserveRepository.findOneByTransactionId(transactionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found"));
    }

    public Reserve updateReserveStatus(UUID reserveTransactionId, ReserveStatus reserveStatus) {
        log.info("updating a reserve");
        Optional<Reserve> reserve = reserveRepository.findOneByTransactionId(reserveTransactionId);
        Reserve reservation = reserve.get();
        reservation.setReserveStatus(reserveStatus);
        reserveRepository.save(reservation);
        ReservationHistory reservationHistory = new ReservationHistory(reservation.getTransactionId(), reservation.getReserveStatus(), "Updated");
        reserveHistoryService.create(reservationHistory);
        System.out.println(reserveTransactionId + " CHANGED");
        return reservation;
    }

}
