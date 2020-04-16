package com.fridgemanagement.repository;

import com.fridgemanagement.model.Fridge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FridgeRepository extends CrudRepository<Fridge, Integer> {

    @Override
    Optional<Fridge> findById(Integer i);

    @Override
    <S extends Fridge> S save(S s);

    @Override
    void delete(Fridge fridge);

    @Override
    Iterable<Fridge> findAllById(Iterable<Integer> var1);

}
