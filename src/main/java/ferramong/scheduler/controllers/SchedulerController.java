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
     * <h2>CURL example</h2>
     * <code>
     *      curl "http://localhost:8080/scheduler/dates/2021-03-27/2021-03-28"
     * </code>
     *
     * @param       start Start range
     * @param       end End range
     *
     * @return      Scheduled visits in the interval [start; end]
     */
    @GetMapping("/scheduler/dates/{start}/{end}")
    public List<Scheduler> getDatesWithScheduling(@PathVariable("start")
                                                  @DateTimeFormat(pattern="yyyy-MM-dd") Date start,
                                                  @PathVariable("end")
                                                  @DateTimeFormat(pattern="yyyy-MM-dd") Date end) {
        return schedulerService.getDatesWithScheduling(start, end);
    }

    /**
     * Gets scheduled visits for a specific day.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "http://localhost:8080/scheduler/2021-03-26"
     * </code>
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
     * <h2>CURL example</h2>
     * <code>
     *      curl "http://localhost:8080/scheduler/dweller/12345678900"
     * </code>
     *
     * @param       idDweller Dweller's id
     *
     * @return      JSON with the dweller's schedules with the informed id
     */
    @GetMapping("/scheduler/dweller/{idDweller}")
    public List<Scheduler> getAllById(@PathVariable("idDweller") int idDweller) {
        return schedulerService.getAllById(idDweller);
    }

    /**
     * Gets last visit that a dweller did.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "http://localhost:8080/scheduler/dweller/12345678900/last"
     * </code>
     *
     * @param       idDweller Dweller's id
     *
     * @return      JSON with the dweller's schedules with the informed id
     */
    @GetMapping("/scheduler/dweller/{idDweller}/last")
    public Date getById(@PathVariable("idDweller") int idDweller) {
        return schedulerService.getById(idDweller);
    }

    /**
     * Schedules a visit.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "http://localhost:8080/schedule" \
     *      -X POST \
     *      -d "{\n  \"idDweller\": \"12345678900\", \n  \"date\": \"2021-03-26T10:35:00.000Z\"\n}" \
     *      -H "Content-type: application/json"
     * </code>
     *
     * @param       scheduling Scheduling information
     *
     * @return Accepted request (202) if scheduling has been successful;
     * otherwise, returns bad request (400) if an error occurred
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

    /**
     * Unschedules a visit.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-scheduler.herokuapp.com/scheduler" \
     *      -X DELETE \
     *      -d "{\n  \"id\": \"123456789\"}" \
     *      -H "Content-type: application/json"
     * </code>
     *
     * @param       idDweller Dweller's id
     *
     * @return      Accepted request (202) if scheduling has been successful;
     * otherwise, returns bad request (400) if an error occurred
     */
    @CrossOrigin
    @DeleteMapping(
            value = "/scheduler",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<Scheduler> unschedule(@PathVariable("idDweller") int idDweller) {
        if (!schedulerService.unschedule(idDweller)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.accepted().build();
    }
}
