package ferramong.scheduler.repositories;

import ferramong.scheduler.entities.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Repository
 *      Responsável por gerenciar queries do banco de dados
 */

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Integer> {

    @Query(
            "SELECT s FROM Scheduler s " +
            "WHERE s.name LIKE CONCAT(UPPER(:name), '%')"
    )
    public List<Scheduler> listAllWithName(
            @Param("name") String name
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
