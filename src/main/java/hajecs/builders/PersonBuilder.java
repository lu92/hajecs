package hajecs.builders;


import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.Actors.Student;
import hajecs.model.Actors.Worker;
import hajecs.model.personalData.Address;
import hajecs.model.personalData.Personality;
import hajecs.model.personalData.Role;

import java.util.Date;

/**
 * Created by lucjan on 30.03.15.
 */
public class PersonBuilder implements AbstractPersonBuilder{

    protected Person person;
    protected Class clazz;

    public PersonBuilder(String username,String password, String email, Class clazz) throws IllegalArgumentException {
        preparePersonFromClassType(username, password, email, clazz);
    }

    public PersonBuilder(String username,String password, String email, String type) throws IllegalArgumentException {
       preparePersonFromStringType(username, password, email, type);
    }

    public PersonBuilder(
            String username,String password, String email,
            Class clazz, Personality personality, Role role,
            Address address) throws IllegalArgumentException {

        preparePersonFromClassType(username, password, email, clazz);
        setPersonality(personality);
        setRoles(role);
        setAddress(address);
    }

    public PersonBuilder(
            String username,String password, String email,
            String type, Personality personality, Role role,
            Address address) throws IllegalArgumentException {

        preparePersonFromStringType(username, password, email, type);
        setPersonality(personality);
        setRoles(role);
        setAddress(address);
    }

    private  void preparePersonFromStringType(String username, String password, String email, String type) {
        String properTypeVales [] = {"Manager", "Student", "Worker"};
        Class PersonsTypes [] = {
                Manager.class, Student.class, Worker.class };

        int n = 0;

        for (int i=0; i<properTypeVales.length; i++) {
            if (properTypeVales[i].equalsIgnoreCase(type)) {
                preparePersonFromClassType(username, password, email, PersonsTypes[i]);
            }
            else n++;
        }

        if (n == properTypeVales.length)
            throw new IllegalArgumentException("constructor cannot set invalid name of Person Object");
    }

    private void preparePersonFromClassType(String username, String password, String email, Class clazz) throws IllegalArgumentException {
        this.clazz = clazz;
        switch (clazz.getName()) {
            case "hajecs.model.Actors.Manager":
                person = new Manager(username, password, email);
                break;
            case "hajecs.model.Actors.Student":
                person = new Student(username, password, email);
                break;
            case "hajecs.model.Actors.Worker":
                person = new Worker(username, password, email);
                break;
            default:
                throw new IllegalArgumentException("constructor cannot set invalid type of Person Object");
        }
    }


    @Override
    public void setPersonality(String name, String lastname, Date birth, String telephoneNumber) {
        person.setPersonality(new Personality(name, lastname, birth, telephoneNumber));
    }

    @Override
    public void setPersonality(String name, String lastname, String birth, String telephoneNumber){
        person.setPersonality( new Personality(name, lastname, birth, telephoneNumber));
    }

    @Override
    public void setPersonality(Personality personality) {
        person.setPersonality(personality);
    }

    @Override
    public void setRoles(String... roleNames) throws IllegalArgumentException {
        for (String roleName : roleNames) {
            this.person.getRoleStorage().add(new Role(roleName));
        }
    }

    @Override
    public void setRoles(Role... roles) {
        for (Role role : roles) {
            person.getRoleStorage().add(role);
        }
    }


    @Override
    public void setAddress(String country, String city, String zipCode) {
        person.setAddress(new Address(country, city, zipCode));
    }

    @Override
    public void setAddress(Address address) {
        person.setAddress(address);
    }

    @Override
    public Class getClassPersonType() {
        return clazz;
    }

    @Override
    public String getPersonType() {
        return clazz.getName();
    }

    @Override
    public Person getBuildResult() {
        return person;
    }

    @Override
    public void printPerson() {
        System.out.println(person);
    }
}
