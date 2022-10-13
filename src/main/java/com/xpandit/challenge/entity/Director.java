package com.xpandit.challenge.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "directors")
@AllArgsConstructor
@NoArgsConstructor
public class Director {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer directorId;
	private String firstName;
	private String lastName;
	
	@OneToMany(mappedBy="director")
    private Set<Movie> movies;

	@Override
	public String toString() {
		return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
	}

	public Integer getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Integer directorId) {
		this.directorId = directorId;
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
