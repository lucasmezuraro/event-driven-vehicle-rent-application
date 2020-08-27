package br.com.rentacar.reserveserviceapplication.controllers;

import br.com.rentacar.reserveserviceapplication.dtos.CarRequestDTO;
import br.com.rentacar.reserveserviceapplication.dtos.CarUpdateDTO;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.CarHistory;
import br.com.rentacar.reserveserviceapplication.services.CarHistoryService;
import br.com.rentacar.reserveserviceapplication.services.CarService;
import br.com.rentacar.reserveserviceapplication.types.CarSituation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/car")
public class CarController {

    private static final Log log = LogFactory.getLog(CarController.class);

    @Autowired
    CarService carService;
    
    @Autowired
    CarHistoryService carHistoryService;

    @PostMapping("/")
    @ResponseBody()
    @Transactional
    public ResponseEntity<Car> create(@RequestBody() CarRequestDTO carRequestDTO) throws JDBCException {
        log.info("creating a car history");
        Car car = carService.create(carRequestDTO.getCar());
        System.out.println(car.getId() + " - " + car.getDayPrice());
        CarHistory carHistory = new CarHistory(car.getId(), CarSituation.AVAILABLE, "Car is created now");
        carHistoryService.create(carHistory);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/{id}")
    @ResponseBody()
    public ResponseEntity<Car> find(@PathVariable("id") Long id) {
        log.info("finding a car");
        Car car = carService.findOne(id);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/{carId}")
    @ResponseBody()
    public ResponseEntity<Car> updateStatus(@PathVariable("carId") Long id, @RequestBody() CarUpdateDTO carUpdateDTO) {
		log.info("updating car status");
        Car car = carService.updateCar(id, carUpdateDTO);
		return ResponseEntity.ok(car);
    }

}
