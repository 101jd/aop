package org.example.aop.services;

import org.example.aop.aspects.ToLog;
import org.example.aop.domain.Comment;
import org.example.aop.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@org.springframework.stereotype.Service
@Component
public class Service {

    @Autowired
    Repo repo;

    @ToLog
    public Comment comment(Comment comment){
        return repo.saveComment(comment);
    }

    public List<Comment> read(){
        return repo.readComments();
    }
}
