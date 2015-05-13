package hajecs.Resource;

import hajecs.model.personalData.Privilege;
import hajecs.model.personalData.Role;

/**
 * Created by lucjan on 13.05.15.
 */
public final class RoleResource {


    public static Role getManagerRole() {
        Role role = new Role("Manager Role");
        role.addPrivileges(
                new Privilege("Create MileStones"),
                new Privilege("Set Worker To Task"),
                new Privilege("Manage MileStone")
        );
        return role;
    }

    public static Role getWorkerRole() {
        Role role = new Role("Worker Role");
        role.addPrivileges(
                new Privilege("Login To System"),
                new Privilege("Check Tasks")
        );
        return role;
    }


    public static Role getJAVADeveloper() {
        Role role = new Role("Java Developer Role");
        role.addPrivileges(
                new Privilege("Java"),
                new Privilege("Spring"),
                new Privilege("Hibernate"),
                new Privilege("J2EE")
        );
        return role;
    }

    public static Role getDataBaseDeveloper() {
        Role role = new Role("DataBase Developer Role");
        role.addPrivileges(
                new Privilege("Oracle"),
                new Privilege("SQL Server"),
                new Privilege("Neo4j")
        );
        return role;
    }

    public static Role getWebDeveloper() {
        Role role = new Role("Web Developer Role");
        role.addPrivileges(
                new Privilege("HTML5"),
                new Privilege("CSS3"),
                new Privilege("BootStrap"),
                new Privilege("AngularJS")
        );
        return role;
    }
}
