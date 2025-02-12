package models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import exceptions.InvalidInputException;

/**
 * Represents a blog post written by a person.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Jacksonized
public class BlogPost {
    private final String sId;
    private final String sAuthorId;
    private final String sPostContent;

    /**
     * Constructor for BlogPost with validation.
     *
     * @param sId         Unique ID of the blog post.
     * @param sAuthorId   ID of the author (Person ID).
     * @param sPostContent Content of the blog post.
     * @throws InvalidInputException if input validation fails.
     */
    public BlogPost(String sId, String sAuthorId, String sPostContent) {
        if (sId == null || sAuthorId == null) {
            throw new InvalidInputException("Invalid BlogPost details provided.");
        }
        this.sId = sId;
        this.sAuthorId = sAuthorId;
        this.sPostContent = sPostContent;
    }
}
