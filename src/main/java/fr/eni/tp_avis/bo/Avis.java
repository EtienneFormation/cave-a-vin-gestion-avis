package fr.eni.tp_avis.bo;

import java.time.LocalDateTime;

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

@Document(collection = "reviews")
public class Avis {
	@Id
	private String id;
	
	@Field(name = "rating")
	private int note;
	
	@Field(name = "commentary")
	private String commentaire;
	
	@Field(name = "date")
	private LocalDateTime date;
	
	private Client client;
}
