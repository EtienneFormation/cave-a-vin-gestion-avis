package fr.eni.tp_avis.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp_avis.dal.BouteilleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestBouteille {
	@Autowired
	private BouteilleRepository repository;
	
	private static BouteilleId id;
	
	@BeforeAll
	static void BeforeAll() {
		id = BouteilleId.builder()
				.idBouteille(2298)
				.idRegion(5)
				.idCouleur(1)
				.build();
	}
	
	@Test
	void test01_ajouter_bouteille() {
		Bouteille bouteille = Bouteille.builder()
				.id(id)
				.nom("Vin Blanc ENI")
				.build();
		
		Bouteille bouteilleInseree = repository.save(bouteille);
		
		assertThat(bouteilleInseree.getId()).isNotNull();
		assertThat(bouteilleInseree.getId().getIdBouteille()).isNotNull();
		assertThat(bouteilleInseree.getId().getIdRegion()).isNotNull();
		assertThat(bouteilleInseree.getId().getIdCouleur()).isNotNull();
		
		assertThat(bouteilleInseree.getNom()).isNotBlank();
		assertThat(bouteilleInseree.getNom()).isEqualTo("Vin Blanc ENI");
		
		log.info(bouteilleInseree.toString());
	}
	
	@Test
	void test02_selectionner_bouteille() {
		Optional<Bouteille> bouteilleOpt = repository.findById(id);
		
		assertThat(bouteilleOpt).isNotNull();
		assertThat(bouteilleOpt.isPresent()).isTrue();
		
		Bouteille resultat = bouteilleOpt.get();
		
		assertThat(resultat.getId()).isNotNull();
		assertThat(resultat.getId().getIdBouteille()).isNotNull();
		assertThat(resultat.getId().getIdRegion()).isNotNull();
		assertThat(resultat.getId().getIdCouleur()).isNotNull();
		
		assertThat(resultat.getNom()).isNotBlank();
		assertThat(resultat.getNom()).isEqualTo("Vin Blanc ENI");
		
		log.info(resultat.toString());
	}
	
	@Test
	void test03_selectionner_mauvais_id() {
		Optional<Bouteille> bouteilleOpt = repository.findById(
				BouteilleId.builder()
				.idBouteille(2296)
				.idRegion(5)
				.idCouleur(1)
				.build()
		);
		
		assertThat(bouteilleOpt).isNotNull();
		assertThat(bouteilleOpt.isPresent()).isFalse();
		
		bouteilleOpt = repository.findById(
				BouteilleId.builder()
				.idBouteille(2298)
				.idRegion(6)
				.idCouleur(1)
				.build()
		);
		
		assertThat(bouteilleOpt).isNotNull();
		assertThat(bouteilleOpt.isPresent()).isFalse();
		
		bouteilleOpt = repository.findById(
				BouteilleId.builder()
				.idBouteille(2298)
				.idRegion(5)
				.idCouleur(2)
				.build()
		);
		
		assertThat(bouteilleOpt).isNotNull();
		assertThat(bouteilleOpt.isPresent()).isFalse();
	}
}
