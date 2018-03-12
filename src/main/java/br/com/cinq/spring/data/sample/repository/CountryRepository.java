package br.com.cinq.spring.data.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cinq.spring.data.sample.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Country findById(Integer id);

	public List<Country> findByNameContaining(String name);

	@Query("Select c from Country c where c.name like %:name%")
	public List<Country> findLikeName(@Param("name") String name);

}
