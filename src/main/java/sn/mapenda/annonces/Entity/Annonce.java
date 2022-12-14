package sn.mapenda.annonces.Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity

public class Annonce implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
	
   private Long idannonce;
   private String titre;
   private String description;
   private String photo;
   public Annonce() {
	super();
   }
   public Annonce(String titre, String description, String photo) {
	super();
	this.titre = titre;
	this.description = description;
	this.photo = photo;
    }
    public String getTitre() {
	return titre;
    }
	public Long getIdannonce() {
		return idannonce;
	}
	public void setIdannonce(Long idannonce) {
		this.idannonce = idannonce;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
    


   
    
   


}
