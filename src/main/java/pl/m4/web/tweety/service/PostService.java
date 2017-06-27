package pl.m4.web.tweety.service;

import pl.m4.web.tweety.model.Post;

import java.util.List;

public interface PostService {
    void addPost(String author, Post post);

    List<Post> getAuthorPosts(String author);

    List<Post> getAuthorReverseChronologyPosts(String author);

    List<Post> getFollowPosts(String author);

    List<Post> getFollowReverseChronologyPosts(String author);

    List<Post> getAllReverseChronologyPosts(String author);
}
