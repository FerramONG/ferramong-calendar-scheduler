package ferramong.scheduler.repositories;

import ferramong.scheduler.entities.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/*
 * Repository
 *      Responsável por gerenciar queries do banco de dados
 */

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Integer> {

    @Query(
            "SELECT s FROM Scheduler s " +
            "WHERE s.date BETWEEN :start AND :end"
    )
    public List<Scheduler> getDatesWithScheduling(
            @Param("start") Date start,
            @Param("end") Date end
    );

    @Query(value=
            "SELECT s FROM Scheduler s " +
            "WHERE SUBSTRING(s.date, 1, 4) = SUBSTRING(:date, 1, 4) " +
            "AND SUBSTRING(s.date, 6, 2) = SUBSTRING(:date, 6, 2) " +
            "AND SUBSTRING(s.date, 9, 2) = SUBSTRING(:date, 9, 2)"
    )
    public List<Scheduler> getSchedulerForDay(
            @Param("date") Date date
    );

    @Query(value =
            "SELECT s FROM Scheduler s " +
            "WHERE s.idDweller = :idDweller " +
            "ORDER BY s.date DESC LIMIT 1",
            nativeQuery = true
    )
    public List<Scheduler> getById(
            @Param("idDweller") int idDweller
    );

    @Query(
            "SELECT s FROM Scheduler s " +
            "WHERE s.idDweller = :idDweller"
    )
    public List<Scheduler> getAllById(
            @Param("idDweller") int idDweller
    );

    @Transactional
    @Modifying
    @Query(value=
            "INSERT INTO Scheduler " +
            "(idDweller, date) " +
            "VALUES (:idDweller, :date)",
            nativeQuery = true
    )
    public int schedule(
            @Param("idDweller") int idDweller,
            @Param("date") Date date
    );

    @Transactional
    @Modifying
    @Query(value=
            "DELETE FROM Scheduler " +
                    "(idDweller) " +
                    "VALUES (:idDweller)",
            nativeQuery = true
    )
    public int unschedule(
            @Param("idDweller") int idDweller
    );
}
