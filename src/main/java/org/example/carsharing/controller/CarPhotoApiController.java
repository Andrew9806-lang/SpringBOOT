package org.example.carsharing.controller;

import org.example.carsharing.api.dto.CarPhotoDto;
import org.example.carsharing.domain.CarPhoto;
import org.example.carsharing.domain.CarPhotoRequest;
import org.example.carsharing.service.CarPhotoService;
import org.hibernate.validator.constraints.Mod11Check;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CarPhotoApiController {
    private final CarPhotoService photoService;

    public CarPhotoApiController(CarPhotoService photoService) {
        this.photoService = photoService;
    }
    @PostMapping("/cars/{cardId}/photo") // dobavlyaem
    public ResponseEntity<CarPhotoDto> add( // chto budet vozvrashatsya object takogo tipa
            @PathVariable Long carId,
            @RequestBody CarPhotoRequest request
    ){
        CarPhoto carPhoto = photoService.add(carId,request);
        return ResponseEntity.status(201).body(CarPhotoDto.fromEntity(carPhoto));

    }
    @GetMapping("/cars/{carId}/photos") // dostaem
    public List<CarPhotoDto> list(@PathVariable Long carId){
        return photoService.listByCar(carId).stream()
                .map(CarPhotoDto::fromEntity) // dlya kazhdogo metoda vizivaet DTO
                .toList();
    }
}
