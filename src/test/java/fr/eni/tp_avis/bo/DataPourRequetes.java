package fr.eni.tp_avis.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.tp_avis.dal.AvisRepository;
import fr.eni.tp_avis.dal.BouteilleRepository;

@SpringBootTest
class DataPourRequetes {

	@Autowired
	BouteilleRepository bouteilleRepository;

	@Autowired
	AvisRepository avisRepository;

	void insertion_Bouteille_DB() {
		final List<Bouteille> listeBouteilles = new ArrayList<>();
		// Création de 3 Bouteille

		listeBouteilles.add(Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(18298)
						.idRegion(3)
						.idCouleur(1)
						.build())
				.nom("Vin ENI Edition")
				.build());

		listeBouteilles.add(Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(1298)
						.idRegion(3)
						.idCouleur(2)
						.build())
				.nom("Vin ENI Service")
				.build());

		listeBouteilles.add(Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(1999)
						.idRegion(2)
						.idCouleur(3)
						.build())
				.nom("Vin ENI Ecole")
				.build());

		listeBouteilles.forEach(b -> {
			bouteilleRepository.save(b);
		});
	}

	void insertion_Avis_DB() {
		// Récupération depuis la base des Bouteille
		final List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
		assertThat(listeBouteilles).isNotNull();
		assertThat(listeBouteilles).isNotEmpty();
		assertThat(listeBouteilles.size()).isEqualTo(3);

		// Liste de Client
		final List<Client> listeClients = new ArrayList<>();
		// Création de 3 Client
		listeClients.add(Client
				.builder()
				.pseudo("bobeponge@email.fr")
				.quantiteCommandee(11)
				.build());
		listeClients.add(Client
				.builder()
				.pseudo("patricketoile@email.fr")
				.quantiteCommandee(12)
				.build());
		listeClients.add(Client
				.builder()
				.pseudo("carlotentacule@email.fr")
				.quantiteCommandee(25)
				.build());

		// Ajout d'Avis par Client sur chaque Bouteille
		// Faire varier la note
		int note = 2;
		
		
		for (Client c : listeClients) {
			//Faire varier la date :
			LocalDateTime ldf = LocalDateTime.of(2023, 7, 13, 15, 28);
			//Attention, en base l'heure sera en GMT (Heure Française - 2)

			for (int i = 0; i < listeBouteilles.size(); i++) {
				final Bouteille b = listeBouteilles.get(i);

				// Faire varier la quantite du Client selon la note
				c.setQuantiteCommandee(c.getQuantiteCommandee() * note);
				final Avis avis = Avis
						.builder()
						.note(note)
						.commentaire("Commentaire (" + note + ")")
						.bouteille(b)
						.client(c)
						.date(ldf)
						.build();
				// Sauvegarde de Avis
				avisRepository.save(avis);
				// incrémenter la date
				ldf = ldf.plusDays(10);
			}
			// incrémenter la note
			note++;
		}
	}

	@BeforeEach
	void insertion_DB() {
		avisRepository.deleteAll();
		bouteilleRepository.deleteAll();
		
		insertion_Bouteille_DB();
		final List<Bouteille> listeBouteilles = bouteilleRepository.findAll();
		assertThat(listeBouteilles).isNotNull();
		assertThat(listeBouteilles).isNotEmpty();
		assertThat(listeBouteilles.size()).isEqualTo(3);

		insertion_Avis_DB();
		final List<Avis> listeAvis = avisRepository.findAll();
		assertThat(listeAvis).isNotNull();
		assertThat(listeAvis).isNotEmpty();
		assertThat(listeAvis.size()).isEqualTo(9);
	}
	
	@Test
	void test_note_inferieure_3() {
		List<Avis> avis = avisRepository.findByNoteLessThan(3);
		
		System.out.println("Le nombre d'avis avec une note < 3 est de : " + avis.size());
		System.out.println(avis);
	}
	
	@Test
	void test_note_superieure_3() {
		List<Avis> avis = avisRepository.findByNoteGreaterThanEqual(3);
		
		System.out.println("Le nombre d'avis avec une note > 3 est de : " + avis.size());
		System.out.println(avis);
	}
	
	@Test
	void test_par_bouteille() {
		Bouteille bouteille = Bouteille
				.builder()
				.id(BouteilleId
						.builder()
						.idBouteille(18298)
						.idRegion(3)
						.idCouleur(1)
						.build())
				.nom("Vin ENI Edition")
				.build();
		
		List<Avis> avis = avisRepository.findByBouteille(bouteille);
		System.out.println("Avis pour la bouteille 18298 : " + avis.size());
		System.out.println(avis);
	}
	
	@Test
	void test_pseudo() {
		List<Avis> avis = avisRepository.findByClientPseudo("bobeponge@email.fr");
		System.out.println("Avis par bob leponge : " + avis.size());
		System.out.println(avis);
	}
	
	@Test
	void test_quantite_100() {
		List<Avis> avis = avisRepository.findByClientQuantiteCommandeeGreaterThan(100);
		System.out.println("Qte > 100 : " + avis.size());
		System.out.println(avis);
	}
	
	@Test
	void test_date_2023_07_13() {
		List<Avis> avis = avisRepository.findByDateBetween(LocalDateTime.of(2023, 7, 13, 0, 0), LocalDateTime.of(2023, 7, 14, 0, 0));
		System.out.println("Date le 13-07-2023 : " + avis.size());
		System.out.println(avis);
	}
}


