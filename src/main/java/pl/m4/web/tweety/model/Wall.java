package pl.m4.web.tweety.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Wall {
    private final String author;
    private List<Post> posts;
    private Map<String, Wall> following;

    public Wall(String author) {
        this.author = author;
        posts = Collections.synchronizedList(new ArrayList<Post>());
        following = new ConcurrentHashMap<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public String getAuthor() {
        return author;
    }

    public Map<String, Wall> getFollows() {
        return following;
    }

    public void addFollow(Wall follow) {
        following.put(follow.getAuthor(), follow);
    }

    public void removeFollow(String author) {
        following.remove(author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;

        Wall wall = (Wall) o;

        if (!getAuthor().equals(wall.getAuthor())) return false;
        if (getPosts() != null ? !getPosts().equals(wall.getPosts()) : wall.getPosts() != null) return false;
        return following != null ? following.equals(wall.following) : wall.following == null;
    }

    @Override
    public int hashCode() {
        return getAuthor().hashCode();
    }
}
