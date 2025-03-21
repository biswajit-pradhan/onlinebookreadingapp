package com.onlinebookreadingapp.entity;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(hidden = true)
	private Long publisherId;

	private String publisherName;
	private String publisherMobileNumber;
	private String publisherEmail;
	
	@Builder.Default
	private Boolean isApproved=false;

	@ManyToMany
	@Builder.Default
	private List<Book> publisherBooks = new ArrayList<>();
}
