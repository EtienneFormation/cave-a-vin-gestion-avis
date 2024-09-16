package fr.eni.tp_avis.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.tp_avis.bo.Bouteille;
import fr.eni.tp_avis.bo.BouteilleId;

public interface BouteilleRepository extends MongoRepository<Bouteille, BouteilleId> {

}
