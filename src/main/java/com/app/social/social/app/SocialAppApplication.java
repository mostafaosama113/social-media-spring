package com.app.social.social.app;

import com.app.social.social.app.entity.Post;
import com.app.social.social.app.repositry.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SocialAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostRepository postRepository) {
		return args -> {
			for(int i = 0 ; i < 20 ; i++){
				Post post = new Post();
				post.setTitle("Title " + i);
				post.setContent("Content " + i);
				postRepository.save(post);
			}
		};
	}

}
