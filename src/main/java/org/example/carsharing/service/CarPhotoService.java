package org.example.carsharing.service;


import org.example.carsharing.domain.Car;
import org.example.carsharing.domain.CarPhoto;
import org.example.carsharing.domain.CarPhotoRequest;
import org.example.carsharing.repository.CarPhotoRepository;
import org.example.carsharing.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarPhotoService {
    private final CarPhotoRepository carPhotoRepository;
    private final CarRepository carRepository;

    public CarPhotoService(CarPhotoRepository carPhotoRepository, CarRepository carRepository) {
        this.carPhotoRepository = carPhotoRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    public CarPhoto add(Long carId, CarPhotoRequest request) {
        Car car = carRepository.findById(carId)
                .orElseThrow();

        CarPhoto carPhoto = new CarPhoto();
        carPhoto.setCar(car);
        carPhoto.setUrl(request.getUrl());
        return carPhotoRepository.save(carPhoto);
    }

    public List<CarPhoto> listByCar(Long carId) {
        return carPhotoRepository.findByCar_IdOrderByCreatedAtDesc(carId);
    }
}
