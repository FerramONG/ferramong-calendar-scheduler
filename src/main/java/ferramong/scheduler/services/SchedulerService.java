package ferramong.scheduler.services;

import ferramong.scheduler.entities.Scheduler;
import ferramong.scheduler.repositories.SchedulerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Scheduler> listAllWithName(String name) {
        return schedulerRepository.listAllWithName(name);
    }

    public boolean unschedule(int idDweller) {
        int rowsAffected = schedulerRepository.unschedule(idDweller);

        return (rowsAffected > 0);
    }

}
