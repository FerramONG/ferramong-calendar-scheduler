package ferramong.scheduler.services;

import ferramong.scheduler.entities.Scheduler;
import ferramong.scheduler.repositories.SchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Service
 *      Terá a lógica principal
 *      Não tem a ver com modelagem de dados
 *      Não faz comunicação direta com bd (quem faz é 'repositories')
 *		Não é um estado (por outro lado, 'models' são)
 *		Não modela dados (quem faz isso são os 'models')
 */

@Service
@AllArgsConstructor
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;

    public List<Scheduler> getDatesWithScheduling(Date start, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTime(end);
        c.add(Calendar.DATE, 1);

        return schedulerRepository.getDatesWithScheduling(start, c.getTime());
    }

    public List<Scheduler> getSchedulerForDay(Date date) {
        return schedulerRepository.getSchedulerForDay(date);
    }

    public List<Scheduler> getAllById(int idDweller) {
        return schedulerRepository.getAllById(idDweller);
    }

    public Scheduler getById(int idDweller) throws Exception {
        // TODO refactor
        var scheduledDates = schedulerRepository.getById(idDweller);

        if (scheduledDates.isEmpty())
            throw new Exception();

        return scheduledDates.get(0);
    }

    public boolean schedule(Scheduler scheduling) {
        try {
            schedulerRepository.save(scheduling);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean unschedule(int idDweller) {
        int rowsAffected = schedulerRepository.unschedule(idDweller);

        return (rowsAffected > 0);
    }
}
