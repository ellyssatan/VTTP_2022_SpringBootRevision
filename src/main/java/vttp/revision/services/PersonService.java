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

    // return selected person
    public Person getPerson(String id) {
        for (Person p : persons) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public String updatePerson(Person p) {
        boolean pExists = false;
        for (Person curr : persons) {
            if (curr.getId().equals(p.getId())) {
                pExists = true;
                curr.setFirstName(p.getFirstName());
                curr.setLastName(p.getLastName());
            }
        }
        if (!pExists) {
            persons.add(p);
            return "Person added";
        }
        return "Person updated";
    }

    public String deletePerson(Person p) {
        boolean pExists = false;
        for (Person curr : persons){
          if (curr.getId().equals(p.getId())){
            pExists = true;
            persons.remove(curr);
            break;
          }
        }
        if (pExists){
          return "Person deleted!";
        }
        return "No Person deleted";
    }
}


