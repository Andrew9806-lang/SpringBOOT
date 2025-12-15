package org.example.carsharing.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String adress;
    @Column(nullable = false)
    private Double latitube;
    @Column(nullable = false)
    private Double longtitube;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Double getLatitube() {
        return latitube;
    }

    public void setLatitube(Double latitube) {
        this.latitube = latitube;
    }

    public Double getLongtitube() {
        return longtitube;
    }

    public void setLongtitube(Double longtitube) {
        this.longtitube = longtitube;
    }
}
