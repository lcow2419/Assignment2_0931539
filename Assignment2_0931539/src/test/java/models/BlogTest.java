package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlogTest {
    private Blog blog;

    @BeforeEach
    void setUp() {
        // Sample Person Data
        Person person1 = new Person("P1", "Alice", "Smith", 25, "Female");
        Person person2 = new Person("P2", "Bob", "Jones", 30, "Male");
        Person person3 = new Person("P3", "Charlie", "Brown", 25, "Non-Binary");

        // Sample Blog Posts
        BlogPost post1 = new BlogPost("B1", "P1", "Post by Alice");
        BlogPost post2 = new BlogPost("B2", "P2", "Post by Bob");
        BlogPost post3 = new BlogPost("B3", "P3", "Post by Charlie");

        // Create Blog instance
        blog = new Blog(Arrays.asList(post1, post2, post3), Arrays.asList(person1, person2, person3));
    }

    @Test
    void testGetPostsByAuthorAge_ValidAge() {
        List<String> posts = blog.getPostsByAuthorAge(25);
        assertEquals(2, posts.size());
        assertTrue(posts.contains("B1"));
        assertTrue(posts.contains("B3"));
    }

    @Test
    void testGetPostsByAuthorAge_NoMatches() {
        List<String> posts = blog.getPostsByAuthorAge(40);
        assertTrue(posts.isEmpty());
    }

    @Test
    void testGetPostsByAuthorAge_EmptyData() {
        Blog emptyBlog = new Blog(Collections.emptyList(), Collections.emptyList());
        List<String> posts = emptyBlog.getPostsByAuthorAge(25);
        assertTrue(posts.isEmpty());
    }

    @Test
    void testGetPostsByAuthorAge_MissingAuthor() {
        // BlogPost with author ID that doesn't exist
        BlogPost orphanPost = new BlogPost("B4", "P99", "Orphan Post");
        Blog blogWithOrphan = new Blog(Arrays.asList(orphanPost), Arrays.asList(
                new Person("P1", "Alice", "Smith", 25, "Female")
        ));

        List<String> posts = blogWithOrphan.getPostsByAuthorAge(25);
        assertTrue(posts.isEmpty());
    }

    @Test
    void testGetPostsByAuthorAge_NullAge() {
        List<String> posts = blog.getPostsByAuthorAge(null);
        assertTrue(posts.isEmpty(), "Should return an empty list when age is null.");
    }

    @Test
    void testGetPostsByAuthorAge_NegativeAge() {
        List<String> posts = blog.getPostsByAuthorAge(-1);
        assertTrue(posts.isEmpty(), "Should return an empty list for negative ages.");
    }
}
