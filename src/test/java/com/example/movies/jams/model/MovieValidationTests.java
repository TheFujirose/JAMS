/*
 * Copyright (c) Carson Fujita 2026.
 */

package com.example.movies.jams.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validation Tests for {@link com.example.movies.jams.model.Movie Movie}.
 * <br/>
 * This covers:
 * <ul>
 *     <li>ID below min</li>
 *     <li>ID above max</li>
 *     <li>ID within range</li>
 *     <li>ID not being unique</li>
 *     <li>Title being null</li>
 *     <li>Title being blank</li>
 *     <li>Title character count above max</li>
 *     <li>Title character count below max</li>
 *     <li>Title character count in range</li>
 *     <li>Title character count in range</li>
 *     <li>Genre being null</li>
 *     <li>Genre being blank</li>
 *     <li>Genre character count above max</li>
 *     <li>Genre character count below min</li>
 *     <li>Genre character count in range</li>
 *     <li>Release year below min</li>
 *     <li>Release year above max</li>
 *     <li>Release year in range</li>
 * </ul>
 * @author Carson Fujita
 * @since 1.0.0
 */
public class MovieValidationTests {

    /**
     * This is the validator
     * @since 1.0.0
     */
    private static Validator validator;

    /**
     * Sets up the validator.
     * @since 1.0.0
     */
    @BeforeAll
    public static void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Test {@link com.example.movies.jams.model.Movie Movie} id below
     * the min of <code>0</code>.
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void idBelowMin(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-Fi");
        movie.setId(-1L);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        assertFalse(violations.isEmpty());
    }

    /**
     * Test {@link com.example.movies.jams.model.Movie Movie} id above
     * the max of <code>4294967295L</code>.
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void idAboveMax(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-Fi");
        movie.setId(14294967296L); // +1
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);

        assertFalse(violations.isEmpty());
    }

    /**
     * Positive test to test if valid range number works.
     * @see Movie
     */
    @Test
    void idInRange(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-Fi");
        movie.setId(1000L);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertTrue(violations.isEmpty());
    }


    /**
     * Test to see if IDs being Unique is a valid case.
     * @since 1.0.0
     */
    @Test
    void idNotUnique(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-Fi");
        movie.setId(14294967296L);
        Movie movie2 = new Movie();
        movie2.setTitle("Interstellar 2: Electric Boogaloo");
        movie2.setGenre("Sci-Fi");
        movie2.setId(14294967296L);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie2);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for title
     * where it is null.
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void titleNotNull(){
        Movie movie = new Movie();
        movie.setTitle(null);
        movie.setGenre("Sci-Fi");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for title
     * where it's blank.
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void titleNotBlank(){
        Movie movie = new Movie();
        movie.setTitle("");
        movie.setGenre("Sci-Fi");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for title
     * where the character count is above the max
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void titleAboveMax(){
        Movie movie = new Movie();
        // If 5 paragraphs of Lorem Ipsum is not above 255 characters I will eat my hat.
        movie.setTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus convallis ex odio, in malesuada elit commodo a. Aliquam blandit lorem in massa placerat viverra. Aenean eleifend ex ut fringilla luctus. Vivamus porttitor aliquam lectus sed convallis. Sed efficitur nulla leo, et hendrerit odio suscipit vel. Curabitur consectetur dapibus ex in egestas. Sed nec eros id mauris lacinia laoreet ut vel odio. Sed ultrices, purus sit amet convallis suscipit, tortor augue sagittis ipsum, sed bibendum augue augue sit amet nunc. Donec cursus massa vel mattis lobortis. In posuere elit sapien, ac consectetur tellus ultrices lacinia. Sed nec sollicitudin tellus. Duis lectus leo, posuere non ligula in, porta condimentum ante.");
        movie.setGenre("Sci-Fi");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for title
     * where character count at the min of 1
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void titleBelowMin(){
        Movie movie = new Movie();
        movie.setTitle("e");
        movie.setGenre("Sci-Fi");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for title
     * where it is in the 1 to 255 character range
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void titleInRange(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar"); //great movie btw
        movie.setGenre("Sci-Fi");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertTrue(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for genre
     * where value is null
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void genreNotNull(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar"); //great movie btw
        movie.setGenre(null);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for genre
     * where value is blank
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void genreNotBlank(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for genre
     * where amount of characters below min of 3
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void genreBelowMin(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("EA");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for genre
     * where character amount over max 64
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void genreAboveMax(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("I was like, why shouldn't I do test cases for a 6% assignment?? Oh, that's why. This took much longer than I had anticipated like wow. That's a lot of stuff");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for genre
     * where character count in range of 3-64
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void genreInRange(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setGenre("Sci-FI");
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertTrue(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for releaseYear
     * where it is below min 0
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void releaseYearBelowMin(){
        Movie movie = new Movie();
        movie.setTitle("Interstellar");
        movie.setReleaseYear( (short) -78);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for releaseYear
     * where it is above max of 3000
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void releaseYearAboveMax(){
        Movie movie = new Movie();
        movie.setReleaseYear( (short) 3000);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }

    /**
     * Tests {@link Movie} Validation for releaseYear
     * where in range 0-3000
     * @since 1.0.0
     * @see Movie
     */
    @Test
    void releaseYearInRange(){
        Movie movie = new Movie();
        movie.setReleaseYear((short) 2001);
        Set<ConstraintViolation<Movie>> violations = validator.validate(movie);
        assertFalse(violations.isEmpty());
    }
}