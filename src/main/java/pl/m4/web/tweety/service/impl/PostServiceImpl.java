package pl.m4.web.tweety.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.m4.web.tweety.model.Post;
import pl.m4.web.tweety.model.Wall;
import pl.m4.web.tweety.service.PostService;
import pl.m4.web.tweety.service.dao.PostMemory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMemory postMemory;

    @Override
    public void addPost(String author, Post post) {
        postMemory.addPost(author, post);
    }

    @Override
    public List<Post> getAuthorPosts(String author) {
        Optional<Wall> wall = postMemory.findWall(author);
        return wall.orElse(new Wall(author)).getPosts();
    }

    @Override
    public List<Post> getAuthorReverseChronologyPosts(String author) {
        return getAuthorPosts(author).stream()
                .sorted(Comparator.comparing(Post::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getFollowPosts(String author) {
        List<Post> posts = new ArrayList<>();
        Optional<Wall> opWall = postMemory.findWall(author);
        opWall.ifPresent(w -> w.getFollows()
                .forEach((s, wall) -> posts.addAll(wall.getPosts())));
        return posts;
    }

    @Override
    public List<Post> getFollowReverseChronologyPosts(String author) {
        return getFollowPosts(author).stream()
                .sorted(Comparator.comparing(Post::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> getAllReverseChronologyPosts(String author) {
        List<Post> posts = new ArrayList<>();
        posts.addAll(getAuthorPosts(author));
        posts.addAll(getFollowPosts(author));

        return posts.stream()
                .sorted(Comparator.comparing(Post::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}