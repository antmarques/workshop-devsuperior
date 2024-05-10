package com.educandoweb.course.services;

import com.educandoweb.course.entities.CategoryEntity;
import com.educandoweb.course.entities.OrderEntity;
import com.educandoweb.course.entities.UserEntity;
import com.educandoweb.course.repositories.CategoryRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        return ce.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public CategoryEntity insert(CategoryEntity category) {
        return repository.save(category);
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

    public CategoryEntity update(Long id, CategoryEntity obj) {
        try {
            CategoryEntity category = repository.getReferenceById(id);
            updateData(category, obj);
            return repository.save(category);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(CategoryEntity category, CategoryEntity obj) {
        category.setName(obj.getName());
    }

}
