package com.deloitte.magazine.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "reviews")
@Data
public class Review {

	@Id
	private String id;
	private String magazineId;
	private Person author;
	private int rating;
	private String description;
	private LocalDate publishedDate=LocalDate.now();

}
