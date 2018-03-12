package br.com.cinq.spring.data.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.repository.CityRepository;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@Path("/cities")
public class CityResource {

	@Autowired
	private CityRepository cityDAO;

	@Autowired
	private CountryRepository countryDAO;

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> findById() {
		List<City> result = new ArrayList<>();
		Optional.ofNullable(cityDAO.findAll()).ifPresent(cities -> result.addAll(cities));
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public City findById(@PathParam("id") Integer id) {
		City result = cityDAO.findById(id);
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public City update(City city) {

		if (Optional.ofNullable(city).isPresent() && Optional.ofNullable(city.getId()).isPresent()) {
			city = cityDAO.save(city);
		}

		return city;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public City insert(City city) {

		if (Optional.ofNullable(city).isPresent() && !Optional.ofNullable(city.getId()).isPresent()) {
			city = cityDAO.save(city);
		}

		return city;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {

		if (!Optional.ofNullable(id).isPresent()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		cityDAO.delete(id);

		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<City> findLikeName(@QueryParam("country") String countryName) {
		List<City> result = new ArrayList<>();
		Optional.ofNullable(countryDAO.findByNameContaining(countryName)).ifPresent((countries) -> {
			countries.forEach((country) -> {
				Optional.ofNullable(cityDAO.findByCountry(country)).ifPresent(cities -> result.addAll(cities));
			});
		});
		return result;
	}
}
