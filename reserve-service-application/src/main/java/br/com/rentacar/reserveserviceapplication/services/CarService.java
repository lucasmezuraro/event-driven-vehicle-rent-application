package br.com.rentacar.reserveserviceapplication.services;

import br.com.rentacar.reserveserviceapplication.dtos.CarUpdateDTO;
import br.com.rentacar.reserveserviceapplication.entities.Car;
import br.com.rentacar.reserveserviceapplication.entities.CarHistory;
import br.com.rentacar.reserveserviceapplication.repositories.CarHistoryRepository;
import br.com.rentacar.reserveserviceapplication.repositories.CarRepository;
import br.com.rentacar.reserveserviceapplication.types.CarSituation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CarService {

    private static final Log log = LogFactory.getLog(CarService.class);

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarHistoryService carHistoryService;


    public Car create(Car car) {
        log.info("creating a car");
        return carRepository.save(car);
    }

    public Car findOne(Long id) throws ResponseStatusException {
        log.info("find a one car");
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car is not found"));
        System.out.println("ID CARRO RETORNADO: "+ car.getId());
        return car;
    }
    
    public Car updateCar(Long id, CarUpdateDTO carUpdateDTO) {
        log.info("updating a car");
    	Car car = findOne(id);
		car.setCarSituation(carUpdateDTO.getCarSituation());
		car.setAvailable(carUpdateDTO.getAvailable());
		carRepository.save(car);
    	return car;
    }
}
