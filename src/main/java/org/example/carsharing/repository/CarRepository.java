package org.example.carsharing.repository;

import org.example.carsharing.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
//    public List<Car> findByClassAndMinSeats(long classId , int minSeats);
//    boolean exsistByPlateNumber (String plateNumber);
}
