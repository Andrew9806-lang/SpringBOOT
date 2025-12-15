package org.example.carsharing.repository;

import org.example.carsharing.domain.Car;
import org.example.carsharing.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
Repository interface  dostupa k modelyam avtomobilei
<CarModel, Long> — дженерики:
CarModel — тип сущности, с которой работает репозиторий
Long — тип первичного ключа (id) этой
 */
public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    /*
    JpaRepository — это готовый набор методов для работы с БД.
    save()
findById()
findAll()
deleteById()
count()
existsById()

     */

}
