package hajecs.notificationVisitor;

import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SeveralDaysTask;

/**
 * Created by Radek on 2015-05-24.
 */
public class DeleteTask implements Visitor {
    @Override
    public String visit(DailyTask task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Zadanie "+ task.getId());
        stringBuilder.append(" o nazwie "+task.getName());
        stringBuilder.append(" które mialo sie rozpocząc "+ task.getStart());
        stringBuilder.append(" zostalo wycofane ");
        return stringBuilder.toString();
    }

    @Override
    public String visit(HourlyTask task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Zadanie "+ task.getId());
        stringBuilder.append(" o nazwie "+task.getName());
        stringBuilder.append(" które mialo sie rozpocząc "+ task.getStart());
        stringBuilder.append(" zostalo wycofane ");
        return stringBuilder.toString();
    }

    @Override
    public String visit(SeveralDaysTask task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Zadanie "+ task.getId());
        stringBuilder.append(" o nazwie "+task.getName());
        stringBuilder.append(" które mialo sie rozpocząc "+ task.getStart());
        stringBuilder.append(" zostalo wycofane ");
        return stringBuilder.toString();
    }
}
