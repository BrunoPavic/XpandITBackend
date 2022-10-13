package com.xpandit.challenge.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {
	
	//basic data
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	        name = "UUID",
	        strategy = "org.hibernate.id.UUIDGenerator"
	)
	@Column(name = "ID", updatable = false, nullable = false)
	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	private UUID movieId;
	
	private String title;
	private LocalDate date;
	private BigDecimal rating;
	private BigDecimal revenue;
	
	//additional data
	private Integer runtime;
	private Integer votes;
	private String description;
	
	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "movie_genre", 
        joinColumns = { @JoinColumn(name = "movie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "genre_id") }
    )
    Set<Genre> genres = new HashSet<>();

	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "movie_actor", 
        joinColumns = { @JoinColumn(name = "movie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    Set<Actor> actors = new HashSet<>();

	@Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE, CascadeType.PERSIST})
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="director_id", nullable=false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Director director;

	public Movie(String title, LocalDate date, BigDecimal rating, BigDecimal revenue, Integer runtime, Integer votes,
			String description, Set<Genre> genres, Set<Actor> actors, Director director) {
		super();
		this.title = title;
		this.date = date;
		this.rating = rating;
		this.revenue = revenue;
		this.runtime = runtime;
		this.votes = votes;
		this.description = description;
		this.genres = genres;
		this.actors = actors;
		this.director = director;
	}
	
	public Movie updateMovie(String id, String title, LocalDate date, BigDecimal rating, BigDecimal revenue, Integer runtime, Integer votes,
			String description, Set<Genre> genres, Set<Actor> actors, Director director) {
		this.movieId = UUID.fromString(id);
		this.title = title;
		this.date = date;
		this.rating = rating;
		this.revenue = revenue;
		this.runtime = runtime;
		this.votes = votes;
		this.description = description;
		this.genres = genres;
		this.actors = actors;
		this.director = director;
		return this;
	}	
	
}