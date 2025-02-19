package main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Blog;
import models.BlogPost;
import models.Person;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Main application that reads JSON files and interacts with Blog.
 */
public class BlogApplication {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Read JSON files into lists of objects
            List<Person> contributors = objectMapper.readValue(new File("src/main/resources/person.json"), new TypeReference<>() {});
            List<BlogPost> posts = objectMapper.readValue(new File("src/main/resources/blogPosts.json"), new TypeReference<>() {});

            // Create Blog instance
            Blog blog = new Blog(posts, contributors);

            // Get posts by authors aged 22
            Integer nAge = 22;
            List<String> postsByAge = blog.getPostsByAuthorAge(nAge);
            System.out.println("Posts by authors aged " + nAge + ": " + postsByAge);

            // Print blog summary
            System.out.println("Total Blog Posts: " + blog.getPosts().size());
            System.out.println("Total Contributors: " + blog.getContributors().size());

        } catch (IOException e) {
            System.out.println("Error reading JSON files: " + e.getMessage());
        }
    }
}
