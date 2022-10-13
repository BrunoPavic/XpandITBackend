package mapper;

import com.xpandit.challenge.dto.DirectorDto;
import com.xpandit.challenge.entity.Director;

public class DirectorMapper {
	
	private DirectorMapper() {}
	
	public static DirectorDto mapToDirectorDto(Director director) {
		return director != null ? new DirectorDto(director.getDirectorId(), director.getFirstName() + " " + director.getLastName()) : null;
	}

}
