package br.com.rentacar.reserveserviceapplication.services;

import br.com.rentacar.reserveserviceapplication.entities.Locator;
import br.com.rentacar.reserveserviceapplication.repositories.LocatorRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LocatorService {

    private static final Log log = LogFactory.getLog(LocatorService.class);

    @Autowired
    LocatorRepository locatorRepository;

    public Locator findOne(Long id) {
        log.info("find a locator");
        return locatorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Locator is not found"));
    }

    public Locator create(Locator locator) {

        log.info("creating a locator");

        return locatorRepository.save(locator);
    }
}
