package org.example.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.aop.domain.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class FilterAspect {

    private final List<String> BAD_WORDS = List.of(
            "хуй", "хуе", "хуё", "пизд", "еба", "еби", "ебу", "fuck", "shit", "цензур"
    );

    @Around("execution(* org.example.aop.services.Service.comment(..))")
    Object filter(ProceedingJoinPoint joinPoint) throws Throwable {
        Comment c = (Comment) joinPoint.getArgs()[0];
        String name = c.getAuthor();
        String text = c.getText();

        for (String s : BAD_WORDS){
            if (name.toLowerCase().contains(s) || text.toLowerCase().contains(s)){
                name = "BANNED";
                text = "USER WAS BANNED FOR THIS POST";
            }
        }
        Comment comment = new Comment();
        comment.setAuthor(name);
        comment.setText(text);
        Object returned = joinPoint.proceed(new Object[]{comment});
        return returned;
    }
}
