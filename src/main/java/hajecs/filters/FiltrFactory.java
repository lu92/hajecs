package hajecs.filters;

/**
 * Created by Radek on 2015-05-24.
 */
public class FiltrFactory {

    public static AvailableWorkersFiltr getFiltr(TypeOfFiltr filtr) {
        if (filtr == TypeOfFiltr.DAYS_FILTR) {
            return new DaysFiltr();
        } else if (filtr == TypeOfFiltr.ROLE_FILTR) {
            return new RoleFiltr();
        } else if (filtr == TypeOfFiltr.DAYS_AND_ROLE_FILTR) {
            return new DayAndRoleFilter();
        } else
            return null;
    }
}
