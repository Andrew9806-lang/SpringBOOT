package org.example.carsharing.service;

import jakarta.transaction.Transactional;
import org.example.carsharing.api.dto.ReservationCreateRequest;
import org.example.carsharing.domain.*;
import org.example.carsharing.repository.CarRepository;
import org.example.carsharing.repository.ReservationRepository;
import org.example.carsharing.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
 * Сервис создания бронирований.
 * Проводит валидаторы бизнес‑правил: корректность интервала, существование пользователя/авто,
 * статус доступности автомобиля и отсутствие пересечений активных бронирований.
 * При успешном создании переводит автомобиль в статус RESERVED.
 */
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    /**
     * Репозиторий для работы с пользователями
     */
    private final UserRepository userRepository;

    /**
     * Репозиторий для работы с автомобилями
     */
    private final CarRepository carRepository;

    /**
     * Конструктор сервиса бронирований.
     *
     * @param reservationRepository репозиторий для работы с бронированиями
     * @param userRepository        репозиторий для работы с пользователями
     * @param carRepository         репозиторий для работы с автомобилями
     * @throws IllegalArgumentException если любой из аргументов равен null
     */


    public ReservationService(
            ReservationRepository reservationRepository,
            UserRepository userRepository,
            CarRepository carRepository
    ) {
        if (reservationRepository == null || userRepository == null || carRepository == null) {
            throw new IllegalArgumentException("Repositories cannot be null");
        }
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    /*
      Делает метод транзакционным.
  Всё, что изменяется в базе данных в этом методе, будет в одной транзакции.
  Если произойдёт ошибка, изменения откатываются
       */
    // metod creazte sozdaet bronirovanie dto s dannimi
    @Transactional
    public Reservation create(ReservationCreateRequest request) {
        //Проверяем, что интервал времени корректный (например, start < end, и start в будущем)
        if (!request.isTimeRangeValid()) {
            // ne mozhet obrabotan bit zapros nevernie dannie
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "Invalid time range");
        }
        // ishem usera
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "car not found"));
        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "car is not avaible");
        }
        /// proveryaet est li bronirovaniya u avto
        /// HTTP статус 422 Unprocessable Entity.
        /// Значит: сервер понял запрос,
        //но не может его обработать, потому что данные некорректны (в нашем случае — автомобиль уже занят).
        Long overLaps = reservationRepository.countOverLaps(car.getId(), request.getStartAT(), request.getEndAT());
        if (overLaps > 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "car has overlapping reservation");
        }
        Reservation reservation = new Reservation();
        // sozdaem novuyu bron
        reservation.setUser(user);
        //user kotoriy broniruet
        reservation.setCar(car);
        // mashinu kotoruyu bronirovali
        reservation.setStartAt(request.getStartAT());
        //vremya nachalo
        reservation.setEndAt(request.getEndAT());
        // konec vremeni bronirovaniya
        reservation.setStatus(ReservationStatus.ACTIVE);
        //menyaem na active
        Reservation savedReservation = reservationRepository.save(reservation);
        //sohranyaet bronirovaniya v reservation
        carRepository.save(car);
        // sohranyaet mashinu i broniruet 
        return savedReservation;
    }


    /**
     * Создаёт новое бронирование автомобиля.
     *
     * @param request DTO с данными для создания бронирования
     * @return созданное бронирование
     * @throws IllegalArgumentException если запрос на бронирование невалиден
     * @throws EntityNotFoundException если пользователь или автомобиль не найдены
     * @throws IllegalStateException если автомобиль уже забронирован на указанный период
     */
    // TODO: Реализовать метод создания бронирования

    /**
     * Отменяет существующее бронирование по идентификатору.
     *
     * @param reservationId идентификатор бронирования для отмены
     * @param userId        идентификатор пользователя, отменяющего бронирование
     * @throws IllegalStateException если бронирование уже отменено или завершено
     */
    // TODO: Реализовать метод отмены бронирования
    @Transactional
    public Reservation cansel(Long reservationId, Long userId) {
        // poisk bronirovaniya
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "reservation not found"));
        // proverka chto imenno etot user broniroval
        if (!reservation.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "you cannot canseled ");
        }
        //proveryaem activen li
        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_CONTENT, "reservation is not Active ");
        }
        // eslo proshla vse proverki udalyaem
        reservation.setStatus(ReservationStatus.CANCELLED);
        // menyaem mashinu na dostupnost
        Car car = reservation.getCar();
        car.setStatus(CarStatus.AVAILABLE);
        return reservation;
    }
    /**
     * Получает список активных бронирований пользователя.
     *
     * @param userId идентификатор пользователя
     * @return список активных бронирований
     * @throws IllegalArgumentException если userId равен null
     */
    // TODO: Реализовать метод получения активных бронирований
//    @Transactional
//    public Reservation getAllReservation(Long userId){
//    }


}
