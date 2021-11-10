package com.deloitte.magazine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "subscription_duration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDuartion {
	@Id
	private SUBFOR subfor;
}
