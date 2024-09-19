package fr.eni.tp_avis.bo;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp_avis.dal.AvisRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestDocument {

	@Autowired
	AvisRepository avisRepository;

	@Test
	void test01_save_avis() {
		Avis avis = Avis
				.builder()
				.note(4)
				.commentaire("Vin délicieux")
				.date(LocalDateTime.now())
				.build();
		Avis avisDB = avisRepository.save(avis);
		
		//Vérifier que l'identifiant n'est pas nul
		assertThat(avisDB.getId()).isNotNull();
		assertThat(avisDB.getId()).isNotBlank();
		//Vérifier que tous les attributs sont remplis et non nuls
		assertThat(avisDB.getNote()).isNotNull();
		assertThat(avisDB.getNote()).isEqualTo(4);
		assertThat(avisDB.getCommentaire()).isNotNull();
		assertThat(avisDB.getCommentaire()).isNotBlank();
		assertThat(avisDB.getDate()).isNotNull();

		System.out.println(avisDB.toString());
	}

	@Test
	void test02_findAll_avis() {
		List<Avis> listeAvis = avisRepository.findAll();
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		System.out.println(listeAvis.toString());
	}
}
