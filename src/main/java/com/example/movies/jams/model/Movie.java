/*
 * Copyright (c) Carson Fujita 2026.
 */

package com.example.movies.jams.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Movie
 * <p>
 * A model for movies.
 * @author Carson Fujita
 * @since 1.0.0
 */
@Entity
@Table(name = "movies")
public class Movie {

    /**
     * The primary ID of the Movie.
     * </br>
     * To be valid the id must be
     * between <code>0</code> and <code>4 294 967 295</code>.
     * This is in relation to the unsigned int SQL.
     * @since 1.0.0
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 0, message = "id cannot be below 0")
    @Max(value = 4294967295L, message = "id cannot exceed 4,294,967,295")
    private Long id;

    /**
     * This wraps the title value.
     * <br/>
     * The title of a movie like "The Empire Strikes Back".
     * To be valid the title value must be
     * between <code>2</code> to <code>255</code> characters
     * and neither be null nor blank.
     * @since 1.0.0
     * @see Movie#setTitle(String)
     * @see Movie#getTitle()
     */
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title cannot be blank")
    @Size(min=2, max=255, message="Title must be between 2 to 255 characters")
    private String title;

    /**
     * Wraps the genre value.
     * <br/>
     * To be valid, the genre value must be
     * between <code>3</code> to <code>64</code> characters
     * and neither <code>null</code> nor blank.
     * @since 1.0.0
     * @see Movie#getGenre() 
     * @see Movie#setGenre(String) 
     */
    @NotNull(message = "genre cannot be null")
    @NotBlank(message = "genre cannot be blank")
    @Size(min=3, max=64, message="Genre must be between 3 to 64 characters")
    private String genre;

    /**
     * Wraps the releaseYear value.
     * <br/>
     * To be valid the releaseYear value must be
     * between <code>0</code> and <code>3000</code>.
     * Apparently one of the first or oldest surviving movies was in the 1800s,
     * but if you wish to stretch the meaning of movie to a <i>play</i> then
     * our year can go only as far back as to 0 AD.
     * @since 1.0.0
     * @see Movie#setReleaseYear(Short)
     * @see Movie#getReleaseYear()
     */
    @Min(value = 0, message = "Year cannot be in an age where movies didn't exist")
    @Max(value=3000, message="Year must be between 0 to 3000 characters")
    private Short releaseYear;

    /**
     * Gets the id of this Movie
     *
     * @since 1.0.0
     * @see Movie#id
     * @see Movie#setId
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of this Movie
     *
     * @since 1.0.0
     * @param id the id to set
     * @see Movie#id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the title of this Movie
     *
     * @since 1.0.0
     * @see Movie#title
     * @see Movie#setTitle
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of this Movie
     *
     * @since 1.0.0
     * @param title the title to set
     * @see Movie#title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the genre of this Movie
     *
     * @since 1.0.0
     * @see Movie#genre
     * @see Movie#setGenre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets the genre of this Movie
     *
     * @since 1.0.0
     * @param genre the genre to set
     * @see Movie#genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets the releaseYear of this Movie
     *
     * @since 1.0.0
     * @see Movie#releaseYear
     * @see Movie#setReleaseYear
     */
    public Short getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the releaseYear of this Movie
     *
     * @since 1.0.0
     * @param releaseYear the releaseYear to set
     * @see Movie#releaseYear
     */
    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }
}
