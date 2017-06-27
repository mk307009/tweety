package pl.m4.web.tweety.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.m4.web.tweety.model.Post;
import pl.m4.web.tweety.service.FollowService;
import pl.m4.web.tweety.service.PostService;

import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
public class MessageController {
    private static final int MSG_MAX_SIZE = 140;
    private static final String FOLLOWING_ADDED = "Following has been added correctly";
    private static final String FOLLOWING_REMOVED = "Following has been removed correctly";
    private static final String POST_ADDED = "Post has been added correctly";
    @Autowired
    private PostService postService;
    @Autowired
    private FollowService followService;

    /**
     * Request to shows all user posts
     * @param user - author nickname of posts
     * @return List of Post objects
     */
    @GetMapping("wall/{user}")
    public List<Post> getAuthorReverseChronologyPosts(@PathVariable String user) {
        return postService.getAuthorReverseChronologyPosts(user);
    }

    /**
     * Request to shows all following posts by user
     * @param user - author nickname of posts
     * @return list of posts
     */
    @GetMapping("timeline/{user}")
    public List<Post> getFollowReverseChronologyPosts(@PathVariable String user) {
        return postService.getFollowReverseChronologyPosts(user);
    }

    /**
     * Request to shows all following and user posts
     * @param user - author nickname of posts
     * @return list of posts
     */
    @GetMapping("board/{user}")
    public List<Post> getAllReverseChronologyPosts(@PathVariable String user) {
        return postService.getAllReverseChronologyPosts(user);
    }

    /**
     * Request for add new post for user
     * @param user author nickname of post
     * @param message text of post, max size is 140 chars
     */
    @PostMapping("post/{user}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = POST_ADDED)
    public void addPost(@PathVariable(value = "user") String user,
                          @Size(max= MSG_MAX_SIZE) @RequestParam(value = "message") String message) {
        postService.addPost(user, new Post(message));
    }

    /**
     * Request to add following option
     * @param who nickname of wall author
     * @param whom nickname of user which will be observed
     */
    @PostMapping("follow/{who}/{whom}")
    @ResponseStatus(value = HttpStatus.CREATED, reason = FOLLOWING_ADDED)
    public void addFollow(@PathVariable String who, @PathVariable String whom) {
        followService.addFollow(who, whom);
    }

    /**
     * Request to remove following option
     * @param who nickname of wall author
     * @param whom nickname of user which will be observed
     */
    @DeleteMapping("unfollow/{who}/{whom}")
    @ResponseStatus(value = HttpStatus.OK, reason = FOLLOWING_REMOVED)
    public void removeFollow(@PathVariable String who, @PathVariable String whom) {
        followService.removeFollow(who, whom);
    }
}
