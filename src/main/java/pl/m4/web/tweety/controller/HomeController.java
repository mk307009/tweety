package pl.m4.web.tweety.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String hello() {
        return "Hello </br>How to use api: </br>" +
                "* To add new post for user send HTTP POST request e.g.: </br>" +
                "localhost:8080/post/{username} with body parameter 'message' as a key and some text as value </br>" +
                "if user's wall does not exist it will be created</br>" +
                "* To show user messages (posts) send HTTP GET request e.g.:</br>" +
                "localhost:8080/wall/{username}</br>" +
                "* To show user following messages send HTTP GET request e.g.:</br>" +
                "localhost:8080/timeline/{username}</br>" +
                "* To show all messages observed by user send HTTP GET request e.g.:</br>" +
                "localhost:8080/board/{username}</br>" +
                "* To add following user posts send HTTP POST request e.g.:</br>" +
                "localhost:8080/follow/{who}/{whom}</br>" +
                "where who - is nickname of observer user</br>" +
                "and whom - is nickname of observed user</br>" +
                "* To remove following user posts send HTTP DELETE request e.g.:</br>" +
                "localhost:8080/unfollow/{who}/{whom}</br>" +
                "with the same parameters as are for follow method.";
    }
}