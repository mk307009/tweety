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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        if (!getMessage().equals(post.getMessage())) return false;
        return getDateTime().equals(post.getDateTime());
    }

    @Override
    public int hashCode() {
        int result = getMessage().hashCode();
        result = 31 * result + getDateTime().hashCode();
        return result;
    }
}
