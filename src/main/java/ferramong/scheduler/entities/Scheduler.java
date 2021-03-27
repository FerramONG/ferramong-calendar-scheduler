package ferramong.scheduler.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date date;

    public Scheduler() {
    }
}
