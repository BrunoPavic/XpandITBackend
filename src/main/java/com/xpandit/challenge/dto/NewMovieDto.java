package com.xpandit.challenge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMovieDto {

	@NotBlank
	private String title;
	@NotNull
	private LocalDate date;
	@NotNull
	private BigDecimal rating;
	@NotNull
	private BigDecimal revenue;
	private Set<Integer> genres;
	private Set<Integer> actors;
	private Integer director;
	private Integer runtime;
	private Integer votes;
	private String description;
	
}
