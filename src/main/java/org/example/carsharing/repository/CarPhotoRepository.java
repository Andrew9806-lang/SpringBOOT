package org.example.carsharing.repository;

import org.example.carsharing.domain.CarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarPhotoRepository extends JpaRepository<CarPhoto,Long> {
    List<CarPhoto> findByCar_IdOrderByCreatedAtDesc(Long carId);
}

