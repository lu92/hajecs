package hajecs.Resource;

import hajecs.builders.PersonBuilder;
import hajecs.model.Actors.Manager;
import hajecs.model.Actors.Person;
import hajecs.model.Actors.Worker;

/**
 * Created by lucjan on 13.05.15.
 */
public class PersonResource {

    public static Person getManagerJanKowalski() {
        PersonBuilder personBuilder = new PersonBuilder("Jan_Kowalski", "Jan_Kowalski", "jan.kowalski@gmail.com", Manager.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Jan", "Kowalski", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getManagerRole(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getJavaDeveloperKamilMilosz() {
        PersonBuilder personBuilder = new PersonBuilder("Kamil_Milosz", "Kamil_Milosz", "kamil_Milosz@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Kamil", "Milosz", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getJAVADeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getJavaDeveloperWojciechSeliga() {
        PersonBuilder personBuilder = new PersonBuilder("Wojciech_Seliga", "Wojciech_Seliga", "wojciech_seliga@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Wojciech", "Seliga", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getJAVADeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getJavaDeveloperPiotrNawalka() {
        PersonBuilder personBuilder = new PersonBuilder("Piotr_Nawalka", "Piotr_Nawalka", "piotr_nawalka@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Piotr", "Nawalka", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getJAVADeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getJavaDeveloperAdamWojcik() {
        PersonBuilder personBuilder = new PersonBuilder("Adam_Wojcik", "Adam_Wojcik", "adam.wojcik@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Adam", "Wojcik", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getJAVADeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getDatabaseDeveloperAdrianCiecholewski() {
        PersonBuilder personBuilder = new PersonBuilder("Adrian_Ciecholewski", "Adrian_Ciecholewski", "adrian.ciecholewski@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Adrian", "Ciecholewski", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getDataBaseDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getDatabaseDeveloperLukaszDebinski() {
        PersonBuilder personBuilder = new PersonBuilder("Lukasz_Debinski", "Lukasz_Debinski", "lukasz.debinski@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Lukasz", "Debinski", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getDataBaseDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getDatabaseDeveloperAdrianKrawiec() {
        PersonBuilder personBuilder = new PersonBuilder("Adrian_Krawiec", "Adrian_Krawiec", "adrian.krawiec@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Adrian", "Krawiec", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getDataBaseDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getWebDeveloperMateuszStepala() {
        PersonBuilder personBuilder = new PersonBuilder("Mateusz_Stepaka", "Mateusz_Stepala", "mateusz.stepala@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Mateusz", "Stepala", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getWebDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getWebDeveloperDominikNocon() {
        PersonBuilder personBuilder = new PersonBuilder("Dominik_Nocon", "Dominik_Nocon", "dominik.nocon@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Dominik", "Nocon", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getWebDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getWebDeveloperPrzemekRoman() {
        PersonBuilder personBuilder = new PersonBuilder("Przemek_Roman", "Przemek_Roman", "przemek.roman@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Przemyslaw", "Roman", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getWebDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }

    public static Person getUXDesignerMonikaStokrotka() {
        PersonBuilder personBuilder = new PersonBuilder("Monika_Stokrotka", "Monika_Stokrotka", "monika.Stokrotka@gmail.com", Worker.class);
        personBuilder.setAddress("Poland", "Warsaw", "30-330 Warsaw");
        personBuilder.setPersonality("Monika", "Stokrotka", "20/04/1970", "123456789");
        personBuilder.setRoles(
                RoleResource.getJAVADeveloper(),
                RoleResource.getWebDeveloper(),
                RoleResource.getWorkerRole());
        return personBuilder.getBuildResult();
    }


}
