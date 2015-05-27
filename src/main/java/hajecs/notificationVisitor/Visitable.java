package hajecs.notificationVisitor;

/**
 * Created by Radek on 2015-05-24.
 */
public interface Visitable {
    String accept(Visitor visitor);
}
