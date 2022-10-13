package mapper;

import com.xpandit.challenge.dto.GenreDto;
import com.xpandit.challenge.entity.Genre;

public class GenreMapper {
	
	private GenreMapper() {}
	
	public static GenreDto mapToGenreDto(Genre genre) {
		return new GenreDto(genre.getGenreId(), genre.getName());
	}

}
