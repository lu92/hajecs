package hajecs.filters;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Radek on 2015-05-24.
 */
public class CriteriaDTO {
    private TypeOfFiltr typeOfFiltr;
    private Set<Long> roles = new HashSet<>();
    private String beginTaskDate;
    private String endTaskDate;

    public CriteriaDTO(String beginTaskDate, String endTaskDate, Set<Long> roles) {
        this.beginTaskDate = beginTaskDate;
        this.endTaskDate = endTaskDate;
        this.roles = roles;
    }

    public CriteriaDTO(TypeOfFiltr typeOfFiltr, String beginTaskDate, String endTaskDate, Set<Long> roles) {
        this.typeOfFiltr = typeOfFiltr;
        this.beginTaskDate = beginTaskDate;
        this.endTaskDate = endTaskDate;
        this.roles = roles;
    }

    public CriteriaDTO() {
    }

    public Set<Long> getRoles() {
        return roles;
    }

    public void setRoles(Set<Long> roles) {
        this.roles = roles;
    }

    public String getBeginTaskDate() {
        return beginTaskDate;
    }

    public void setBeginTaskDate(String beginTaskDate) {
        this.beginTaskDate = beginTaskDate;
    }

    public String getEndTaskDate() {
        return endTaskDate;
    }

    public void setEndTaskDate(String endTaskDate) {
        this.endTaskDate = endTaskDate;
    }

    public TypeOfFiltr getTypeOfFiltr() {
        return typeOfFiltr;
    }

    public void setTypeOfFiltr(TypeOfFiltr typeOfFiltr) {
        this.typeOfFiltr = typeOfFiltr;
    }

    public CriteriaDTO getFakeData(){
        Set<Long> longs = new HashSet<>();
        longs.add(1L);
        longs.add(2L);
        return new CriteriaDTO("2015/05/05","2015/05/11",longs);
    }
}
