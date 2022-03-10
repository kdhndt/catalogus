package be.vdab.catalogus.repositories;

import be.vdab.catalogus.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
}