package pl.m4.web.tweety.controller.handler;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class TweetyExceptionHandler {
    private static final String MESSAGE_TOO_LONG = "Message too long, max size is 140 chars.";

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = MESSAGE_TOO_LONG)
    public void messageTooLongExceptionHandler(HttpServletRequest request) {
        request.setAttribute(DefaultErrorAttributes.class.getName() + ".ERROR", null);
    }
}
