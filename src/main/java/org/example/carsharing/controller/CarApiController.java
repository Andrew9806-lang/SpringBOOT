package org.example.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.carsharing.api.dto.CarDto;
import org.example.carsharing.service.CarService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/*
REST-kontroller kataloga i dostupnosti avtomobilei.
-GET/api/cars - katalog s filtrami
-GET/api/availabity - dostupnie avtomobili po vremennomu intervalu
 */

// restcontroleer vozvrashaet napryamuyu v HTTP bez Html
//peredelivaet v json
//vizivaet service
@RestController

//eto znachto chto ssila budet nachinatsya
@RequestMapping("/api")
//Это аннотация Spring, которая включает валидацию входящих данных.
@Validated
@Tag(name = "Cars", description = "Каталог и доступность автомобилей")
public class CarApiController {
    private final CarService carService;

    public CarApiController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    @Operation(summary = "Каталог автомобилей", description = "Фильтры: locationId, modelId, seats")// opisivaet operaciyu kotoruyu mi dolzhni sdelat
    @ApiResponse(responseCode = "200",description = "OK")
    // dobavili parametr
    @Parameter(name = "Location Id", description = "Identifikator lokacii")
    // ssilka posle api cars
    public List<CarDto> cars(@RequestParam(required = false) Long locationId,//
                             @RequestParam(required = false) Long modelID,
                             @RequestParam(required = false) Integer seats) {
        return carService.list(locationId, modelID, seats).stream()
                //peredelivaet iz sushnosti v dto
                //
                .map(CarDto::fromEntity)
                // sobiraet to  v list otfil
//pochemu tut collect
                .collect(Collectors.toList()); // mozhno to list
    }

    @GetMapping("/cars/avaiblity")
    @Operation(summary = "Доступность автомобилей", description = "Интервал времени ISO-8601 b опциональные фильтры: locationId, modelId, seats")
    @ApiResponse(responseCode = "200",description = "OK")
    public List<CarDto> available(@RequestParam(required = false) Long locationId,
                                  //Говорит Spring, в каком формате приходит дата.
                                  //Здесь: стандарт ISO 8601 (например, 2025-12-17T10:00:00Z)
                                  //Spring автоматически превращает строку из URL в объект Instant
                                  //chtob spring ponimal v kakom formate prihodit data
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startAt,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant startEnd,
                                  @RequestParam(required = false) Integer seats) {
        return carService.avaible(locationId, startAt, startEnd, seats).stream()
                .map(CarDto::fromEntity)
                .toList(); //sintaksichwskiy sahar

    }
}

