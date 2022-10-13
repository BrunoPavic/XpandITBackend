package mapper;

import com.xpandit.challenge.dto.ActorDto;
import com.xpandit.challenge.entity.Actor;

public class ActorMapper {
	
	private ActorMapper() {}
	
	public static ActorDto mapToActorDto(Actor actor) {
		return new ActorDto(actor.getActorId(), actor.getFirstName() + " " + actor.getLastName());
	}

}
