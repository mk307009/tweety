package pl.m4.web.tweety.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.m4.web.tweety.model.Post;
import pl.m4.web.tweety.service.dao.PostMemory;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowServiceTest {
    private static final String AUTHOR = "tom";
    private static final String AUTHOR_2 = "bob";
    private static final String OBSERVER = "greg";
    private static final String EMPTY_AUTHOR = "empty";
    private static final String AUTHOR_MSG = "test message for tests";
    private static final String AUTHOR_2_MSG = "message from bob";
    @Autowired
    private FollowService followService;
    @Autowired
    private PostMemory postMemory;

    @Before
    public void init() {
        postMemory.addPost(AUTHOR, new Post(AUTHOR_MSG));
        postMemory.addPost(AUTHOR_2, new Post(AUTHOR_2_MSG));
        //for creating Wall
        postMemory.addPost(OBSERVER, null);
    }

    @Test
    public void testAddFollow() {
        followService.addFollow(OBSERVER, AUTHOR);
        Assert.assertTrue(postMemory.findWall(OBSERVER).get().getFollows().size() == 1);
        followService.removeFollow(OBSERVER, AUTHOR);
    }

    @Test
    public void testEmptyFollow() {
        postMemory.addPost(EMPTY_AUTHOR, null);
        Assert.assertTrue(postMemory.findWall(EMPTY_AUTHOR).get().getFollows().isEmpty());
    }

    @Test
    public void testFollowMessageIsCorrect() {
        followService.addFollow(OBSERVER, AUTHOR);
        List<Post> posts = postMemory.findWall(OBSERVER).get().getFollows().get(AUTHOR).getPosts();
        Assert.assertTrue(posts.get(0).getMessage().equals(AUTHOR_MSG));
        followService.removeFollow(OBSERVER, AUTHOR);
    }

    @Test
    public void testFollowMessageAuthor() {
        followService.addFollow(OBSERVER, AUTHOR);
        String author = postMemory.findWall(OBSERVER).get().getFollows().get(AUTHOR).getAuthor();
        Assert.assertTrue(author.equals(AUTHOR));
        followService.removeFollow(OBSERVER, AUTHOR);
    }

    @Test
    public void testAddTwoFollows() {
        followService.addFollow(OBSERVER, AUTHOR);
        followService.addFollow(OBSERVER, AUTHOR_2);
        Assert.assertTrue(postMemory.findWall(OBSERVER).get().getFollows().size() == 2);
        followService.removeFollow(OBSERVER, AUTHOR);
        followService.removeFollow(OBSERVER, AUTHOR_2);
    }
}
