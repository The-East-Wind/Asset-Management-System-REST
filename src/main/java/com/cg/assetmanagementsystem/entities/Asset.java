package com.cg.assetmanagementsystem.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="asset")
public class Asset implements Serializable {
	@Id
	@Column(name="asset_id")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "asset_id_gen")
	private Integer assetId;
	@Column(name="asset_name")
	private String assetName;
	@Column(name="asset_desc")
	private String assetDescription;
	@Column(name="availability")
	private String availability;
	@OneToOne
	@JoinColumn(name="allotted_to")
	private Employee allottedTo;
	@Column(name="asset_category")
	private String assetCategory;
	public Asset(){
	}
	
	public Asset(String assetName, String assetDescription, String availability, Employee allottedTo,
				 String assetCategory) {
		this.assetName = assetName;
		this.assetDescription = assetDescription;
		this.availability = availability;
		this.allottedTo = allottedTo;
		this.assetCategory = assetCategory;
	}
	
	public String getAssetCategory() {
		return assetCategory;
	}
	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}
	public Integer getAssetId() {
		return assetId;
	}
	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public Employee getAllottedTo() {
		return allottedTo;
	}
	public void setAllottedTo(Employee allottedTo) {
		this.allottedTo = allottedTo;
	}
}
