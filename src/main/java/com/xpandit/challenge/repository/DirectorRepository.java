package com.xpandit.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpandit.challenge.entity.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Integer> {

}
