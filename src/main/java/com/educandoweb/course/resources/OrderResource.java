package com.educandoweb.course.resources;

import com.educandoweb.course.entities.OrderEntity;
import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.services.OrderService;
import com.educandoweb.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders/")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> findAll() {
        List<OrderEntity> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderEntity> findById(@PathVariable Long id) {
        OrderEntity user = service.findById(id);
        return ResponseEntity.ok().body(user);
    }

}
