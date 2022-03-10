package be.vdab.catalogus.events;

import be.vdab.catalogus.domain.Artikel;

public class ArtikelGemaakt {
    private final long id;
    private final String naam;

    public ArtikelGemaakt(Artikel artikel) {
        this.id = artikel.getId();
        this.naam = artikel.getNaam();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
