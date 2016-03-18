package com.marse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="category")
public class Category {

	
	@Id
	@GenericGenerator(name="catIdGenerator", strategy="increment")
	@GeneratedValue(generator="catIdGenerator")
	@Column()
	private int categoryId;
	@Column()
	private String categoryName;
	
	
	// setters and getters
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
