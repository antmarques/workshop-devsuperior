package com.educandoweb.course.resources;

import com.educandoweb.course.entities.ProductEntity;
import com.educandoweb.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products/")
public class ProductResource {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> findAll() {
        List<ProductEntity> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> findById(@PathVariable Long id) {
        ProductEntity pe = service.findById(id);
        return ResponseEntity.ok().body(pe);
    }
}
