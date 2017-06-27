package pl.m4.web.tweety.service.dao;

import pl.m4.web.tweety.model.Post;
import pl.m4.web.tweety.model.Wall;

import java.util.Optional;

public interface PostMemory {

    Optional<Wall> findWall(String author);

    void addPost(String author, Post post);

}
