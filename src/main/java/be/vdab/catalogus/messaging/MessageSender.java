package be.vdab.catalogus.messaging;

import be.vdab.catalogus.repositories.ArtikelGemaaktRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageSender {
    private final ArtikelGemaaktRepository artikelGemaaktRepository;
    private final AmqpTemplate template;

    public MessageSender(ArtikelGemaaktRepository repository, AmqpTemplate template) {
        this.artikelGemaaktRepository = repository;
        this.template = template;
    }

    //wordt elke 5 sec uitgevoerd
    @Scheduled(fixedDelay = 5_000)
    @Transactional
    void verstuurMessages() {
        var artikelsGemaakt = artikelGemaaktRepository.findAll();
        artikelsGemaakt.forEach(
                gemaakt -> template.convertAndSend("catalogus", null, gemaakt)
        );
        artikelGemaaktRepository.deleteAllInBatch(artikelsGemaakt);
    }
}
