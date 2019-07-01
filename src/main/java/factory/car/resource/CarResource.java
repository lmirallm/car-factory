package factory.car.resource;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
	private final static Logger logger = Logger.getLogger(CarResource.class.getName());
	@EJB
	private CarService carService;

	@GET
	@ApiOperation(value = "Return all cars", response = Car.class, responseContainer = "List")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "car list", response = Car.class) })
	public Response getAllCars() {
		final GenericEntity<List<Car>> cars = new GenericEntity<List<Car>>(carService.getCars()) {
		};
		return Response.status(Status.OK).entity(cars).build();
	}

	@GET
	@Path("{id}")
	@ApiOperation(value = "Return car by its ID", response = Car.class, responseContainer = "JSON")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "requested car", response = Car.class),
			@ApiResponse(code = 400, message = "bad request", response = String.class, examples = @Example(value = {
					@ExampleProperty(value = "Could not get car") })) })
	public Response getCarById(
			@ApiParam(value = "id of the car that need to be returned", required = true) @PathParam("id") final String id) {
		Car carFromDB = carService.getCar(id);
		if (carFromDB != null) {
			logger.info("Car with ID:" + carFromDB.getId() + " whose brand is " + carFromDB.getBrand() + " and country "
					+ carFromDB.getCountry() + " has been gotten from the DB");
			return Response.status(Status.OK).entity(carFromDB).build();
		} else {
			logger.log(Level.SEVERE, "The id of the car could not be found on the database");
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	@ApiOperation(value = "Create cars", response = Car.class, responseContainer = "JSON")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Car created", response = Car.class),
			@ApiResponse(code = 400, message = "bad request", response = String.class, examples = @Example(value = {
					@ExampleProperty(value = "Could not create car") })) })
	public Response createCar(Car car) {
		if (carService.createCar(car)) {
			logger.info("Car with ID" + car.getId() + " whose brand is " + car.getBrand() + " and country "
					+ car.getCountry() + " has been persisted to the DB");
			return Response.status(Status.OK).entity("Car with ID " + car.getId() + " created correctly").build();
		} else {
			logger.log(Level.SEVERE, "The car could not be persisted on the database");
			return Response.status(Status.BAD_REQUEST).entity("Car with ID " + car.getId() + " could not be created")
					.build();
		}
	}

	@PUT
	@Path("{id}")
	@ApiOperation(value = "Update car by its ID", response = Car.class, responseContainer = "JSON")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Car updated", response = Car.class),
			@ApiResponse(code = 400, message = "bad request", response = String.class, examples = @Example(value = {
					@ExampleProperty(value = "Could not update car") })) })
	public Response updateCar(Car car,@ApiParam(value = "id of the car that need to be updated", required = true) @PathParam("id") String id) {
		if (carService.updateCar(car, id)) {
			logger.info("Car with ID" + car.getId() + " whose brand is " + car.getBrand() + " and country "
					+ car.getCountry() + " has been updated");
			return Response.status(Status.OK).entity("Car with ID " + id + " updated correctly").build();
		} else {
			logger.log(Level.SEVERE, "The  car could not be updated on the database");
			return Response.status(Status.BAD_REQUEST).entity("Car with ID " + id + "could not be updated").build();
		}
	}

	@DELETE
	@Path("{id}")
	@ApiOperation(value = "Delete car by its ID from DB", response = Car.class, responseContainer = "JSON")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Car deleted", response = Car.class),
			@ApiResponse(code = 400, message = "bad request", response = String.class, examples = @Example(value = {
					@ExampleProperty(value = "Could not delete car") })) })
	public Response deleteCar( @ApiParam(value="id of the car that need to be deleted",required=true) @PathParam("id") final String id) {
		if (carService.deleteCar(id)) {
			return Response.status(Status.OK).entity("Car with ID " + id + " deleted correctly").build();
		} else {
			logger.log(Level.SEVERE, "The car could not be removed from the database");
			return Response.status(Status.BAD_REQUEST).entity("Car with ID " + id + " not found").build();
		}
	}
}
