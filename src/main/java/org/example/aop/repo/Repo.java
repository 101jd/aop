package org.example.aop.repo;

import org.example.aop.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Repo {

    private final JdbcTemplate template;

    public Repo(JdbcTemplate template) {
        this.template = template;
    }

    public Comment saveComment (Comment comment){
        template.update("INSERT INTO comments (author, text) VALUES (?, ?)", comment.getAuthor(), comment.getText());
        return comment;
    }

    public List<Comment> readComments(){

        RowMapper<Comment> mapper = (rs, rowNum) -> {
            Comment comment = new Comment();
            comment.setId(rs.getInt("id"));
            comment.setAuthor(rs.getString("author"));
            comment.setText(rs.getString("text"));

            return comment;
        };
        return template.query("SELECT * FROM comments", mapper);
    }
}
