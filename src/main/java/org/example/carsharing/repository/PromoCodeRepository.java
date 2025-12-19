package org.example.carsharing.repository;

import org.example.carsharing.domain.PromoCode;
import org.example.carsharing.domain.PromoCodeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

/*
repositroy Sprong Data JPA:metodi sozdayutsya po imnei
provaryaem na unikalnot kod na moment vremeni
 */
public interface PromoCodeRepository extends JpaRepository<PromoCode,Long> {
//    public boolean existByCode(String code);
//    public List<PromoCode> findAllByStatusAndStartBeforeAndEndAfter(PromoCodeStatus status, Instant startAT,Instant startEnd);

}
