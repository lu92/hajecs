package hajecs.notificationVisitor;

import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SeveralDaysTask;
import hajecs.model.Task.SingleTask;

/**
 * Created by Radek on 2015-05-24.
 */
public class AllocationOfTask implements Visitor {
    @Override
    public String visit(DailyTask dailyTask) {
        StringBuilder builder = new StringBuilder();
        builder.append(" zostalo stworzone dla cibie nowe dzienne zadanie ");
        builder.append(" o nazwie " +dailyTask.getName());
        builder.append(" wiecej informacji "+ dailyTask.getDescribe());
        builder.append(" zadanie zaczyna sie i kończy "+ dailyTask.getStart().toString());
        builder.append(" życze powodzenia");
        return builder.toString();
    }

    @Override
    public String visit(HourlyTask hourlyTask) {
        StringBuilder builder = new StringBuilder();
        builder.append(" zostalo stworzone dla cibie nowe dzienne zadanie z kilkoma podzadaniami");
        builder.append(" o nazwie " + hourlyTask.getName());
        builder.append(" wiecej informacji "+ hourlyTask.getDescribe());
        builder.append(" zadanie zaczyna sie i kończy "+ hourlyTask.getStart().toString());
        builder.append(" zadanie posiada kilka podzadań ");
        for(SingleTask singleTask:hourlyTask.getSingleTaskStorage()){
            builder.append(" podzadanie "+ singleTask.getTask()+ " przwidywany czas wykonania w godzinach "+ singleTask.getDuration());
        }
        builder.append(" życze powodzenia");
        return builder.toString();
    }

    @Override
    public String visit(SeveralDaysTask severalDaysTask) {
        StringBuilder builder = new StringBuilder();
        builder.append("zostalo stworzone dla cibie nowe kilkudniowe zadanie ");
        builder.append(" o nazwie " +severalDaysTask.getName());
        builder.append(" wiecej informacji " + severalDaysTask.getDescribe());
        builder.append(" zadanie zaczyna " + severalDaysTask.getStart().toString());
        builder.append(" oraz konczy się " + severalDaysTask.getDeadline().toString());
        builder.append(" życze powodzenia");
        return builder.toString();
    }
}
