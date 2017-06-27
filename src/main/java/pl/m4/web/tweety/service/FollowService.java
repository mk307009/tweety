package pl.m4.web.tweety.service;

public interface FollowService {
    void addFollow(String observer, String author);

    void removeFollow(String observer, String author);
}
