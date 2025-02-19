package models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.extern.jackson.Jacksonized;
import exceptions.InvalidInputException;

/**
 * Represents a person with an ID, name, age, and gender.
 * Implements validation and uses Lombok for boilerplate code reduction.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder
@Jacksonized
public class Person {
    private final String sId;
    private final String sFirstName;
    private final String sLastName;
    private final Integer nAge;
    private final String sGender;

    /**
     * Constructor for the Person class with input validation.
     *
     * @param sId        Unique ID of the person.
     * @param sFirstName First name of the person.
     * @param sLastName  Last name of the person.
     * @param nAge       Age of the person.
     * @param sGender    Gender of the person.
     * @throws InvalidInputException if input validation fails.
     */
    public Person(String sId, String sFirstName, String sLastName, Integer nAge, String sGender) {
        if (sId == null || sFirstName == null || sFirstName.isBlank() ||
                sLastName == null || sLastName.isBlank() || nAge < 0) {
            throw new InvalidInputException("Invalid Person details provided.");
        }
        this.sId = sId;
        this.sFirstName = sFirstName;
        this.sLastName = sLastName;
        this.nAge = nAge;
        this.sGender = sGender;
    }
}
