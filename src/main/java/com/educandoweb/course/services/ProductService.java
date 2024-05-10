package com.educandoweb.course.services;

import com.educandoweb.course.entities.CategoryEntity;
import com.educandoweb.course.entities.ProductEntity;
import com.educandoweb.course.repositories.ProductRepository;
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
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductEntity> findAll() {
        return repository.findAll();
    }

    public ProductEntity findById(Long id) {
        Optional<ProductEntity> pe = repository.findById(id);
        return pe.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public ProductEntity insert(ProductEntity product) {
        return repository.save(product);
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

    public ProductEntity update(Long id, ProductEntity obj) {
        try {
            ProductEntity product = repository.getReferenceById(id);
            updateData(obj, product);
            return repository.save(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(ProductEntity obj, ProductEntity product) {
        product.setName(obj.getName());
        product.setDescription(obj.getDescription());
        product.setPrice(obj.getPrice());
        product.setImgUrl(obj.getImgUrl());
        product.setCategories(obj.getCategories());
    }
}
