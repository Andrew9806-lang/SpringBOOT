package org.example.carsharing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.carsharing.api.dto.ReservationCreateRequest;
import org.example.carsharing.api.dto.ReservationDto;
import org.example.carsharing.domain.Reservation;
import org.example.carsharing.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
rest controller bronirovaniya
-post / api /reservation  -sozdaniya bronirovaniya s validaciei i biznes proverkami
 */
@RestController
@RequestMapping("/api")
@Tag(name="Reservation",description = "Sozdanie bronirovanie avromobilya")
public class ReservationApiController {
    private final ReservationService service;

    public ReservationApiController(ReservationService service) {
        this.service = service;
    }

    @PostMapping("/reservations")
    @Operation(summary = "Sozdat Bronirovanie",description = "Proveryaet dostupnost i sozdaet zapis bronirovaniya")
    @ApiResponse(responseCode = "201",description = "Created")
    @ApiResponse(responseCode = "404",description = "User ili Car ne naideni")
    @ApiResponse(responseCode = "422",description = "Validation/busines error")
    @Tag(name = "Reservation",description = "Sozdanie bronirovaniya avtomobilei")
    @Parameter(name = "Request",description = "DTO s dannimi bronirovaniya")
    //pri znachenii parametrov po umolchaniyu
    //budet v zagalovke opisaniya controllera
    public ResponseEntity<ReservationDto> create(@Valid @RequestBody ReservationCreateRequest request) {
        Reservation reservation = service.create(request);
        //HTTP otvet -responseEntity .statu. sam otvet v vvide dto
        return ResponseEntity.status(201).body(ReservationDto.fromEntity(reservation));

    }
}
