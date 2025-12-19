package org.example.carsharing.service;

import org.example.carsharing.domain.*;
import org.example.carsharing.repository.CarRepository;
import org.example.carsharing.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


/**
 * Набор модульных тестов для класса {@link CarService}.
 * <p>
 * В тестах используется фреймворк Mockito для создания мок-объектов (заглушек)
 * для зависимостей, таких как репозитории. Это позволяет изолировать
 * тестируемый сервис от реальной базы данных и других внешних систем,
 * обеспечивая предсказуемость и скорость выполнения тестов.
 */
@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    // Мок-объект репозитория автомобилей. Имитирует доступ к данным об автомобилях.
    private CarRepository carRepository;

    // Мок-объект репозитория бронирований. Имитирует доступ к данным о бронированиях.
    private ReservationRepository reservationRepository;

    // Экземпляр CarService, который является объектом тестирования.
    // В него будут внедрены мок-репозитории.
    private CarService carService;

    /**
     * Вспомогательный метод для создания экземпляра {@link Car} в целях тестирования.
     * <p>
     * Этот метод инкапсулирует логику создания полностью готового объекта Car
     * со всеми необходимыми связанными сущностями (CarModel, Location),
     * что делает тестовые методы более чистыми и читаемыми.
     *
     * @param id         Уникальный идентификатор автомобиля.
     * @param model      Название модели (в основном используется для наглядности).
     * @param seats      Количество посадочных мест.
     * @param locationId Идентификатор местоположения автомобиля.
     * @param status     Текущий статус автомобиля (например, AVAILABLE, MAINTENANCE).
     * @return Новый, полностью инициализированный объект {@link Car}.
     */
    private Car car(Long id, String model, Integer seats, Long locationId, CarStatus status) {
        // Создание и настройка связанной сущности CarModel (модель автомобиля).
        CarModel carModel = new CarModel();
        carModel.setId(100L + id); // Генерируем ID модели, чтобы избежать дублирования.
        carModel.setBrand("Brand" + id);
        carModel.setNameModels("Model" + id);
        carModel.setSeats(seats);
        carModel.setTransmission(Transmission.AUTOMATIC);
        carModel.setFuelType(FuelType.DIESEL);

        // Создание и настройка связанной сущности Location (местоположение).
        Location location = new Location();
        location.setID(locationId);
        location.setName("Loc" + locationId);
        location.setAddress("Address");
        location.setLatitude(1.0);
        location.setLongitude(2.0);

        // Создание основного объекта Car и установка всех его полей.
        Car car = new Car();
        car.setId(id);
        car.setCarModel(carModel);
        car.setPlateNumber("P" + id); // Генерируем номерной знак.
        car.setStatus(status);
        car.setLocation(location);
        car.setVin("V" + id); // Генерируем VIN.

        return car;
    }

    /**
     * Метод, выполняемый перед каждым тестом (аннотация {@code @BeforeEach}).
     * <p>
     * Основная задача - подготовка "чистого" тестового окружения для каждого теста.
     * Это гарантирует, что тесты не влияют друг на друга.
     */
    @BeforeEach
    void setup() {
        // Создаем мок-объекты для интерфейсов репозиториев.
        carRepository = Mockito.mock(CarRepository.class);
        reservationRepository = Mockito.mock(ReservationRepository.class);
        // Инициализируем тестируемый сервис, передавая ему моки в качестве зависимостей.
        carService = new CarService(carRepository, reservationRepository);
    }


    /**
     * Тестирует основной сценарий работы метода {@link CarService#avaible(Long, Instant, Instant, Integer)}.
     * <p>
     * <b>Проверяемый сценарий:</b>
     * <ol>
     *   <li>Метод должен правильно отфильтровывать автомобили по статусу (только {@code AVAILABLE}).</li>
     *   <li>Метод должен правильно отфильтровывать автомобили по местоположению ({@code locationId}).</li>
     *   <li>Метод должен правильно отфильтровывать автомобили по количеству мест (не меньше заданного).</li>
     *   <li>Метод должен исключать автомобили, у которых есть пересекающиеся по времени бронирования.</li>
     * </ol>
     */
    @Test
    public void available_filtersByStatusLocationSeats_andExcludesOverlaps() {
        // --- Этап 1: Подготовка данных и настройка моков (Arrange) ---

        // Создаем тестовый набор автомобилей. Этот список будет возвращаться моком carRepository.
        // Он включает автомобили, которые должны и не должны попасть в итоговый результат.
        List<Car> allCarsInDb = Arrays.asList(
                // Эти два автомобиля должны пройти все проверки
                car(1L, "Model", 5, 1L, CarStatus.AVAILABLE),
                car(2L, "Model", 6, 1L, CarStatus.AVAILABLE),

                // Этот автомобиль будет отфильтрован из-за пересечения бронирования
                car(3L, "Model", 5, 1L, CarStatus.AVAILABLE),

                // Этот автомобиль будет отфильтрован по статусу
                car(4L, "Model", 5, 1L, CarStatus.MAINTENANCE),

                // Этот автомобиль будет отфильтрован по местоположению (locationId = 2, а ищем 1)
                car(5L, "Model", 5, 2L, CarStatus.AVAILABLE),

                // Этот автомобиль будет отфильтрован по количеству мест (4, а ищем >= 5)
                car(6L, "Model", 4, 1L, CarStatus.AVAILABLE)
        );

        // Настраиваем поведение мока carRepository: при вызове метода findAll()
        // он должен вернуть наш заранее подготовленный список автомобилей.
        when(carRepository.findAll()).thenReturn(allCarsInDb);

        // Настраиваем поведение мока reservationRepository для проверки пересечений.
        // Для автомобилей с ID 1 и 2 пересечений нет (метод countOverlaps вернет 0).
        when(reservationRepository.countOverLaps(eq(1L), any(Instant.class), any(Instant.class))).thenReturn(0L);
        when(reservationRepository.countOverLaps(eq(2L), any(Instant.class), any(Instant.class))).thenReturn(0L);
        // Для автомобиля с ID 3 имитируем наличие пересекающегося бронирования (метод вернет 1).
        when(reservationRepository.countOverLaps(eq(3L), any(Instant.class), any(Instant.class))).thenReturn(1L);
        // Для остальных автомобилей (4, 5, 6) мок не настроен, и Mockito по умолчанию вернет 0,
        // но они все равно будут отфильтрованы по другим критериям.

        // Определяем временной интервал для поиска доступных автомобилей.
        Instant start = Instant.now().plusSeconds(3600);
        Instant end = start.plusSeconds(3600);

        // --- Этап 2: Выполнение тестируемого действия (Act) ---

        // Вызываем метод, который мы хотим протестировать, с заданными параметрами:
        // ищем в местоположении с ID=1, автомобили с количеством мест не менее 5.
        List<Car> result = carService.avaible(1L, start, end, 5);

        // --- Этап 3: Проверка результата (Assert) ---

        // Проверяем, что результат соответствует нашим ожиданиям.
        // В итоговом списке должны быть только автомобили с ID 1 и 2.
        assertThat(result)
                .extracting(Car::getId) // Извлекаем ID из каждого объекта Car в результирующем списке.
                .containsExactlyInAnyOrder(1L, 2L); // Утверждаем, что список ID содержит только 1 и 2 (порядок не важен).
    }
}

