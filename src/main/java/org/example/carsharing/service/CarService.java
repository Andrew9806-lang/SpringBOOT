package org.example.carsharing.service;

import org.example.carsharing.domain.Car;
import org.example.carsharing.domain.CarStatus;
import org.example.carsharing.repository.CarRepository;
import org.example.carsharing.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ReservationRepository reservationRepository;

    public CarService(CarRepository carRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

    //?????????
    public List<Car> list(Long locationId, Long modelId, Integer seats) {
        // nahodim vse mashini
        return carRepository.findAll().stream()
                // polyovatel sravnivaet svozu lokacizu s mashinami kotorie svobodnie po bloyosti v etoi lokacii
                .filter(car -> locationId == null || car.getLocation().getID().equals(locationId))
                // esli polyovatel vvodit model tomishem dostupnie esli net vivodim vse modeli
                .filter(car -> modelId == null || car.getCarModel().getId().equals(modelId))
                //polyovatel ne vvel kolichestvo sideniz a ynachit vse mashini esli vvodit to sravnivaet
                .filter(car -> seats == null || car.getCarModel().getSeats() > seats)
                .collect(Collectors.toList());
    }

    public List<Car> avaible(Long locationId, Instant startAt, Instant endAt, Integer seats) {
        return carRepository.findAll().stream()
                // proverka postupnosti
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                //
                .filter(car -> locationId == null || car.getLocation().getID().equals(locationId))
                .filter(car -> seats == null || car.getCarModel().getSeats() >= seats)
                .filter(car -> reservationRepository.countOverLaps(car.getId(), startAt, endAt) == 0)
                .collect(Collectors.toList());
    }
}
