package hajecs.services;

import hajecs.model.Actors.Person;
import hajecs.model.DTO.LoginDataDTO;
import hajecs.model.DTO.PersonFormDTO;
import hajecs.model.personalData.Address;

/**
 * Created by lucjan on 21.05.15.
 */
public interface PersonService {
    Person loginToSystem(LoginDataDTO loginDataDTO);
    Person addPerson(PersonFormDTO personFormDTO);
    String deletePerson(long id);
    String changeAddress(long id, Address address);
    String addRole(long id, long role);
    Person getPerson(long id);
}
