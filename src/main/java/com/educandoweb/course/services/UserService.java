package com.educandoweb.course.services;

import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.apache.catalina.User;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public UserEntity insert(UserEntity user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UserEntity update(Long id, UserEntity obj) {
        UserEntity user = repository.getReferenceById(id);
        updateData(user, obj);
        return repository.save(user);
    }

    private void updateData(UserEntity user, UserEntity obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPhone(obj.getPhone());
    }

}
