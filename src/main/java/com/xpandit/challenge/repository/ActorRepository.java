package com.xpandit.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpandit.challenge.entity.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

}
