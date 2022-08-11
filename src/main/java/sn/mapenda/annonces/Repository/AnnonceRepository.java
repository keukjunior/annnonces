package sn.mapenda.annonces.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.mapenda.annonces.Entity.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce,Long> {

}
