package org.example.carsharing.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    //ne mogu sozdat object bez etogo
    // ne mozhet bit null
    //optional = false — проверка на уровне Java/Hibernate
    //?? optoinal true
    // modelei mnogo mashina odna
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel carModel;
    @Column(name = "plate_number", unique = true)
    private String plateNumber;
    @Column(unique = true)
    private String vin;
    //???
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarStatus status;
//    @ManyToOne(optional = false)
//    @JoinColumn(name = "veheicle_id")
//    private VehicleClass vehicleClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

//    public VehicleClass getVehicleClass() {
//        return vehicleClass;
//    }
//
//    public void setVehicleClass(VehicleClass vehicleClass) {
//        this.vehicleClass = vehicleClass;
//    }
}
