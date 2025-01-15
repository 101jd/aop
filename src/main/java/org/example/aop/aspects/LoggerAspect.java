package org.example.aop.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggerAspect {
    Logger logger = Logger.getLogger("log");

    @AfterReturning(value = "@annotation(ToLog)", returning = "returned")
    public void logReturned(Object returned){
        logger.info("comment posted: " + returned.toString());
    }

    @AfterThrowing(value = "@annotation(ToLog)", throwing = "e")
    public void logError(Exception e){
        logger.warning("Something went wrong: " + e.getMessage());
    }
}
