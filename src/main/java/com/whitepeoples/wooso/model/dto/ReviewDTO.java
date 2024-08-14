package com.whitepeoples.wooso.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReviewDTO {
	private Integer matchId;
	private Integer reviewer;
	private Integer rating;
	private String reviewDescription;
}
