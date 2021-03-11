package com.binhle.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(updatable = false, nullable = false)
	private Long id;
	
	@Getter
	@Setter
	private String title;
}
