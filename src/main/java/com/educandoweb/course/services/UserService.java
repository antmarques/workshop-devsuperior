package com.educandoweb.course.services;

import com.educandoweb.course.entities.OrderEntity;
import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    public UserEntity findById(Long id) {
        Optional<UserEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public UserEntity findByOrder(Long id) {
        List<UserEntity> list = repository.findAll();
        Optional<OrderEntity> order = orderRepository.findById(id);
        for (UserEntity ue: list) {
            if (ue.getId() != null) {
                for (OrderEntity oe: ue.getOrders()) {
                    if (Objects.equals(oe.getId(), order.get().getId())) {
                        return ue;
                    }
                }
            }
        }
        throw new ResourceNotFoundException(id);
    }

    public UserEntity insert(UserEntity user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public UserEntity update(Long id, UserEntity obj) {
        try {
            UserEntity user = repository.getReferenceById(id);
            updateData(user, obj);
            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(UserEntity user, UserEntity obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPhone(obj.getPhone());
    }
}
