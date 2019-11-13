package com.capgemini.dnd.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "RMSpecs")
public class RawMaterialSpecsEntity {

	@Id
	@Column(name = "rmsId")
	private String rmsId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "duration")
	private int duration; 
	
	@Column(name = "description")
	private String description; 

	public RawMaterialSpecsEntity() {
		
	}
	
	public RawMaterialSpecsEntity(String rmsId, String name, Date manufacturingDate, int duration, String description) {
		this.rmsId = rmsId;
		this.name = name;
		this.duration = duration;
		this.description = description;
	}

	public String getRmsId() {
		return rmsId;
	}

	public String getName() {
		return name;
	}

	public int getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}
}
