package hajecs.notificationVisitor;

import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SeveralDaysTask;

/**
 * Created by Radek on 2015-05-24.
 */
public interface Visitor {
    String visit(DailyTask dailyTask);
    String visit(HourlyTask hourlyTask);
    String visit(SeveralDaysTask severalDaysTask);
}
