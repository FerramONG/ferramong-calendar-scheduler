package ferramong.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Para testar, rode o projeto e no navegador digite: http://localhost:8080/listall/fulano
 * Para modificar o banco de dados de testes (H2 - em memória), modifique 'resources/data.sql'
 */
@SpringBootApplication
public class FerramongSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerramongSchedulerApplication.class, args);
	}

}
