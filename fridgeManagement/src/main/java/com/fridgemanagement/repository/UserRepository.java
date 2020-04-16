package com.fridgemanagement.repository;

import com.fridgemanagement.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    @Override
    Optional<User> findById(String string);

    @Override
    <S extends User> S save(S s);

    @Override
    void delete(User user);
}
