package com.educandoweb.course.resources;

import com.educandoweb.course.entities.ProductEntity;
import com.educandoweb.course.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<ProductEntity> insert(@RequestBody ProductEntity product) {
        product = service.insert(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable Long id, @RequestBody ProductEntity product) {
        product = service.update(id, product);
        return ResponseEntity.ok().body(product);
    }
}
