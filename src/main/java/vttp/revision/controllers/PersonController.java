package vttp.revision.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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


}