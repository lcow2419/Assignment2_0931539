package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Blog;
import models.BlogPost;
import models.Person;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Load JSON files using ClassLoader
            InputStream personStream = Main.class.getClassLoader().getResourceAsStream("person.json");
            InputStream blogPostStream = Main.class.getClassLoader().getResourceAsStream("blogPosts.json");

            if (personStream == null || blogPostStream == null) {
                System.err.println("Error: One or both JSON files not found in resources folder.");
                return;
            }

            // Parse JSON into objects
            List<Person> persons = objectMapper.readValue(personStream, new TypeReference<List<Person>>() {});
            List<BlogPost> blogPosts = objectMapper.readValue(blogPostStream, new TypeReference<List<BlogPost>>() {});

            // Create Blog instance
            Blog blog = new Blog(blogPosts, persons);

            // Ask user for an age (Input validation added)
            Scanner scanner = new Scanner(System.in);
            int targetAge;
            while (true) {
                System.out.print("Enter an age to find blog posts: ");
                if (scanner.hasNextInt()) {
                    targetAge = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a valid integer age.");
                    scanner.next(); // Discard invalid input
                }
            }

            // Get and print blog posts by authors of the given age
            List<String> postsByAge = blog.getPostsByAuthorAge(targetAge);
            if (postsByAge.isEmpty()) {
                System.out.println("No blog posts found for authors aged " + targetAge);
            } else {
                System.out.println("Blog post IDs by authors aged " + targetAge + ": " + postsByAge);
            }

            // Print total number of blog posts and contributors
            System.out.println("Total Blog Posts: " + blogPosts.size());
            System.out.println("Total Contributors: " + persons.size());

        } catch (Exception e) {
            System.err.println("Error processing JSON files: " + e.getMessage());
        }
    }
}
