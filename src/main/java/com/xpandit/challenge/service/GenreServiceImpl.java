package com.xpandit.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xpandit.challenge.entity.Genre;
import com.xpandit.challenge.repository.GenreRepository;

@Component
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;
	
	@Override
	public Genre getServiceById(Integer id) {
		return genreRepository.findById(id).orElse(null);
	}

}
