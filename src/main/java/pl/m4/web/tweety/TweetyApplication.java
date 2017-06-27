package pl.m4.web.tweety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class TweetyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweetyApplication.class, args);
    }
}
