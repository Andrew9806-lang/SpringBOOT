package org.example.carsharing.api.dto;

import org.example.carsharing.domain.Car;
/*
Dto - data transfer Object preobrazovivaet v json api i potom na front
//mi vibiraem te dannie kotorie nam nuzhno stalnoe otkidivaem
// front ne znaet chto takoe enum
// ne  pokazivaet bet many to one anotaciy hibernate
 */
public class CarDto {
    private Long id;
    private String brand;
    private String transmition;
    private String model;
    private int seats;
    private String fuelType;
    private String plateNumber;
    private Long locationId;
    private String LocationName;
    private String status;
    //
public  static CarDto fromEntity(Car car){
    /*
   Берёт Car (Entity из БД)
 Создаёт CarDto (объект для клиента)
Копирует нужные данные
Возвращает готовый DTO
     */
    CarDto carDto = new CarDto();
    /*
     пустую коробку, в которую сейчас будешь класть данные.
     teper mi vitaskivaem dannie s entity i lozhim v dto
     */
    carDto.setId(car.getId());
    carDto.setBrand(car.getCarModel().getBrand());
    carDto.setModel(car.getCarModel().getNameModels());
    carDto.setSeats(car.getCarModel().getSeats());
    // tut name potamuchto enum
    carDto.setTransmition(car.getCarModel().getTransmission().name());
    // tut name potamuchto enum
    carDto.setFuelType(car.getCarModel().getFuelType().name());
    carDto.setLocationId(car.getLocation().getID());
    carDto.setLocationName(car.getLocation().getName());
    // potamuchto enum
    carDto.setStatus(car.getStatus().name());
    carDto.setPlateNumber(car.getPlateNumber());
    return carDto;
}
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

    public String getTransmition() {
        return transmition;
    }

    public void setTransmition(String transmition) {
        this.transmition = transmition;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
