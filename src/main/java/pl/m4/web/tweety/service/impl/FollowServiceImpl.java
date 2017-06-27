package pl.m4.web.tweety.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.m4.web.tweety.model.Wall;
import pl.m4.web.tweety.service.FollowService;
import pl.m4.web.tweety.service.dao.PostMemory;

import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private PostMemory postMemory;

    @Override
    public void addFollow(String observer, String author) {
        Optional<Wall> mainWall = postMemory.findWall(observer);
        Optional<Wall> followingWall = postMemory.findWall(author);
        followingWall.ifPresent(wall -> mainWall
                .ifPresent(board -> board.addFollow(wall)));
    }

    @Override
    public void removeFollow(String observer, String author) {
        Optional<Wall> wall = postMemory.findWall(observer);
        wall.ifPresent(board -> board.removeFollow(author));
    }


}
