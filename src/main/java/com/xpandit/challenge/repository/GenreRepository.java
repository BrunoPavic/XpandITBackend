package com.xpandit.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpandit.challenge.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
