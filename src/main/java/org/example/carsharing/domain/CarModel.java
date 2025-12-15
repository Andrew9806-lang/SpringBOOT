package org.example.carsharing.domain;

import jakarta.persistence.*;

/*
 *Model avtomobilya (brand + harakteristiki )
 */
@Entity
@Table(name = "car-models")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @Column(name = "model-name", nullable = false)
    private String nameModels;
    @Column(nullable = false)
    private Integer seats;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    @Column(name = "Fuel_type", nullable = false)
    private FuelType fuelType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getNameModels() {
        return nameModels;
    }

    public void setNameModels(String nameModels) {
        this.nameModels = nameModels;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }
}

