package models;

import models.Person;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class PersonTest {

    @Test
    void shouldCreatePersonSuccessfully() {
        Person person = new Person("P001", "John", "Doe", 25, "Male");

        assertThat(person.getSId()).isEqualTo("P001");
        assertThat(person.getSFirstName()).isEqualTo("John");
        assertThat(person.getSLastName()).isEqualTo("Doe");
        assertThat(person.getNAge()).isEqualTo(25);
        assertThat(person.getSGender()).isEqualTo("Male");
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThatThrownBy(() -> new Person(null, "John", "Doe", 25, "Male"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Invalid Person details provided.");
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsNull() {
        assertThatThrownBy(() -> new Person("P001", null, "Doe", 25, "Male"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Invalid Person details provided.");
    }

    @Test
    void shouldThrowExceptionWhenAgeIsNegative() {
        assertThatThrownBy(() -> new Person("P001", "John", "Doe", -5, "Male"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("Invalid Person details provided.");
    }
}
