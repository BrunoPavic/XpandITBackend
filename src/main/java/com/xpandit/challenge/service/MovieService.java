package com.xpandit.challenge.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.xpandit.challenge.dto.MovieDetailsDto;
import com.xpandit.challenge.dto.MovieDto;
import com.xpandit.challenge.dto.NewMovieDto;
import com.xpandit.challenge.dto.UpdateMovieDto;

@Service
public interface MovieService {

	public Page<MovieDto> getAllMovies(Integer pageNumber, Integer pageSize, LocalDate start, LocalDate end);
	public MovieDetailsDto getMovieById(String id);
	public void deleteMovie(String id);
	public MovieDetailsDto saveMovie(NewMovieDto newMovie);
	public MovieDetailsDto updateMovie(UpdateMovieDto updatedMovie);
	public Page<MovieDto> findTopMovies(Integer pageNumber, Integer pageSize, Integer year);
	
}
