package com.capgemini.dnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ProductSpecs")
public class ProductSpecsEntity {
	
	@Id
	@Column(name = "pId")
	private String psId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "duration")
	private String duration;
	
	@Column(name = "description")
	private String description;


	public ProductSpecsEntity() {
		
	}
	
	public ProductSpecsEntity(String psId, String name, String duration, String description) {
		this.psId = psId;
		this.name = name;
		this.duration = duration;
		this.description = description;
	}
	
	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
