package factory.car.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "brand")
	private String brand;
	@Column(name = "registration")
	private Timestamp registration;
	@Column(name = "country")
	private String country;
	@Column(name = "created_at")
	private Timestamp created_at;
	@Column(name = "last_updated")
	private Timestamp last_updated;
	
	@PrePersist
	protected void onRegistration() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.created_at=timestamp;
		this.last_updated=timestamp;
		this.registration=timestamp;
		
	}
	
	public Car() {
		
	}
	
	/**
	 * @param id
	 * @param brand
	 * @param registration
	 * @param country
	 * @param created_at
	 * @param last_updated
	 */
	public Car(String id, String brand, Timestamp registration, String country, Timestamp created_at,
			Timestamp last_updated) {
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
	public Timestamp getRegistration() {
		return registration;
	}
	public void setRegistration(Timestamp registration) {
		this.registration = registration;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(Timestamp last_updated) {
		this.last_updated = last_updated;
	}
	
	
}
