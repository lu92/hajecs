package hajecs.services;

import hajecs.model.Actors.Person;
import hajecs.model.DTO.LoginDataDTO;
import hajecs.model.DTO.PersonFormDTO;
import hajecs.model.personalData.Address;
import hajecs.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lucjan on 21.05.15.
 */

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person loginToSystem(LoginDataDTO loginDataDTO) {

        Person persondb = personRepository.findByUsernameAndPassword(
                loginDataDTO.getUsername(), loginDataDTO.getPassword());

        return persondb;
    }

    @Override
    public Person addPerson(PersonFormDTO personFormDTO) {
        return null;
    }

    @Override
    public String deletePerson(long id) {
        return null;
    }

    @Override
    public String changeAddress(long id, Address address) {
        return null;
    }

    @Override
    public String addRole(long id, long role) {
        return null;
    }

    @Override
    public Person getPerson(long id) {
        return null;
    }


}
