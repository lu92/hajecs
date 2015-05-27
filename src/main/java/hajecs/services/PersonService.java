package hajecs.services;

import hajecs.filters.CriteriaDTO;
import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.DTO.LoginDataDTO;
import hajecs.model.DTO.PersonDTOInfo;
import hajecs.model.DTO.PersonFormDTO;
import hajecs.model.personalData.Address;

import java.util.Set;

/**
 * Created by lucjan on 21.05.15.
 */
public interface PersonService {
    Person loginToSystem(LoginDataDTO loginDataDTO);
    Person addPerson(PersonFormDTO personFormDTO);
    Manager addManager(PersonFormDTO personFormDTO);
    void deletePerson(long personId);
    boolean addRoleToPerson(long personId, long roleId) throws IllegalArgumentException;
    Person getPerson(long personId);
    Set<Person> getAllPersons();
    Set<PersonDTOInfo> getAllPersonDtoInfos();
    Set<Person> doFiltr(CriteriaDTO criteriaDTO);

}
