package fr.eni.tp_avis.dal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.tp_avis.bo.Avis;
import fr.eni.tp_avis.bo.Bouteille;

public interface AvisRepository extends MongoRepository<Avis, String> {
	// Niveau 1
	List<Avis> findByNoteLessThan(int note);
	List<Avis> findByNoteGreaterThanEqual(int note);
	List<Avis> findByBouteille(Bouteille bouteille);
	
	// Niveau 2
	List<Avis> findByClientPseudo(String pseudo);
	List<Avis> findByClientQuantiteCommandeeGreaterThan(int quantite);
	List<Avis> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
