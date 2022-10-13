package com.xpandit.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xpandit.challenge.entity.Director;
import com.xpandit.challenge.repository.DirectorRepository;

@Component
public class DirectorServiceImpl implements DirectorService {

	@Autowired
	private DirectorRepository directorRepository;
	
	@Override
	public Director getDirectorById(Integer id) {
		return directorRepository.findById(id).orElse(null);
	}

}
