package ferramong.scheduler.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/*
 * Entity
 *    Sera mapeada para uma tabela do banco de dados
 */

@Entity
@Getter
@Setter
public class Scheduler implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
}
