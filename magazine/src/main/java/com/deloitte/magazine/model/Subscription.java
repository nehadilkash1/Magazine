package com.deloitte.magazine.model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Document(collection = "subscription")
@Data
@Builder

public class Subscription {
	
	@Id
	private ObjectId id;
	private Magazine magazine;
	private Person user;
	private LocalDate expiryDate;
	private SUBFOR subscriptionPeriod;
	
}
