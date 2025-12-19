package org.example.carsharing.repository;

import org.example.carsharing.domain.Car;
import org.example.carsharing.domain.VehicleClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleClassRepository extends JpaRepository<VehicleClass,Long> {
//    public List<Car> findByClassIdAndMinSeats(long classID, int minSeats);
}
