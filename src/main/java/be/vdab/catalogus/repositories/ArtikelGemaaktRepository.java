package be.vdab.catalogus.repositories;

import be.vdab.catalogus.events.ArtikelGemaakt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikelGemaaktRepository extends JpaRepository<ArtikelGemaakt, Long> {
}
