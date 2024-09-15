package fr.eni.tp_avis.dal;

import org.springframework.data.mongodb.repository.MongoRepository;

import fr.eni.tp_avis.bo.Avis;

public interface AvisRepository extends MongoRepository<Avis, String> {

}
