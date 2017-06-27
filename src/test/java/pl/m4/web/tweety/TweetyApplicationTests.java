package pl.m4.web.tweety;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import pl.m4.web.tweety.controller.HomeController;
import pl.m4.web.tweety.controller.MessageController;
import pl.m4.web.tweety.service.dao.PostMemory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweetyApplicationTests {
    @Autowired
    private HomeController homeController;
    @Autowired
    private MessageController messageController;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostMemory postMemory;
    @LocalServerPort
    private int port;
    private String url;

    @Before
    public void init() {
        url = "http://localhost:" + port + "/";
    }

    @Test
    public void contextLoads() {
        Assertions.assertThat(homeController).isNotNull();
        Assertions.assertThat(messageController).isNotNull();
    }

    @Test
    public void testHomeRequestSayHello() {
        Assertions.assertThat(restTemplate.getForObject(url, String.class))
                .contains("Hello");
    }

    @Test
    public void testRequestAddPost() {
        Assertions.assertThat(restTemplate
                .postForEntity(url + "post/mati", generateHeaderForAddPost("Hello everyone"), String.class)
                .getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testRequestAddFollowing() {
        Assertions.assertThat(restTemplate.postForEntity(url + "follow/mati/tom", null, String.class)
                .getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testRequestRemoveFollowing() {
        Assertions.assertThat(restTemplate.postForEntity(url + "follow/mati/tom", null, String.class)
                .getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    public void testViewEmptyWall() {
        Assertions.assertThat(restTemplate.getForObject(url + "wall/tom", String.class))
                .contains("[]");
    }

    private HttpEntity<MultiValueMap<String, String>> generateHeaderForAddPost(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("message", message);
        return new HttpEntity<>(map, headers);
    }
}
