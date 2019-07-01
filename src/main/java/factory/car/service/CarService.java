package factory.car.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import factory.car.entity.Car;
import logger.MyLogger;
@Stateless
public class CarService {
	private final static Logger logger = Logger.getLogger(CarService.class.getName());
	
	@PersistenceContext(unitName = "pc_em")
	private EntityManager em;

	public List<Car> getCars() {
		return (List<Car>) em.createQuery("SELECT c FROM Car c").getResultList();
	}

	public Car getCar(String id) {
		return em.find(Car.class, id);
	}

	public boolean createCar(Car user_car) {
		em.persist(user_car);
		em.flush();
		if (em.find(Car.class, user_car.getId()) != null) {
			return true;
		} else {
			logger.log(Level.SEVERE,"Car could not be found");
			return false;
		}
	}

	public boolean updateCar(Car user_car, String id) {
		logger.info("Input car " + user_car);
		Car dbCar = em.find(Car.class, id);
		if (dbCar != null) {
			if (user_car.getBrand() != null && user_car.getBrand() != dbCar.getBrand())
				dbCar.setBrand(user_car.getBrand());
			if (user_car.getCountry() != null && user_car.getCountry() != dbCar.getCountry())
				dbCar.setCountry(user_car.getCountry());
			if (user_car.getRegistration() != null && user_car.getRegistration() != dbCar.getRegistration())
				dbCar.setRegistration(user_car.getRegistration());
			em.flush();
			logger.info("Output car " + dbCar);
			return true;
		} else {
			logger.log(Level.SEVERE, "Car could not be found");
			return false;
		}
	}

	public boolean deleteCar(String id) {
		Car dbCar = em.find(Car.class, id);
		logger.info("Car to delete" + dbCar);
		if (dbCar != null) {
			em.remove(dbCar);
			em.flush();
			return true;
		} else {
			logger.log(Level.SEVERE, "Car could not be found");
			return false;
		}
	}
}
