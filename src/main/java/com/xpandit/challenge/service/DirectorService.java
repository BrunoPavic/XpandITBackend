package com.xpandit.challenge.service;

import org.springframework.stereotype.Service;

import com.xpandit.challenge.entity.Director;

@Service
public interface DirectorService {

	public Director getDirectorById(Integer id);
	
}
