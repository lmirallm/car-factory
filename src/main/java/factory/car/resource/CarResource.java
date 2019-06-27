package factory.car.resource;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import factory.car.entity.Car;
import factory.car.service.CarService;

@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {

	@EJB
	private CarService carService;

	@GET
	public Response getAllCars() {
		final GenericEntity<List<Car>> cars = new GenericEntity<List<Car>>(carService.getCars()) {
		};

		return Response.status(Status.OK).entity(cars).build();
	}

	@GET
	@Path("{id}")
	public Response getCarById(@PathParam("id") final String id) {
		Car carFromDB = carService.getCar(id);
		if (carFromDB != null) {
			return Response.status(Status.OK).entity(carFromDB).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	public Response createCar(Car car) {
		if (carService.createCar(car))
			return Response.status(Status.OK).entity("Car with ID " + car.getId() + " created correctly").build();
		else
			return Response.status(Status.BAD_REQUEST).entity("Car with ID " + car.getId() + " could not be created")
					.build();
	}

	@PUT
	@Path("{id}")
	public Response updateCar(Car car,@PathParam("id") String id) {
		if (carService.updateCar(car,id)) {
			return Response.status(Status.OK).entity("Car with ID " + id + " updated correctly").build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Car with ID " + id + "could not be updated")
					.build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deleteCar(@PathParam("id") final String id) {
		if (carService.deleteCar(id)) {
			return Response.status(Status.OK).entity("Car with ID " + id + " deleted correctly").build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Car with ID " + id + " not found").build();
		}
	}
}
