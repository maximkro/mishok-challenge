package de.mischok.academy.skiller.service;

import de.mischok.academy.skiller.domain.Person;
import de.mischok.academy.skiller.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }


    public List<Person> getAllPersonsWithComment() {
        List<Person> list = new ArrayList<>();
        for (Person p : this.personRepository.findAll()) {
            if (p.getComment() != null && !p.getComment().isBlank() && !p.getComment().isEmpty()) {
                list.add(p);
            }
        }
        return list;
    }

    public List<Person> getAllPersonsFromCountries(String... countries) {
        return this.personRepository.getAllPersonsFromCountries(countries);
    }

    public List<String> getAllCountries() {
        return this.personRepository.getAllCountries();
    }

    public List<Person> getAllPersonsBornInTheEighties() {
        return this.personRepository.getAllPersonsBornInTheEighties();
    }

    public Map<String, Long> getPersonCountByCountry(){
        Map<String, Long> map = new HashMap();
        List<Person> peopleTmp = this.personRepository
                .findAll()
                .stream()
                .filter((p) -> p.getCountry() != null)
                .collect(Collectors.toList());
        peopleTmp.stream().map(Person::getCountry).forEach((country) -> {
            map.put(country, 0L);
        });
        peopleTmp.forEach((p) -> {
            long count = (Long)map.get(p.getCountry());
            map.put(p.getCountry(), count + 1L);
        });
        return map;
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }
}
