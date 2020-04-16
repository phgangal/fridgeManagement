package com.fridgemanagement.repository;

import com.fridgemanagement.model.Soda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SodaRepository extends CrudRepository<Soda, Integer> {
    @Override
    Iterable<Soda> findAllById(Iterable<Integer> var1);

    @Override
    Optional<Soda> findById(Integer integer);

    @Override
    <S extends Soda> S save(S s);

    @Override
    void delete(Soda soda);

}
