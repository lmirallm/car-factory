package factory.car.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import factory.car.entity.Car;

@Stateless
public class CarService {

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
			return false;
		}
	}

	public boolean updateCar(Car user_car) {
		if (em.find(Car.class, user_car.getId()) != null) {
			em.merge(user_car);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteCar(String id) {
		if (em.find(Car.class, id) != null) {
			em.remove(id);
			return true;
		} else {
			return false;
		}
	}
}
