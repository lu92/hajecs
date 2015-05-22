package hajecs.controllers;

import hajecs.model.Actors.Person;
import hajecs.model.DTO.PersonFormDTO;
import hajecs.repositories.PersonRepository;
import hajecs.resources.PersonResource;
import hajecs.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

/**
 * Created by lucjan on 21.05.15.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonRepository personRepository;

    Logger logger = Logger.getLogger(String.valueOf(this));

    @RequestMapping(value = "/addPerson", method = RequestMethod.POST)
    @ResponseBody
    public String addPerson(@RequestBody PersonFormDTO personFormDTO) {
        personService.addPerson(personFormDTO);
        logger.info(personFormDTO.toString());
        logger.info("new Person was added to system");
        return "created new Person";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable("id") long id) {
        logger.info("get Person from Database with id " + id);
        return personRepository.findOne(id);
//        return personService.getPerson(id);
    }

    @RequestMapping(value = "/insertFakePerson", method = RequestMethod.GET)
    public Person insertFakePerson() {
        personRepository.deleteAll();
        personRepository.save(PersonResource.getJavaDeveloperAdamWojcik());
        return personRepository.findByUsernameAndPassword(
                PersonResource.getJavaDeveloperAdamWojcik().getUsername(),
                PersonResource.getJavaDeveloperAdamWojcik().getPassword());
    }


}
