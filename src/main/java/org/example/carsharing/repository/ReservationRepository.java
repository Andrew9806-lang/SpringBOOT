package org.example.carsharing.repository;

import org.example.carsharing.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//ETOT METOD SRAVNIVAET BRINOROVANIYA NE ZABRANROVANA LI MASHINA

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    /// Query eto pishem zapros v bd vruchnuyu
    /// vibrat schetchik kotoriy schitaet broni
    /// gde masha zabronirovannaya sotvestvuet id i status active no nado dobavit ssilku
    /// i chto vremya menshe konca i konec bolshe starta
    /// Проверяет пересечение временного интервала:
    /// r.startAt < :endAt → бронирование начинается раньше конца желаемого интервала.
    /// r.endAt > :startAT → бронирование заканчивается позже начала желаемого интервала.
    @Query("select count(r) from Reservation r " +
            "where r.car.id = :carId and r.status = org.example.carsharing.domain.ReservationStatus.ACTIVE " +
            "and  r.startAt < :endAt and r.endAt > :startAT")
    // kolichestvo bronirovaniy
    // long potamuchto count v java trebuet long v zaprose
    long countOverLaps(
            /// bez param spring ne znaet imena
            /// svzayivaet parametr metoda s imenem zaprosa
            @Param("carId") Long carId,
            @Param("startAt") java.time.Instant startAT,
            @Param("endAt") java.time.Instant endAt
    );

}
