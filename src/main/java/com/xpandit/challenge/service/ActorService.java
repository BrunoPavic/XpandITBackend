package com.xpandit.challenge.service;

import org.springframework.stereotype.Service;

import com.xpandit.challenge.entity.Actor;

@Service
public interface ActorService {

	public Actor getActorByID(Integer id);
	
}
