package vttp.revision.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vttp.revision.models.Person;
import vttp.revision.models.PersonForm;
import vttp.revision.services.PersonService;

@Controller
@RequestMapping(path="/")
public class PersonController {
    
    private List<Person> personList = new ArrayList<Person>();

    @Autowired
    PersonService pService;

    @Value("${welcome}")
    private String message;

    private String errorMessage = "Add First & Last Names";

    @GetMapping(value={"/", "/home", "/index"})
    public String index(Model model) {
        model.addAttribute("message", message);

        return "index"; 
    }

    @GetMapping(value="/testRetrieve", produces="application/json")
    public @ResponseBody List<Person> getAllPersons() {
        personList = pService.getPersons();

        return personList;
    }

    @GetMapping(value="/personList")
    public String personList(Model model) {
        personList = pService.getPersons();
        model.addAttribute("persons", personList);

        return "personList";
    }

    @GetMapping(value="/addPerson")
    public String showAddPerson(Model model) {

        PersonForm pForm = new PersonForm();
        
        model.addAttribute("personForm", pForm);
        return "addPerson";
    }

    @PostMapping(value="/addPerson")
    public String savePerson(Model model, @ModelAttribute("personForm")PersonForm personForm) {
        String fName = personForm.getFirstName();
        String lName = personForm.getLastName();

        if (fName != null && fName.length() > 0 && lName != null && lName.length() > 0) {
            Person p = new Person(fName, lName);
            pService.addPerson(p);
            return "redirect:/personList";
        }

        model.addAttribute("errorMessage", errorMessage);

        return "addPerson";
    }

    // Retrieve person info
    @GetMapping(value="/edit/{id}", produces="text/html")
    public String showEditPersonForm(@PathVariable("id") String id, Model model){
        Person p = pService.getPerson(id);
        System.out.printf("> id: %s\n", id);
        System.out.println(p);

        model.addAttribute("person", p);
        return "edit";
    }

    @PostMapping(value="/edit/{id}", produces="text/html")
    public String personInfo(@PathVariable("id") String id, @ModelAttribute("personForm") PersonForm personForm, Model model) {

        Person p = pService.getPerson(id);
        p.setFirstName(personForm.getFirstName());
        p.setLastName(personForm.getLastName());
        pService.updatePerson(p);
        
        model.addAttribute("Person", p);
        return "redirect:/personList";
    }

    @GetMapping(value="/deletePerson/{id}", produces="text/html")
    public String deletePerson(@PathVariable("id") String id) {
        Person p = pService.getPerson(id);
        pService.deletePerson(p);
        return "redirect:/personList";
    }

    // @GetMapping(value = "/{id}", produces = "text/html")
    // public String getContact(@PathVariable("id") String id, Model model) { 

    //     Person p = pService.getPerson(id);

    //     System.out.printf(">> id: %s", p);
    //     model.addAttribute("contact", p);
    //     return "edit";

    // }

    @GetMapping(value="/{id}", produces="application/json")
    public @ResponseBody Person getContact(@PathVariable("id") String id) { 

        Person p = pService.getPerson(id);

        System.out.printf(">> id: %s\n", id);
        System.out.println(p.toString());

        return p;

    }

}
