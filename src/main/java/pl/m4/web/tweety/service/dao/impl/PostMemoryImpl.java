package pl.m4.web.tweety.service.dao.impl;

import org.springframework.stereotype.Repository;
import pl.m4.web.tweety.service.dao.PostMemory;
import pl.m4.web.tweety.model.Post;
import pl.m4.web.tweety.model.Wall;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostMemoryImpl implements PostMemory {
    private static final int CAPACITY = 100;
    private Map<String, Wall> memory;

    public PostMemoryImpl() {
        memory = new ConcurrentHashMap<>(CAPACITY);
    }

    @Override
    public void addPost(String author, Post post) {
        Wall wall = findOrCreateWall(author);
        wall.addPost(post);
    }

    @Override
    public Optional<Wall> findWall(String author) {
        return Optional.ofNullable(memory.get(author));
    }

    private Wall createWall(String author) {
        Wall wall = new Wall(author);
        memory.put(author, wall);
        return wall;
    }

    private Wall findOrCreateWall(String author) {
        Optional<Wall> opWall = findWall(author);
        return opWall.orElseGet(() -> createWall(author));
    }
}
