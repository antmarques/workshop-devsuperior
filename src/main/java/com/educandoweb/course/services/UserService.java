package com.educandoweb.course.services;

import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> obj = repository.findById(id);
        return obj.get();
    }

    public UserEntity insert(UserEntity user) {
        return repository.save(user);
    }

}
