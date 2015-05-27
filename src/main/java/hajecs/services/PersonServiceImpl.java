package hajecs.services;

import hajecs.filters.*;
import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.DTOConverter;
import hajecs.model.DTO.LoginDataDTO;
import hajecs.model.DTO.PersonDTOInfo;
import hajecs.model.DTO.PersonFormDTO;
import hajecs.model.personalData.Address;
import hajecs.model.personalData.Role;
import hajecs.repositories.PersonRepository;
import hajecs.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public Person loginToSystem(LoginDataDTO loginDataDTO) {
        Person persondb = null;
        try {
            persondb = personRepository.findByUsernameAndPassword(
                    loginDataDTO.getUsername(), loginDataDTO.getPassword());
        } catch (Exception e) {
        }
        return persondb;
    }

    @Override
    public Person addPerson(PersonFormDTO personFormDTO) {
        return personRepository.save(DTOConverter.toPerson(personFormDTO));
    }

    @Override
    public Manager addManager(PersonFormDTO personFormDTO) {
        return personRepository.save((Manager) DTOConverter.toPerson(personFormDTO));
    }

    @Override
    public void deletePerson(long personId) {
        personRepository.delete(personId);
    }


    @Override
    public boolean addRoleToPerson(long personId, long roleId) throws IllegalArgumentException{
        Person person = null;
        Role role = null;

        try {
            person = personRepository.findOne(personId);
        }catch (Exception e) {
            throw new IllegalArgumentException("person with id = " + personId + "doesn't exist");
        }

        try {
            role = roleRepository.findOne(roleId);
        }catch (Exception e) {
            throw new IllegalArgumentException("role with id = " + role + "doesn't exist");
        }

        person.addRoles(role);
        personRepository.save(person);
        return true;
    }

    @Override
    public Person getPerson(long personId) {
        return personRepository.findOne(personId);
    }

    @Override
    public Set<Person> getAllPersons() {
        Set<Person> personSet = new HashSet<>();
        for (Person person : personRepository.findAll())
            personSet.add(person);
        return personSet;
    }

    @Override
    public Set<PersonDTOInfo> getAllPersonDtoInfos() {
        Set<PersonDTOInfo> personDTOInfos = new HashSet<>();
        for (Person person : getAllPersons())
            personDTOInfos.add(DTOConverter.toPersonDTOInfo(person));
        return personDTOInfos;
    }

    @Override
    public Set<Person> doFiltr(CriteriaDTO criteriaDTO) {
        AvailableWorkersFiltr availableWorkersFiltr = FiltrFactory.getFiltr(criteriaDTO.getTypeOfFiltr());
        FiltrCriteriaImplBuilder builder = new FiltrCriteriaImplBuilder(criteriaDTO, roleService.getAllRoles());
        return availableWorkersFiltr.getAvaialbeWorkers(getAllPersons(), builder.buldFiltrCriteriaImpl());
    }
}
