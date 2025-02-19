package models;

import lombok.Getter;
import lombok.Builder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a collection of blog posts and contributors.
 */
@Getter
@Builder
public class Blog {
    private final List<BlogPost> posts;
    private final List<Person> contributors;

    /**
     * Constructor to initialize the Blog with posts and contributors.
     *
     * @param posts        List of blog posts.
     * @param contributors List of people contributing to the blog.
     */
    public Blog(List<BlogPost> posts, List<Person> contributors) {
        this.posts = posts;
        this.contributors = contributors;
    }

    /**
     * Retrieves blog post IDs where the author's age matches the input.
     * Uses Java Streams (no loops).
     *
     * @param nAge Age of the author.
     * @return List of blog post IDs by authors with the given age.
     */
    public List<String> getPostsByAuthorAge(Integer nAge) {
        return contributors.stream()
                .filter(person -> person.getNAge().equals(nAge))
                .flatMap(person -> posts.stream().filter(post -> post.getSAuthorId().equals(person.getSId())))
                .map(BlogPost::getSId)
                .collect(Collectors.toList());
    }

    /**
     * Reads a JSON file and converts it into a list of objects.
     */
    public static <T> List<T> readJsonFile(String filePath, Class<T[]> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return List.of(objectMapper.readValue(new File(filePath), clazz));
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            return List.of();
        }
    }
}
