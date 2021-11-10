package com.deloitte.magazine.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "magazines")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Magazine {

	@Id
	private int id;
	@NotBlank
	private String name;
	private String about;
	private LocalDate createdAt;
	private String category;
	private String image;
	private Person author;
	private double avgRating;
	private int totalReviews;
	private Review latestReview;
	private int votes;
	private int likedPercentage;

}
