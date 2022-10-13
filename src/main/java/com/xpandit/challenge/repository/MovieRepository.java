package com.xpandit.challenge.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xpandit.challenge.entity.Director;
import com.xpandit.challenge.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

	public Page<Movie> findByDateBetweenOrderByRatingDescDateAscRevenueDesc(LocalDate start, LocalDate end, PageRequest page);
	
	public Optional<List<Movie>> findByTitleAndDateAndDirector(String title, LocalDate date, Director director);
	
	@Query(
		value = "SELECT * FROM movies WHERE (:year is null or YEAR(date) = :year) ORDER BY revenue DESC",
		nativeQuery = true
	)
	public Page<Movie> findMoviesByRevenue(@Param("year") Integer year, PageRequest page);
	
}
