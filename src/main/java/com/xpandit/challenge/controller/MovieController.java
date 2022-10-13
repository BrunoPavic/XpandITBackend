package com.xpandit.challenge.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpandit.challenge.dto.MovieDetailsDto;
import com.xpandit.challenge.dto.MovieDto;
import com.xpandit.challenge.dto.NewMovieDto;
import com.xpandit.challenge.dto.UpdateMovieDto;
import com.xpandit.challenge.service.MovieService;

@RestController
@RequestMapping("app/movies")
@CrossOrigin(origins = "*")
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	@GetMapping
	public Page<MovieDto> getMovies(@RequestParam(name = "pageNumber", required = false) Integer pageNumber, @RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @RequestParam(name = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end){
		return movieService.getAllMovies(pageNumber, pageSize, start, end);
	}
	
	@GetMapping("/{id}")
	public MovieDetailsDto getMovieById(@PathVariable String id) {
		return movieService.getMovieById(id);
	}
	
	@GetMapping("/top-movies")
	public Page<MovieDto> getTopMovies(@RequestParam(name = "pageNumber", required = false) Integer pageNumber, @RequestParam(name = "pageSize", required = false) Integer pageSize, @RequestParam(name = "year", required = false) Integer year){
		return movieService.findTopMovies(pageNumber, pageSize, year);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteMovie(@PathVariable String id){
		movieService.deleteMovie(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	public ResponseEntity<MovieDetailsDto> saveMovie(@Valid @RequestBody NewMovieDto newMovie){
		return new ResponseEntity<>(movieService.saveMovie(newMovie), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<MovieDetailsDto> updateMovie(@Valid @RequestBody UpdateMovieDto updatedMovie){
		return new ResponseEntity<>(movieService.updateMovie(updatedMovie), HttpStatus.OK);
	}
	
}
