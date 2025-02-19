package models;

import models.BlogPost;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BlogPostTest {

    @Test
    void shouldCreateBlogPostSuccessfully() {
        BlogPost blogPost = new BlogPost("B001", "P123", "This is my first blog post.");

        assertThat(blogPost.getSId()).isEqualTo("B001");
        assertThat(blogPost.getSAuthorId()).isEqualTo("P123");
        assertThat(blogPost.getSPostContent()).isEqualTo("This is my first blog post.");
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> new BlogPost(null, "P123", "Content"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Invalid BlogPost details provided.");
    }

    @Test
    void shouldThrowExceptionWhenAuthorIdIsNull() {
        assertThatThrownBy(() -> new BlogPost("B002", null, "Content"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Invalid BlogPost details provided.");
    }
}
