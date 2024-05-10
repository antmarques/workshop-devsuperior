package com.educandoweb.course.resources;

import com.educandoweb.course.entities.CategoryEntity;
import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories/")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> findAll(){
        List<CategoryEntity> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryEntity> findById(@PathVariable Long id){
        CategoryEntity ce = service.findById(id);
        return ResponseEntity.ok().body(ce);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> insert(@RequestBody CategoryEntity category) {
        category = service.insert(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable Long id, @RequestBody CategoryEntity category) {
        category = service.update(id, category);
        return ResponseEntity.ok().body(category);
    }

}
