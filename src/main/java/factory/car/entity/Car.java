package factory.car.entity;



import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@Column(name = "id")
	private String id;
	@NotEmpty(message = "Brand cannot be null")
	@Column(name = "brand")
	private String brand;
	@PastOrPresent(message ="Date can not be a future one ")
	@NotEmpty(message = "Registration cannot be null")
	@Column(name = "registration",columnDefinition="Timestamp")
	private LocalDateTime registration;
	@NotEmpty(message = "Country cannot be null")
	@Column(name = "country" )
	private String country;
	@Column(name = "created_at", updatable=false,columnDefinition="Timestamp")
	private LocalDateTime created_at;
	@Column(name = "last_updated",columnDefinition="Timestamp")
	private LocalDateTime last_updated;

	@PrePersist
	protected void onRegistration() {

		this.created_at = LocalDateTime.now();
		this.last_updated =LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
	}

	@PreUpdate
	protected void onUpdate() {
		this.last_updated = LocalDateTime.now();
	}

	public Car() {

	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	/**
	 * @param id
	 * @param brand
	 * @param registration
	 * @param country
	 * @param created_at
	 * @param last_updated
	 */
	public Car(String id, String brand, LocalDateTime registration, String country, LocalDateTime created_at,
			LocalDateTime last_updated) {
		this.id = id;
		this.brand = brand;
		this.registration = registration;
		this.country = country;
		this.created_at = created_at;
		this.last_updated = last_updated;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public LocalDateTime getRegistration() {
		return registration;
	}

	public void setRegistration(LocalDateTime registration) {
		this.registration = registration;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public void setLast_updated(LocalDateTime last_updated) {
		this.last_updated = last_updated;
	}

}
