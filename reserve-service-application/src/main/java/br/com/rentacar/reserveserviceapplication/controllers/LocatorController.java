package br.com.rentacar.reserveserviceapplication.controllers;

import br.com.rentacar.reserveserviceapplication.dtos.CarRequestDTO;
import br.com.rentacar.reserveserviceapplication.dtos.LocatorRequestDTO;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.Locator;
import br.com.rentacar.reserveserviceapplication.services.CarService;
import br.com.rentacar.reserveserviceapplication.services.LocatorService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locator")
public class LocatorController {

    private static final Log log = LogFactory.getLog(LocatorController.class);

    @Autowired
    LocatorService locatorService;

    @PostMapping("/")
    @ResponseBody()
    public ResponseEntity<Locator> create(@RequestBody() LocatorRequestDTO locatorRequestDTO) {
        log.info("creating a locator");
        Locator locator = locatorService.create(locatorRequestDTO.getLocator());
        return ResponseEntity.ok(locator);
    }

    public ResponseEntity<Locator> find(@PathVariable("id") Long id) {
        log.info("finding a locator");
        Locator locator = locatorService.findOne(id);
        return ResponseEntity.ok(locator);
    }
}
