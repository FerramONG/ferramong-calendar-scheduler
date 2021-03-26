package ferramong.scheduler.repositories;

import ferramong.scheduler.entities.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repository
 *      Responsável por gerenciar queries do banco de dados
 */

/*
*  getByID(id do usuario) que retorne qual horário ele ja tem marcado se houver,
* um post que receba um horário e um id e salve isso no banco, e um post com id
* do usuario para deleção do horário dele.
*
*  códigos http, mas ja adianto que vou usar query parameters pra mandar o id da pessoa no getById,
*  e que por enquanto as respostas esperadas pensei em 200 no caso de ja haver a data e 204
* no caso de não haver data para aquela pessoa.
* */

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, Integer> {

    @Query(
            "SELECT s FROM Scheduler s " +
            "WHERE s.cpf LIKE CONCAT(UPPER(:cpf), '%')"
    )
    public List<Scheduler> listAllWithName(
            @Param("cpf") String cpf
    );

}
