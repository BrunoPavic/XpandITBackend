package com.xpandit.challenge.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "actors")
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer actorId;
	private String firstName;
	private String lastName;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "actors")
    private Set<Movie> movies = new HashSet<>();

	@Override
	public String toString() {
		return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
	}

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	
}
