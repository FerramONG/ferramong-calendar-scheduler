package ferramong.scheduler.controllers;

import ferramong.scheduler.entities.Scheduler;
import ferramong.scheduler.services.SchedulerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/*
* Controller
*   Deve ter minimo de logica
*	Serve para chamar serviços
*	Mapeia endpoints
* */
@CrossOrigin(methods = {GET, POST, PUT, DELETE})
@RestController
@AllArgsConstructor
public class SchedulerController {

    private final SchedulerService schedulerService;

    /**
     * Gets scheduled visits within a date range.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-scheduler.herokuapp.com/scheduler/dates/2021-03-27/2021-03-28"
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
     *      curl "https://ferramong-scheduler.herokuapp.com/scheduler/2021-03-26"
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
     *      curl "https://ferramong-scheduler.herokuapp.com/scheduler/dweller/123456789"
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
     *      curl "https://ferramong-scheduler.herokuapp.com/scheduler/dweller/123456789/last"
     * </code>
     *
     * @param       idDweller Dweller's id
     *
     * @return      JSON with the dweller's schedules with the informed id
     */
    @GetMapping("/scheduler/dweller/{idDweller}/last")
    public Date getById(@PathVariable("idDweller") int idDweller) {
        // TODO refactor
        try {
            return ResponseEntity.ok().body(schedulerService.getById(idDweller));
        }
        catch (Exception e) {
            ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(204).build();
    }

    /**
     * Schedules a visit.
     *
     * <h2>CURL example</h2>
     * <code>
     *      curl "https://ferramong-scheduler.herokuapp.com/scheduler" \
     *      -X POST \
     *      -d "{\n  \"idDweller\": \"123456789\", \n  \"date\": \"2021-03-26T10:35:00.000Z\"\n}" \
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
