package hajecs.notificationVisitor;

import hajecs.Person;
import hajecs.model.Actors.Worker;
import hajecs.model.Task.DailyTask;
import hajecs.model.Task.HourlyTask;
import hajecs.model.Task.SeveralDaysTask;

/**
 * Created by Radek on 2015-05-24.
 */
public class ExecutedTask implements Visitor {
    @Override
    public String visit(DailyTask dailyTask) {
        StringBuilder builder = new StringBuilder();
        builder.append(" Zadanie "+dailyTask.getId());
        builder.append(" o nazwie "+dailyTask.getName());
        builder.append(" wykonane przez ");
        for(hajecs.model.Actors.Person person: dailyTask.getWorkerStorage()){
            builder.append(person.getPersonality().getName()+" "+person.getPersonality().getLastname());
        }
        builder.append(" zostalo zakonczone w dniu "+dailyTask.getEnd());
        if(dailyTask.getEnd().before(dailyTask.getDeadline())){
            builder.append(" Zadanie zostalo wykonane przed czasem!!! ");
        }
        if(dailyTask.getEnd().after(dailyTask.getDeadline())){
            builder.append(" zadanie zostalo wykonane po przewidywanym czasie zakonczenia ");
        }
        return builder.toString();
    }

    @Override
    public String visit(HourlyTask task) {
        StringBuilder builder = new StringBuilder();
        builder.append(" Zadanie "+task.getId());
        builder.append(" o nazwie "+task.getName());
        builder.append(" wykonane przez ");
        for(hajecs.model.Actors.Person person: task.getWorkerStorage()){
            builder.append(person.getPersonality().getName()+" "+person.getPersonality().getLastname());
        }
        builder.append(" zostalo zakonczone w dniu " + task.getEnd());
        if(task.getEnd().after(task.getDeadline())){
            builder.append(" zadanie zostalo wykonane po przewidywanym czasie zakonczenia");
        }
        return builder.toString();
    }

    @Override
    public String visit(SeveralDaysTask task) {
        StringBuilder builder = new StringBuilder();
        builder.append(" Zadanie "+task.getId());
        builder.append(" o nazwie "+task.getName());
        builder.append(" wykonane przez ");
        for(hajecs.model.Actors.Person person: task.getWorkerStorage()){
            builder.append(person.getPersonality().getName()+" "+person.getPersonality().getLastname());
        }
        builder.append(" zostalo zakonczone w dniu "+task.getEnd());
        if(task.getEnd().before(task.getDeadline())){
            builder.append(" Zadanie zostalo wykonane przed czasem!!!");
        }
        if(task.getEnd().after(task.getDeadline())){
            builder.append(" zadanie zostalo wykonane po przewidywanym czasie zakonczenia");
        }
        return builder.toString();
    }
}
