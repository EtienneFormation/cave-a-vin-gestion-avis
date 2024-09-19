package fr.eni.tp_avis.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.tp_avis.bo.Bouteille;

public interface BouteilleRepository extends MongoRepository<Bouteille, Integer> {

}
