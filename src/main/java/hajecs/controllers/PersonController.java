package hajecs.controllers;

import hajecs.filters.CriteriaDTO;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.*;
import hajecs.notificationVisitor.SingleNotificationMessage;
import hajecs.repositories.PersonRepository;
import hajecs.resources.PersonResource;
import hajecs.services.PersonService;
import hajecs.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by lucjan on 21.05.15.
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    Logger logger = Logger.getLogger(String.valueOf(this));

    @ResponseBody
    @RequestMapping(value = "/createPerson", method = RequestMethod.POST)
    public Response createPerson(@RequestBody PersonFormDTO personFormDTO) {
        personService.addPerson(personFormDTO);
        return new Response("created new Person");
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public PersonDTOInfo login(@RequestBody LoginDataDTO loginDataDTO) {
        PersonDTOInfo personDTOInfo = null;
        try {
            personDTOInfo = DTOConverter.toPersonDTOInfo(personService.loginToSystem(loginDataDTO));
        } catch (Exception e) {
            personDTOInfo = new PersonDTOInfo();
            personDTOInfo.setUsername("Fake");
            personDTOInfo.setPassword("Fake");
        }
        return personDTOInfo;

    }


    @ResponseBody
    @RequestMapping(value = "/doFiltr", method = RequestMethod.POST)
    Set<PersonDTOInfo> doFiltr(@RequestBody CriteriaDTO criteriaDTO) {
        Set<PersonDTOInfo> personDTOInfos = new HashSet<>();
        for (Person properPerson : personService.doFiltr(criteriaDTO))
            personDTOInfos.add(DTOConverter.toPersonDTOInfo(properPerson));
        return personDTOInfos;
    }

    @ResponseBody
    @RequestMapping(value = "/getPerson/{personId}", method = RequestMethod.GET)
    public PersonDTOInfo getPerson(@PathVariable("personId") long personId) {
        return DTOConverter.toPersonDTOInfo(personService.getPerson(personId));
    }

    @ResponseBody
    @RequestMapping(value = "/getAllPersons", method = RequestMethod.GET)
    public Set<PersonDTOInfo> getAllPersons() {
        return personService.getAllPersonDtoInfos();
    }

    @ResponseBody
    @RequestMapping(value = "/deletePerson/{personId}", method = RequestMethod.DELETE)
    public Response deletePerson(@PathVariable("personId") long personId) {
        personService.deletePerson(personId);
        return new Response("Person was deleted");
    }

    @ResponseBody
    @RequestMapping(value = "/addRoleToPerson/{personId}/{roleId}", method = RequestMethod.GET)
    public Response addRoleToPerson(@PathVariable("personId") long personId, @PathVariable("roleId") long roleId) {
        try {
            personService.addRoleToPerson(personId, roleId);
        } catch (Exception e) {
            return new Response("Invalid Data");
        }
        return new Response("Add role to person successful");
    }

    @ResponseBody
    @RequestMapping(value = "/getFakePerson", method = RequestMethod.GET)
    public PersonDTOInfo getFakePerson() {
        return DTOConverter.toPersonDTOInfo(PersonResource.getJavaDeveloperAdamWojcik());
    }


    @RequestMapping(value = "/insertFakePerson", method = RequestMethod.GET)
    public Person insertFakePerson() {
        personRepository.deleteAll();
        personRepository.save(PersonResource.getJavaDeveloperAdamWojcik());
        return personRepository.findByUsernameAndPassword(
                PersonResource.getJavaDeveloperAdamWojcik().getUsername(),
                PersonResource.getJavaDeveloperAdamWojcik().getPassword());
    }

    @RequestMapping(value = "/notification/{person_id}",method = RequestMethod.GET)
    public Set<SingleNotificationMessage> notificationMessages(@PathVariable("person_id") long person_id){
        return personService.getPerson(person_id).getNotificationMessages();
    }
    @RequestMapping(value = "/readNotification/{person_id}/{mess_id}")
    public void markNotificatonAsRead(@PathVariable("person_id") long personId, @PathVariable("mess_id") long mesId){
        Person p = personService.getPerson(personId);
        p.markAsRead(mesId);
        personRepository.save(p);
    }

}
