package models;

import lombok.Getter;
import lombok.Builder;
import lombok.Singular;
import com.fasterxml.jackson.annotation.JsonProperty;
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
}