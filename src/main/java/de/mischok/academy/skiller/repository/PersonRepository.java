package de.mischok.academy.skiller.repository;

import de.mischok.academy.skiller.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {


    @Query(value = "SELECT p FROM Person p WHERE p.country in :countries")
    List<Person> getAllPersonsFromCountries(@Param("countries") String... countries);

    @Query(value= "select distinct p.country from Person p where p.country is not null")
    List<String> getAllCountries();


    @Query(value= "select p from Person p where p.birthday >= '1980-1-1' and p.birthday < '1990-1-1'")
    List<Person> getAllPersonsBornInTheEighties();

}
