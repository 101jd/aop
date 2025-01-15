package org.example.aop.controller;

import org.example.aop.domain.Comment;
import org.example.aop.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    Service service;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> hello(){
        List<Comment> comments = service.read();
        if (comments == null)
            comments = Collections.EMPTY_LIST;
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<Comment> comment (@RequestBody Comment comment){
        return ResponseEntity.ok(service.comment(comment));
    }
}
