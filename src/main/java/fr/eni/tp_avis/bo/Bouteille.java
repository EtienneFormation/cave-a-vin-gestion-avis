package fr.eni.tp_avis.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder

@Document(collection = "bottles")
public class Bouteille {
	@Id
	@Field(name = "bottle_id")
	private int id;

	@Field(name = "region_id")
	private int idRegion;

	@Field(name = "color_id")
	private int idCouleur;
	
	@Field(name = "name")
	private String nom;
}
