package ferramong.scheduler.controllers;

import ferramong.scheduler.entities.Scheduler;
import ferramong.scheduler.services.SchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

/*
* Controller
*   Deve ter minimo de logica
*	Serve para chamar serviços
*	Mapeia endpoints
* */

@RestController
@AllArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;

    @RequestMapping("/listall/{name}")
    public List<Scheduler> listAllWithName(@PathVariable("name") @NotBlank String name) {
        return schedulerService.listAllWithName(name);
    }
}
