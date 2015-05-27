package hajecs.notificationVisitor;

/**
 * Created by Radek on 2015-05-24.
 */
public class VisitorFactory {
    public static Visitor getVisitor(VisitorType type){
        if(type==VisitorType.ALLOCATION_TASK){
            return new AllocationOfTask();
        }
        else if(type == VisitorType.DELETE_TASK){
            return new DeleteTask();
        }
        else if(type == VisitorType.EXECUTE_TASK){
            return new ExecutedTask();
        }
        else return null;
    }
}
