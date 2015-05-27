package hajecs.filters;

/**
 * Created by Radek on 2015-05-24.
 */
public class FiltrCriteriaImpl implements FiltrCriteria {

    private DaysCriteria daysCriteria;
    private RoleCriteria roleCriteria;

    public FiltrCriteriaImpl(DaysCriteria daysCriteria, RoleCriteria roleCriteria) {
        this.daysCriteria = daysCriteria;
        this.roleCriteria = roleCriteria;
    }

    public FiltrCriteriaImpl(){}

    public void setDaysCriteria(DaysCriteria daysCriteria) {
        this.daysCriteria = daysCriteria;
    }

    public void setRoleCriteria(RoleCriteria roleCriteria) {
        this.roleCriteria = roleCriteria;
    }

    @Override
    public DaysCriteria getDayCriteria() {
        return this.daysCriteria;
    }

    @Override
    public RoleCriteria getRoleCriteria() {
        return this.roleCriteria;
    }
}
