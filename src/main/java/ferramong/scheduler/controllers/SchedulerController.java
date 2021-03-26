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
import javax.ws.rs.PathParam;
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

    /**
     * Gets scheduled visits within a date range.
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      JSON with scheduled visits in the interval [start; end]
     */
    @GetMapping("/scheduler/dates/{start}/{end}")
    public List<Scheduler> getDatesWithScheduling(@PathVariable("start")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                  @PathVariable("end")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        return schedulerService.getDatesWithScheduling(start, end);
    }

    /**
     * Gets scheduled visits for a specific day.
     *
     * @param       date Day whose visits will be obtained
     *
     * @return      JSON with scheduled visits in the interval [start; end]
     */
    @GetMapping("/scheduler/{date}")
    public List<Scheduler> getSchedulerForDay(@PathVariable("date")
                                              @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
        return schedulerService.getSchedulerForDay(date);
    }

    /**
     * Gets all schedules that a dweller did.
     *
     * @param       cpf Dweller's cpf
     *
     * @return      JSON with the dweller's schedules with the informed cpf
     */
    @GetMapping("/scheduler/dweller/{cpf}")
    public List<Scheduler> getByCPF(@PathVariable("cpf") String cpf) {
        return schedulerService.getByCPF(cpf);
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
            value = "/scheduler",
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
            value = "/scheduler",
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
