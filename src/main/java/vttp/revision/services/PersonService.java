package vttp.revision.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import vttp.revision.models.Person;

// Service class to return list of people/add people
@Service
public class PersonService {
    private List<Person> persons = new ArrayList<Person>();

    public PersonService() {
        persons.add(new Person("Mark", "Zuckerburg"));
        persons.add(new Person("Elon", "Musk"));

    }
    public List<Person> getPersons() {
        return this.persons;
    }

    public void addPerson(Person p) {
        persons.add(new Person(p.getFirstName(), p.getLastName()));
    }
}
