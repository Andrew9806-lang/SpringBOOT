package org.example.carsharing.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_name")
public class VehicleClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(nullable = false,unique = true)
    private int code;
    @Column(nullable = false)
    private String name;
    @ManyToOne(optional = false)
    //class_id ??
    @JoinColumn(name = "class_id",nullable = false)
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
