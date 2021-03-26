package ferramong.scheduler.controllers;

import ferramong.scheduler.entities.Scheduler;
import ferramong.scheduler.models.Person;
import ferramong.scheduler.models.Post;
import ferramong.scheduler.services.SchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.Member;
import java.net.URI;
import java.util.*;

/*
* Controller
*   Deve ter minimo de logica
*	Serve para chamar serviços
*	Mapeia endpoints
* */
@CrossOrigin
@RestController
@AllArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;

    @RequestMapping("/listall/{name}")
    public List<Scheduler> listAllWithName(@PathVariable("name") @NotBlank String name) {
        return schedulerService.listAllWithName(name);
    }

    /**
     * Gets scheduled visits within a date range.
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      JSON with scheduled visits in the interval [start; end]
     */
    //@RequestMapping("/listall/{name}")
    public List<Scheduler> getDatesWithScheduling(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        List<Scheduler> l = new ArrayList<>();
        return l;
    }

    /**
     * Gets scheduled visits for a specific day.
     *
     * @param       day Day whose visits will be obtained
     *
     * @return      JSON with scheduled visits in the interval [start; end]
     */
    //@RequestMapping("/listall/{name}")
    public List<Scheduler> getSchedulerForDay(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date day) {
        List<Scheduler> l = new ArrayList<>();
        return l;
    }

    /**
     * Gets all schedules that a dweller did.
     *
     * @param       cpf Dweller's cpf
     *
     * @return      JSON with the dweller's schedules with the informed cpf
     */
    //@RequestMapping("/listall/{name}")
    public List<Scheduler> getByCPF(String cpf) {
        List<Scheduler> l = new ArrayList<>();
        return l;
    }

    /**
     * Schedules a visit.
     *
     * <h2>CURL example<h2/>
     * <code>
     *      curl "http://localhost:8080/schedule" \
     *      -X POST \
     *      -d "{\n  \"cpf\": \"12345678900\", \n  \"date\": \"2021-03-26T10:35:00.000Z\"\n}" \
     *      -H "Content-type: application/json"
     * </code>
     *
     * @param       scheduling Scheduling information
     *
     * @return      JSON with response code
     */
    @CrossOrigin
    @PostMapping(
            value = "/schedule",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Scheduler> schedule(@RequestBody Scheduler scheduling) {
        if (!schedulerService.schedule(scheduling)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }

    // Matheus fará
    /**
     * Schedules a visit.
     *
     * <h2>CURL example<h2/>
     * <code>
     *      curl "http://localhost:8080/schedule" \
     *      -X DELETE \
     *      -d "{\n  \"cpf\": \"12345678900\", \n  \"date\": \"2021-03-26T10:35:00.000Z\"\n}" \
     *      -H "Content-type: application/json"
     * </code>
     *
     * @param       scheduling Scheduling information
     *
     * @return      JSON with response code
     */
    @CrossOrigin
    @DeleteMapping(
            value = "/schedule",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Scheduler> unschedule(@RequestBody Scheduler scheduling) {
        if (!schedulerService.unschedule(scheduling)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }
}
