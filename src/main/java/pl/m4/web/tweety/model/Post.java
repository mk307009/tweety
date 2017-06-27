package pl.m4.web.tweety.model;

import java.time.LocalDateTime;

public class Post {
    private final String message;
    private final LocalDateTime dateTime;

    public Post(String message) {
        this.message = message;
        dateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
