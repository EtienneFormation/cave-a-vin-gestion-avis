package fr.eni.tp_avis.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp_avis.dal.AvisRepository;
import fr.eni.tp_avis.dal.BouteilleRepository;

@SpringBootTest
public class TestAssociationAvisBouteille {
	@Autowired
	private AvisRepository avisRepository;
	
	@Autowired
	private BouteilleRepository bouteilleRepository;
	
	@Test
	void test01_inserer_avis_bouteille() {
		Optional<Bouteille> bouteilleOpt = bouteilleRepository.findById(2298);
		assertThat(bouteilleOpt.isPresent()).isTrue();
		
		Bouteille bouteille = bouteilleOpt.get();
		
		Avis avis = Avis.builder()
				.note(5)
				.commentaire("Doux. A deguster frais.")
				.date(LocalDateTime.now())
				.client(Client.builder()
						.pseudo("bobeponge@email.fr")
						.quantiteCommandee(11)
						.build())
				.bouteille(bouteille) // association
				.build();
		
		final Avis avisDB = avisRepository.save(avis);
		
		// Vérifier que l'identifiant n'est pas nul
		assertThat(avisDB.getId()).isNotNull();
		assertThat(avisDB.getId()).isNotBlank();

		// Vérifier que la référence
		final Bouteille bouteilleDB = avisDB.getBouteille();
		assertThat(bouteilleDB).isNotNull();
		assertThat(bouteilleDB).isEqualTo(bouteille);

		System.out.println(avisDB.toString());
	}
}
