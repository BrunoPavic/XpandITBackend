package com.xpandit.challenge.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.xpandit.challenge.dto.ActorDto;
import com.xpandit.challenge.dto.GenreDto;
import com.xpandit.challenge.dto.MovieDetailsDto;
import com.xpandit.challenge.dto.MovieDto;
import com.xpandit.challenge.dto.NewMovieDto;
import com.xpandit.challenge.dto.UpdateMovieDto;
import com.xpandit.challenge.entity.Actor;
import com.xpandit.challenge.entity.Director;
import com.xpandit.challenge.entity.Genre;
import com.xpandit.challenge.entity.Movie;
import com.xpandit.challenge.exception.MovieAlreadyExistsException;
import com.xpandit.challenge.exception.MovieNotFoudException;
import com.xpandit.challenge.repository.MovieRepository;

import mapper.MovieMapper;

@Component
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private GenreService genreService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private DirectorService directorService;
	
	private final LocalDate minDate = LocalDate.of(1753, 1, 1);
	private final LocalDate maxDate = LocalDate.of(9999, 12, 31);

	@Override
	public Page<MovieDto> getAllMovies(Integer pageNumber, Integer pageSize, LocalDate start, LocalDate end) {
		Page<Movie> moviePage;
			moviePage = movieRepository.findByDateBetweenOrderByRatingDescDateAscRevenueDesc(start !=null ? start : minDate, end !=null ? end : maxDate,
			PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize != null ?  pageSize : Integer.MAX_VALUE));
			
		return moviePage.map(MovieMapper::mapToMovieDto);
	}

	@Override
	public MovieDetailsDto getMovieById(String id) {
		var movie = movieRepository.findById(UUID.fromString(id)).orElse(null);
		if(movie == null)
			throw new MovieNotFoudException("Movie with id " + id + " does not exist");
		return MovieMapper.mapToMovieDetailsDto(movie);
	}

	@Override
	public void deleteMovie(String id) {
		var movie = movieRepository.findById(UUID.fromString(id)).orElse(null);
		if(movie == null)
			throw new MovieNotFoudException("Nothing to delete because movie with id " + id + " does not exist");
		movieRepository.delete(movie);
	}

	@Override
	public MovieDetailsDto saveMovie(NewMovieDto newMovie) {
		Set<Genre> genres = new HashSet<>();
		Set<Actor> actors = new HashSet<>();
		var director = new Director();
		
		if(newMovie.getDirector() != null)
			director = directorService.getDirectorById(newMovie.getDirector());
		
		if(newMovie.getGenres() != null)
			for (Integer genreId : newMovie.getGenres()) {
				genres.add(genreService.getServiceById(genreId));
			}
		
		if(newMovie.getActors() != null)
			for (Integer actorId : newMovie.getActors()) {
				actors.add(actorService.getActorByID(actorId));
			}
		
		var existingMovie = movieRepository.findByTitleAndDateAndDirector(newMovie.getTitle(), newMovie.getDate(), director).orElse(null);
		
		if(existingMovie != null) {
			for (Movie movie : existingMovie) {
				if(movie.getActors().stream().map(Actor::getActorId).allMatch(id -> actors.stream().map(Actor::getActorId).anyMatch(actorId -> actorId.equals(id))))
					throw new MovieAlreadyExistsException("This movie already exists under id " + movie.getMovieId().toString());
			}
		}
		
		var movie = new Movie(newMovie.getTitle(), newMovie.getDate(), newMovie.getRating(), newMovie.getRevenue(), newMovie.getRuntime(), newMovie.getVotes(), newMovie.getDescription(), genres, actors, director);
		
		return MovieMapper.mapToMovieDetailsDto(movieRepository.save(movie));
	}

	@Override
	public MovieDetailsDto updateMovie(UpdateMovieDto updatedMovieDto) {
		var movie = movieRepository.findById(UUID.fromString(updatedMovieDto.getId())).orElse(null);
		if(movie == null)
			throw new MovieNotFoudException("Cannot update movie because movie with id " + updatedMovieDto.getId() + " does not exist");
		
		Set<Genre> genres = new HashSet<>();
		Set<Actor> actors = new HashSet<>();
		var director = new Director();
		
		if(updatedMovieDto.getDirector() != null)
			director = directorService.getDirectorById(updatedMovieDto.getDirector().getId());
		
		if(updatedMovieDto.getGenres() != null)
			for (GenreDto genre : updatedMovieDto.getGenres()) {
				genres.add(genreService.getServiceById(genre.getId()));
			}
		
		if(updatedMovieDto.getActors() != null)
			for (ActorDto actor : updatedMovieDto.getActors()) {
				actors.add(actorService.getActorByID(actor.getId()));
			}
		
		movie.updateMovie(updatedMovieDto.getId(), updatedMovieDto.getTitle(), updatedMovieDto.getDate(), updatedMovieDto.getRating(), updatedMovieDto.getRevenue(), updatedMovieDto.getRuntime(), updatedMovieDto.getVotes(), updatedMovieDto.getDescription(), genres, actors, director);
		
		return MovieMapper.mapToMovieDetailsDto(movieRepository.save(movie));
	}

	@Override
	public Page<MovieDto> findTopMovies(Integer pageNumber, Integer pageSize, Integer year) {
		return movieRepository.findMoviesByRevenue(year, PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize != null ?  pageSize : Integer.MAX_VALUE))
				.map(MovieMapper::mapToMovieDto);
	}

}
