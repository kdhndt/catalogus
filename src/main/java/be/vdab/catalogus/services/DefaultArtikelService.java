package be.vdab.catalogus.services;

import be.vdab.catalogus.domain.Artikel;
import be.vdab.catalogus.events.ArtikelGemaakt;
import be.vdab.catalogus.repositories.ArtikelRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class DefaultArtikelService implements ArtikelService {
    private final ArtikelRepository artikelRepository;
    //bean ter beschikking gesteld dankzij de Spring for RabbitMQ dependency
    private final AmqpTemplate template;

    DefaultArtikelService(ArtikelRepository artikelRepository, AmqpTemplate template) {
        this.artikelRepository = artikelRepository;
        this.template = template;
    }

    @Override
    public void create(Artikel artikel) {
        artikelRepository.save(artikel);
        //1. naam van de exchange naar waar het bericht wordt gestuurd op RabbitMQ
        //2. geavanceerd gebruik
        //3. object dat het bericht voorstelt en geconverteerd wordt naar JSON
        template.convertAndSend("catalogus", null, new ArtikelGemaakt(artikel));
    }
}
