package br.com.rentacar.reserveserviceapplication.services;

import br.com.rentacar.reserveserviceapplication.entities.CarHistory;
import br.com.rentacar.reserveserviceapplication.repositories.CarHistoryRepository;
import javassist.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarHistoryService {

    private static final Log log = LogFactory.getLog(CarHistoryService.class);

    @Autowired
    CarHistoryRepository carHistoryRepository;

    public CarHistory create(CarHistory carHistory) throws JDBCException {
        log.info("creating a car history");
        return carHistoryRepository.save(carHistory);
    }

    public CarHistory findById(Long id) throws NotFoundException {
        log.info("find a car history");
        return carHistoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Car is not found"));
    }

}
