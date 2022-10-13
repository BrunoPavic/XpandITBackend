package com.xpandit.challenge.service;

import org.springframework.stereotype.Service;

import com.xpandit.challenge.entity.Genre;

@Service
public interface GenreService {

	public Genre getServiceById(Integer id);
	
}
