package fr.eni.tp_avis.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp_avis.dal.BouteilleRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestBouteille {
	@Autowired
	private BouteilleRepository repository;
	
	@Test
	void test01_ajouter_bouteille() {
		Bouteille bouteille = Bouteille.builder()
				.id(2298)
				.nom("Vin Blanc ENI")
				.build();
		
		Bouteille bouteilleInseree = repository.save(bouteille);
		
		assertThat(bouteilleInseree.getId()).isGreaterThan(0);
		
		assertThat(bouteilleInseree.getNom()).isNotBlank();
		assertThat(bouteilleInseree.getNom()).isEqualTo("Vin Blanc ENI");

		System.out.println(bouteilleInseree.toString());
	}
	
	@Test
	void test02_selectionner_bouteille() {
		Optional<Bouteille> bouteilleOpt = repository.findById(2298);
		
		assertThat(bouteilleOpt).isNotNull();
		assertThat(bouteilleOpt.isPresent()).isTrue();
		
		Bouteille resultat = bouteilleOpt.get();
		
		assertThat(resultat.getId()).isGreaterThan(0);
		
		assertThat(resultat.getNom()).isNotBlank();
		assertThat(resultat.getNom()).isEqualTo("Vin Blanc ENI");

		System.out.println(resultat.toString());
	}
	
	@Test
	void test03_selectionner_mauvais_id() {
		Optional<Bouteille> bouteilleOpt = repository.findById(2297);
		
		assertThat(bouteilleOpt).isNotNull();
		assertThat(bouteilleOpt.isPresent()).isFalse();
	}
}
