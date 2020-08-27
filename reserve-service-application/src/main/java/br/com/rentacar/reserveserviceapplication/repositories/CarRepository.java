package br.com.rentacar.reserveserviceapplication.repositories;

import br.com.rentacar.reserveserviceapplication.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
