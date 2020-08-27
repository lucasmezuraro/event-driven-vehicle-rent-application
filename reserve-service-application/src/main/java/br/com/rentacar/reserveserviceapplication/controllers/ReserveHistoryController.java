package br.com.rentacar.reserveserviceapplication.controllers;

import br.com.rentacar.reserveserviceapplication.entities.ReservationHistory;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import br.com.rentacar.reserveserviceapplication.services.ReserveHistoryService;
import br.com.rentacar.reserveserviceapplication.services.ReserveService;
import br.com.rentacar.reserveserviceapplication.types.ReserveStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/history")
public class ReserveHistoryController {

    private static final Log log = LogFactory.getLog(ReserveHistoryController.class);

    @Autowired
    ReserveService reserveService;

    @Autowired
    ReserveHistoryService reserveHistoryService;

    @GetMapping(path = "/{transactionId}")
    public ResponseEntity<ReserveStatus> findByTransactionId(@PathVariable("transactionId") UUID transactionId) {
        log.info("find reserve status");
        Reserve reserve = reserveService.findOneByTransactionId(transactionId);
        return ResponseEntity.ok(reserve.getReserveStatus());
    }

    @GetMapping(path = "/reserve/{transactionId}")
    public ResponseEntity<List<ReservationHistory>> findHistoricalReserve(@PathVariable("transactionId") UUID transactionId) {
        log.info("reserve history");
        List<ReservationHistory> reservationHistories = reserveHistoryService.findReserveHistory(transactionId);
        return ResponseEntity.ok(reservationHistories);
    }
}
