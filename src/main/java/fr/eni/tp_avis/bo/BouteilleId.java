package fr.eni.tp_avis.bo;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class BouteilleId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Field(name = "bottle_id")
	private int idBouteille;
	
	@Field(name = "region_id")
	private int idRegion;
	
	@Field(name = "color_id")
	private int idCouleur;
}
