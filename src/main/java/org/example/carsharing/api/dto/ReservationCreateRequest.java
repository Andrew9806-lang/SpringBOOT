package org.example.carsharing.api.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.Instant;
// eto zapros ot clienta

public class ReservationCreateRequest {
    @NotNull
    @Positive // ne dolzhno bit otricatelnoe
    private Long userId;

    @NotNull
    @Positive
    private Long carId;

    @NotNull
    @Future
    private Instant startAT;

    @NotNull
    @Future // proverka na budushee no chtob proshloe vremya ne proverylos to est ne menshe zadannogo
    private Instant endAT;
    //etot metod dlya zaprosa ot clienta
    // proveryaet clienta
    //proveryet vremya nachalo i chto ono ne null vremya konca i chto nachalo ne pozhe vremeni konca

    @AssertTrue
    public boolean isTimeRangeValid() {
        // startAt null = false , i tak zhe endAt  i chtob vremya sozdaniya pozhe konca
        return startAT != null && endAT != null && startAT.isBefore(endAT);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Instant getStartAT() {
        return startAT;
    }

    public void setStartAT(Instant startAT) {
        this.startAT = startAT;
    }

    public Instant getEndAT() {
        return endAT;
    }

    public void setEndAT(Instant endAT) {
        this.endAT = endAT;
    }
}
