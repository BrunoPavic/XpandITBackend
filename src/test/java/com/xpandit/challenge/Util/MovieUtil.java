package com.xpandit.challenge.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.xpandit.challenge.dto.DirectorDto;
import com.xpandit.challenge.dto.MovieDetailsDto;
import com.xpandit.challenge.dto.NewMovieDto;
import com.xpandit.challenge.entity.Movie;

public class MovieUtil {
	
	public static MovieDetailsDto createMovieDetailsDTO() {
		return new MovieDetailsDto("24f955ba-9b9b-470b-991d-3464bc583e1a", "title", LocalDate.of(2020, 1, 8), new BigDecimal(1), new BigDecimal(1), null, null, null, 1, 1, "desc");
	}
	
	public static Movie createMovie() {
		return new Movie(UUID.fromString("24f955ba-9b9b-470b-991d-3464bc583e1a"), "title", LocalDate.of(2020, 1, 8), new BigDecimal(1), new BigDecimal(1), 1, 1, "desc", null, null, null);
	}

	public static NewMovieDto createNewMovieDTO() {
		return new NewMovieDto("title", LocalDate.of(2020, 1, 8), new BigDecimal(1), new BigDecimal(1), null, null, null, 1, 1, "des");
	}
}
