package org.example.carsharing.api.dto;



import org.example.carsharing.domain.CarPhoto;

import java.time.Instant;

public class CarPhotoDto {
    private Long id;
    private Long carId;
    private String url;
    private Instant createdAt;

    public static CarPhotoDto fromEntity(CarPhoto p) {
        CarPhotoDto dto = new CarPhotoDto();
        dto.setId(p.getId());
        dto.setCarId(p.getCar().getId());
        dto.setUrl(p.getUrl());
        dto.setCreatedAt(p.getCreatedAt());
        return dto;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

