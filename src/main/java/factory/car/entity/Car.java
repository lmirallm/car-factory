package factory.car.entity;



import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "brand")
	private String brand;
	@Column(name = "registration",columnDefinition="Timestamp")
	private LocalDateTime registration;
	@Column(name = "country" )
	private String country;
	@Column(name = "created_at", updatable=false,columnDefinition="Timestamp")
	private LocalDateTime created_at;
	@Column(name = "last_updated",columnDefinition="Timestamp")
	private LocalDateTime last_updated;

	@PrePersist
	protected void onRegistration() {
<<<<<<< Updated upstream
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.created_at=timestamp;
		this.last_updated=timestamp;
		this.registration=timestamp;
<<<<<<< Updated upstream
		this.id=UUID.randomUUID().toString();
=======
		
>>>>>>> Stashed changes
=======
		this.created_at = LocalDateTime.now();
		this.last_updated =LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
>>>>>>> Stashed changes
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
