package com.xpandit.challenge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

	private String id;
	private String title;
	private LocalDate date;
	private BigDecimal rating;
	private BigDecimal revenue;
	
}
