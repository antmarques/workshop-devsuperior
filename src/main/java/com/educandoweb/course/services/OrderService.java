package com.educandoweb.course.services;

import com.educandoweb.course.entities.OrderEntity;
import com.educandoweb.course.entities.OrderItemEntity;
import com.educandoweb.course.entities.ProductEntity;
import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.ProductRepository;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<OrderEntity> findAll() {
        return repository.findAll();
    }

    public OrderEntity findById(Long id) {
        Optional<OrderEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<OrderEntity> findByProduct(Long id) {
        List<OrderEntity> retornoList = new ArrayList<>();
        List<OrderEntity> list = repository.findAll();
        Optional<ProductEntity> product = productRepository.findById(id);
        for (OrderItemEntity oie: product.get().getItems()) {
            if (oie.getId() != null) {
                for (OrderEntity oe: list) {
                    if (Objects.equals(oie.getOrder().getId(), oe.getId())) {
                        retornoList.add(oe);
                    }
                }
            }
        }
        if (retornoList.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return retornoList;
    }

    public List<OrderEntity> findByUser(Long id) {
        List<OrderEntity> retornoList = new ArrayList<>();
        List<OrderEntity> list = repository.findAll();
        Optional<UserEntity> user = userRepository.findById(id);
        for (OrderEntity oe: user.get().getOrders()) {
            if (oe.getId() != null) {
                for (OrderEntity order: list) {
                    if (oe.getId().equals(order.getId())) {
                        retornoList.add(oe);
                    }
                }
            }
        }
        if (retornoList.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return retornoList;
    }
}
