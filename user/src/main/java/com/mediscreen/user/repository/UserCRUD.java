package com.mediscreen.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserCRUD extends MongoRepository<Object, Long> {

    public Optional<Object> findUserByLogin(String login);

}
