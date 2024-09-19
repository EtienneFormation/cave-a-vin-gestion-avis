package fr.eni.tp_avis.dal;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.tp_avis.bo.Avis;
import fr.eni.tp_avis.bo.Bouteille;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "avis", collectionResourceRel = "avis")
public interface AvisRepository extends MongoRepository<Avis, String> {
	// Niveau 1
	List<Avis> findByNoteLessThan(int note);
	List<Avis> findByNoteGreaterThanEqual(int note);
	List<Avis> findByBouteilleId(int id);
	
	// Niveau 2
	List<Avis> findByClientPseudo(String pseudo);
	List<Avis> findByClientQuantiteCommandeeGreaterThan(int quantite);

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	List<Avis> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
