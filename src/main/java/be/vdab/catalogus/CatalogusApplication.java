package be.vdab.catalogus;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

//op regelmatige tijdstippen code uitvoeren
@EnableScheduling
@SpringBootApplication
public class CatalogusApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogusApplication.class, args);
    }

    //we sturen een bericht vanuit Java NAAR RabbitMQ bij een succesvolle toevoeging van een Artikel
    //Java object wordt default door Spring geconverteerd naar een bericht met serialization (= niet leesbaar in RabbitMQ of door niet-Java applicaties)
    //met deze bean converteert Spring dan Java object naar JSON ipv serialization
    @Bean
    Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
