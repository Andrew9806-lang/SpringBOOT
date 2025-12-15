package org.example.carsharing.repository;

import org.example.carsharing.domain.Car;
import org.example.carsharing.domain.CarModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ModelCarRepo implements CarModelRepository{
    @Override
    public void flush() {

    }

    @Override
    public <S extends CarModel> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends CarModel> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<CarModel> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public CarModel getOne(Long aLong) {
        return null;
    }

    @Override
    public CarModel getById(Long aLong) {
        return null;
    }

    @Override
    public CarModel getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends CarModel> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends CarModel> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends CarModel> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends CarModel> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends CarModel> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends CarModel> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends CarModel, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends CarModel> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CarModel> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<CarModel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<CarModel> findAll() {
        return List.of();
    }

    @Override
    public List<CarModel> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CarModel entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CarModel> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<CarModel> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<CarModel> findAll(Pageable pageable) {
        return null;
    }



}
