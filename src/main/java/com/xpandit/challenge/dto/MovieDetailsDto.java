package com.xpandit.challenge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsDto {

	private String id;
	private String title;
	private LocalDate date;
	private BigDecimal rating;
	private BigDecimal revenue;
	
	private List<GenreDto> genres;
	private List<ActorDto> actors;
	private DirectorDto director;
	private Integer runtime;
	private Integer votes;
	private String description;
	
}
