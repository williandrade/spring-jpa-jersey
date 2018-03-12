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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.repository.CountryRepository;

@Path("/countries")
public class CountryResource {

	@Autowired
	private CountryRepository countryDAO;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> findById() {
		List<Country> result = new ArrayList<>();
		Optional.ofNullable(countryDAO.findAll()).ifPresent(countries -> result.addAll(countries));
		return result;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Country findById(@PathParam("id") Integer id) {
		Country result = countryDAO.findById(id);
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Country update(Country Country) {

		if (Optional.ofNullable(Country).isPresent() && Optional.ofNullable(Country.getId()).isPresent()) {
			Country = countryDAO.save(Country);
		}

		return Country;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Country insert(Country Country) {

		if (Optional.ofNullable(Country).isPresent() && !Optional.ofNullable(Country.getId()).isPresent()) {
			Country = countryDAO.save(Country);
		}

		return Country;
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Integer id) {

		if (!Optional.ofNullable(id).isPresent()) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		countryDAO.delete(id);

		return Response.ok().build();
	}
}
