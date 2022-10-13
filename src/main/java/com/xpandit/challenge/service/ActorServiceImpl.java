package com.xpandit.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xpandit.challenge.entity.Actor;
import com.xpandit.challenge.repository.ActorRepository;

@Component
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorRepository actorRepository;
	
	@Override
	public Actor getActorByID(Integer id) {
		return actorRepository.findById(id).orElse(null);
	}

}
