package hajecs.notificationVisitor;

/**
 * Created by Radek on 2015-05-24.
 */
public class SingleNotificationMessage {
    private boolean isRead;
    private String message;
    private int id;

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
