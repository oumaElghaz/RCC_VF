package ma.vivalis.BKAM_CDR_API1.client.batch.writer;

import generated.ComEnt;
import org.springframework.batch.infrastructure.item.xml.StaxEventItemWriter;
import org.springframework.batch.infrastructure.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientXmlWriterConfig {
    @Bean
    public StaxEventItemWriter<ComEnt.DonneesEnt> clientXmlWriter() {
        // Streaming XML : écrit directement sur disque, pas en mémoire
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(ComEnt.DonneesEnt.class);

        return new StaxEventItemWriterBuilder<ComEnt.DonneesEnt>()
                .name("clientXmlWriter")
                .resource(new FileSystemResource("output/clients_cdr.xml"))
                .marshaller(marshaller)
                .rootTagName("ComEnt")
                .overwriteOutput(true)
                .build();
    }
}
