package com.educandoweb.course.resources;

import com.educandoweb.course.entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<UserEntity> findAll() {
        UserEntity ue = new UserEntity(1L, "Maria", "maria@gamil.com", "999999999", "123456");
        return ResponseEntity.ok().body(ue);
    }

}
