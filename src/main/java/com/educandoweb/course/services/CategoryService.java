package com.educandoweb.course.services;

import com.educandoweb.course.entities.CategoryEntity;
import com.educandoweb.course.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryEntity> findAll() {
        return repository.findAll();
    }

    public CategoryEntity findById(Long id) {
        Optional<CategoryEntity> ce = repository.findById(id);
        return ce.get();
    }

}
