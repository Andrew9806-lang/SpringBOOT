package org.example.carsharing.api.dto;

import org.example.carsharing.domain.Reservation;

import java.time.Instant;

public class ReservationDto {
    // chtob otlichat odnu bron ot drugoi
    private Long id;
    //po id mi mozhem poluchit vse dannie tak kak id Identity
    private Long userId;
    //  //po id mi mozhem poluchit vse dannie tak kak id Identity
    private Long carId;
    // Instant — это точный момент времени без часового пояса utc format
    //дату время в миллисекундах от 1 января 1970 (Unix time)
    //Instant — лучший тип для времени в API и базе данных
    private Instant startAt;
    private  Instant endAt;
    private String status;

    public static ReservationDto fromEntity(Reservation reservation) {
        //sozdaem dto korobku
        ReservationDto dto = new ReservationDto();
        // zaprishivaem s entity i lozhim v dto
        dto.setId(reservation.getId());
        dto.setUserId(reservation.getUser().getId());
        dto.setCarId(reservation.getCar().getId());
        dto.setStartAt(reservation.getStartAt());
        dto.setEndAt(reservation.getEndAt());
        // name potamuchto enum front ne znaet chto takoe enum
        dto.setStatus(reservation.getStatus().name());

        return dto;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
