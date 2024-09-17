package fr.eni.tp_avis.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp_avis.dal.AvisRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestAssociationAvisClient {

	@Autowired
	AvisRepository avisRepository;

	@Test
	void test01_save_avis_client() {
		final Client client = Client
				.builder()
				.pseudo("bobeponge@email.fr")
				.quantiteCommandee(11)
				.build();
		
		final Avis avis = Avis
				.builder()
				.note(4)
				.commentaire("Doux. A déguster frais.")
				.date(LocalDateTime.now())
				.client(client) // Association
				.build();
				
		final Avis avisDB = avisRepository.save(avis);
		
		// Vérifier que l'identifiant n'est pas nul
		assertThat(avisDB.getId()).isNotNull();
		assertThat(avisDB.getId()).isNotBlank();

		// Vérifier que la référence embarquée existe
		final Client clientDB = avisDB.getClient();
		assertThat(clientDB).isNotNull();
		assertThat(clientDB).isEqualTo(client);
		
		log.info(avisDB.toString());
	}
}
