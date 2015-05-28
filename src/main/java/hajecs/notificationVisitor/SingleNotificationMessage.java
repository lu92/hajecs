package hajecs.notificationVisitor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hajecs.model.Actors.Person;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Created by Radek on 2015-05-24.
 */
@NodeEntity
public class SingleNotificationMessage {
    private boolean isRead;
    private String message;
    private int id;
    @GraphId
    private Long bd_id;


    @JsonIgnore
    @Fetch
    @RelatedTo(type = "Mess",direction = Direction.BOTH)
    private Person person;

    public SingleNotificationMessage(String message, int id) {
        this.isRead = false;
        this.message = message;
        this.id = id;
    }

    public SingleNotificationMessage(){}

    public boolean isRead() {
        return isRead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    public Long getBd_id() {
        return bd_id;
    }

    public void setBd_id(Long bd_id) {
        this.bd_id = bd_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void markAsRead(){
        this.isRead = true;
    }

    @Override
    public String toString() {
        return "SingleNotificationMessage{" +
                "isRead=" + isRead +
                ", message='" + message + '\'' +
                ", id=" + id +
                '}';
    }




}
