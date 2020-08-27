package br.com.rentacar.reserveserviceapplication.controllers;

import br.com.rentacar.reserveserviceapplication.dtos.ReserveCreateRequestDTO;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.Locator;
import br.com.rentacar.reserveserviceapplication.entities.Reserve;
import br.com.rentacar.reserveserviceapplication.entities.ReserveDuration;
import br.com.rentacar.reserveserviceapplication.producers.ReserveProducer;
import br.com.rentacar.reserveserviceapplication.services.CarService;
import br.com.rentacar.reserveserviceapplication.services.LocatorService;
import br.com.rentacar.reserveserviceapplication.services.ReserveService;
import br.com.rentacar.reserveserviceapplication.types.CarBrand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    private static final Log log = LogFactory.getLog(ReserveController.class);

    @Autowired
    ReserveProducer reserveProducer;

    @Autowired
    ReserveService reserveService;

    @Autowired
    CarService carService;
    @Autowired
    LocatorService locatorService;



    @GetMapping(path = "/{id}")
    @ResponseBody()
    public ResponseEntity<Reserve> index(@PathVariable("id") Long id) {
        log.info("index of reserves");
        Reserve reserve = reserveService.findOne(id);
        return ResponseEntity.ok(reserve);
    }

    @PostMapping("/")
    @ResponseBody()
    public ResponseEntity<Reserve> create(@RequestBody() ReserveCreateRequestDTO reserveCreateRequestDTO) throws HttpResponseException {
        log.info("creating a reserve");
        Car car = carService.findOne(reserveCreateRequestDTO.carId);
        System.out.println(car.getId() + " "+ car.getDayPrice() + " "+ car.getAvailable());
        if (car.getAvailable()) {
            Reserve reserve = new Reserve(reserveCreateRequestDTO.carId, reserveCreateRequestDTO.locatorId, reserveCreateRequestDTO.reserveDuration);
            Reserve reserveSaved = reserveService.create(reserve);
            System.out.println(reserve.toString());
            reserveProducer.sendMessage(reserve, car, reserveCreateRequestDTO.pmtInfo);
            return ResponseEntity.ok(reserveSaved);
        }else {
            throw new HttpResponseException(406, "Car was reserved");
        }
    }



}

