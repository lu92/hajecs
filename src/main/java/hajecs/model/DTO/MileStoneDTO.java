package hajecs.model.DTO;

/**
 * Created by lucjan on 21.05.15.
 */
public class MileStoneDTO {
    private String name;
    private String describe;


    public MileStoneDTO() {
    }

    public MileStoneDTO(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
