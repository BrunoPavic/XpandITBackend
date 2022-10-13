package mapper;

import java.util.stream.Collectors;

import com.xpandit.challenge.dto.MovieDetailsDto;
import com.xpandit.challenge.dto.MovieDto;
import com.xpandit.challenge.entity.Movie;

public class MovieMapper {
	
	private MovieMapper() {}
	
	public static MovieDto mapToMovieDto(Movie movie) {
		return new MovieDto(movie.getMovieId().toString(), movie.getTitle(), movie.getDate(), movie.getRating(), movie.getRevenue());
	}
	
	public static MovieDetailsDto mapToMovieDetailsDto(Movie movie) {
		return new MovieDetailsDto(movie.getMovieId().toString(), movie.getTitle(), movie.getDate(), movie.getRating(),
				movie.getRevenue(), (movie.getGenres() != null ? movie.getGenres().stream().map(GenreMapper::mapToGenreDto).collect(Collectors.toList()) : null),
				(movie.getActors() != null ? movie.getActors().stream().map(ActorMapper::mapToActorDto).collect(Collectors.toList()) : null), 
				DirectorMapper.mapToDirectorDto(movie.getDirector()), movie.getRuntime(), movie.getVotes(), movie.getDescription());
	}

}
