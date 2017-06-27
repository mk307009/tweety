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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    private static final String AUTHOR = "tommy";
    private static final String AUTHOR_2 = "bobby";
    private static final String AUTHOR_EMPTY_MSG = "empty_author";
    private static final String AUTHOR_MSG = "test message for tests";
    private static final String AUTHOR_2_MSG = "message from bob";
    private static final String ANOTHER_MSG = "another message for everyone";
    @Autowired
    private PostService postService;
    @Autowired
    private PostMemory postMemory;

    @Before
    public void init() {
        postMemory.addPost(AUTHOR, new Post(AUTHOR_MSG));
        postMemory.addPost(AUTHOR_2, new Post(AUTHOR_2_MSG));
    }

    @Test
    public void testAddPost() {
        postService.addPost(AUTHOR, new Post(ANOTHER_MSG));
        Assert.assertTrue(postService.getAuthorPosts(AUTHOR).size() == 2);
    }

    @Test
    public void testAddPostForEmptyWall() {
        postService.addPost(AUTHOR_EMPTY_MSG, new Post(ANOTHER_MSG));
        Assert.assertTrue(postService.getAuthorPosts(AUTHOR_EMPTY_MSG).size() == 1);
    }

    @Test
    public void testPostValueForUser() {
        Assert.assertTrue(postService.getAuthorPosts(AUTHOR_2).get(0).getMessage()
                .equals(AUTHOR_2_MSG));
    }
}
