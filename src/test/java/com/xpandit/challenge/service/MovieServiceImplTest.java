package com.xpandit.challenge.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;

import com.xpandit.challenge.Util.MovieUtil;
import com.xpandit.challenge.dto.MovieDetailsDto;
import com.xpandit.challenge.dto.NewMovieDto;
import com.xpandit.challenge.entity.Movie;
import com.xpandit.challenge.exception.MovieNotFoudException;
import com.xpandit.challenge.repository.MovieRepository;

import mapper.MovieMapper;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {
	
	@Mock
	private MovieRepository movieRepository;
	
	@Mock
	private GenreServiceImpl genreService;
	@Mock
	private ActorServiceImpl actorService;
	@Mock
	private DirectorServiceImpl directorService;
	
	@InjectMocks
	private MovieServiceImpl movieService;
	
	@Test
	public void findByID() {
		Movie movie = MovieUtil.createMovie();
		String uuid = "24f955ba-9b9b-470b-991d-3464bc583e1a";
		
		
		when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
		
		
		MovieDetailsDto movieDetailsDto = movieService.getMovieById(uuid);
		
	
		MovieMapper.mapToMovieDetailsDto(movie);
		verify(movieRepository, times(1)).findById(any());
		verifyNoMoreInteractions(movieRepository);
	}
	
	@Test
	public void findByIDException() {
		MovieDetailsDto detailsDto = MovieUtil.createMovieDetailsDTO();
		Movie movie = MovieUtil.createMovie();
		String uuid = "24f955ba-9b9b-470b-991d-3464bc583e1a";
		
		
		when(movieRepository.findById(any())).thenReturn(Optional.empty());
		
		
		Assertions.assertThrows(MovieNotFoudException.class, () -> movieService.getMovieById(uuid));
		verify(movieRepository, times(1)).findById(any());
		verifyNoMoreInteractions(movieRepository);
	}
	
	
	@Test
	public void saveMovie() {
		NewMovieDto newMovieDto = MovieUtil.createNewMovieDTO();
		MovieDetailsDto detailsDto = MovieUtil.createMovieDetailsDTO();
		Movie movie = MovieUtil.createMovie();
		
		when(movieRepository.save(any())).thenReturn(movie);
		when(movieRepository.findByTitleAndDateAndDirector(any(), any(), any())).thenReturn(Optional.empty());
		
		MovieDetailsDto movieDetailsDto = movieService.saveMovie(newMovieDto);
		
		Assertions.assertEquals(detailsDto, movieDetailsDto);
		verify(movieRepository, times(1)).save(any());
		verify(movieRepository, times(1)).findByTitleAndDateAndDirector(any(), any(), any());
		verifyNoMoreInteractions(movieRepository);
	}
	
	@Test
	public void saveMovieException() {
		NewMovieDto newMovieDto = MovieUtil.createNewMovieDTO();
		MovieDetailsDto detailsDto = MovieUtil.createMovieDetailsDTO();
		Movie movie = MovieUtil.createMovie();
		
		when(movieRepository.save(any())).thenReturn(movie);
		when(movieRepository.findByTitleAndDateAndDirector(any(), any(), any())).thenReturn(Optional.empty());
		
		MovieDetailsDto movieDetailsDto = movieService.saveMovie(newMovieDto);
		
		Assertions.assertEquals(detailsDto, movieDetailsDto);
		verify(movieRepository, times(1)).save(any());
		verify(movieRepository, times(1)).findByTitleAndDateAndDirector(any(), any(), any());
		verifyNoMoreInteractions(movieRepository);
	}
}
