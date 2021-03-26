package ferramong.scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/*
 * Entity
 *    Sera mapeada para uma tabela do banco de dados
 */

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Scheduler implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private int idDweller;

    @Column(nullable = false)
    private Date date;

    public Scheduler() {
    }
}
