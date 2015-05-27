package hajecs.notificationVisitor;

import hajecs.model.Actors.Person;
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Radek on 2015-05-24.
 */
@NodeEntity
public class Notification {

    @GraphId
    private Long id;
    private int notReadMessages = 0;
    private int lastMessage =1;

    @Fetch
    @RelatedTo(type = "PERSON_NOTIFICATION", direction = Direction.BOTH)
    private Person notificationOwner;

    @Fetch
    @GraphProperty
    private Set<SingleNotificationMessage> notificationMessages = new HashSet<>();

    public Notification(Person notificationOwner) {
        this.notificationOwner = notificationOwner;
    }

    public Notification(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNotReadMessages() {
        return notReadMessages;
    }

    public void setNotReadMessages(int notReadMessages) {
        this.notReadMessages = notReadMessages;
    }

    public Set<SingleNotificationMessage> getNotificationMessages() {
        return notificationMessages;
    }

    public void setNotificationMessages(Set<SingleNotificationMessage> notificationMessages) {
        this.notificationMessages = notificationMessages;
    }

    public void addNewNotification(String message){
        this.notificationMessages.add(new SingleNotificationMessage(message,this.lastMessage++));
        this.notReadMessages++;
    }

    public void markAsRead(int simpleNotificationId){
        for (SingleNotificationMessage message: this.notificationMessages){
            if(message.getId() == simpleNotificationId){
                message.markAsRead();
                this.notReadMessages--;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", notReadMessages=" + notReadMessages +
                ", lastMessage=" + lastMessage +
                ", notificationMessages=" + notificationMessages +
                '}';
    }
}
