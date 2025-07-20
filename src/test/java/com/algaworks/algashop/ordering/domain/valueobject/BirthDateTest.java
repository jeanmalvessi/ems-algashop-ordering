package com.algaworks.algashop.ordering.domain.valueobject;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BirthDateTest {

    @Test
    void shouldCreateValidBirthDate() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        BirthDate birthDate = new BirthDate(date);

        assertNotNull(birthDate);
        assertEquals(date, birthDate.value());
    }

    @Test
    void shouldCalculateAgeCorrectly() {
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        BirthDate birth = new BirthDate(birthDate);

        int expectedAge = LocalDate.now().getYear() - birthDate.getYear();
        assertEquals(expectedAge, birth.age());
    }

    @Test
    void shouldThrowExceptionForNullDate() {
        assertThrows(NullPointerException.class, () -> new BirthDate(null));
    }

    @Test
    void shouldThrowExceptionForFutureDate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> new BirthDate(futureDate));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        LocalDate date = LocalDate.of(1990, 1, 1);
        BirthDate birthDate = new BirthDate(date);

        assertEquals(date.toString(), birthDate.toString());
    }
}
