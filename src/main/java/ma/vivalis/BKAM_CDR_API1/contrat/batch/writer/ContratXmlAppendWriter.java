package ma.vivalis.BKAM_CDR_API1.contrat.batch.writer;

import generated.ComCon;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Component
public class ContratXmlAppendWriter implements ItemWriter<ComCon.Con> {
    private static final Logger log = LoggerFactory.getLogger(ContratXmlAppendWriter.class);
    private Marshaller marshaller;
    @Value("${batch.output.dir:output/}")
    private String outputDir;

    @Value("${batch.output.contrat.file:contrats_cdr.xml}")
    private String fileName;
    @PostConstruct
    public void init() throws Exception {
        JAXBContext context = JAXBContext.newInstance(ComCon.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // ⚠️ Ne PAS écrire le <?xml ...?> pour chaque DonneesEnt
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

    }
    @Override
    public void write(Chunk<? extends ComCon.Con> chunk) throws Exception {
        String filePath = outputDir + fileName;
        File file = new File(filePath);
        log.info("📁 Écriture dans : {} (existe: {}, taille avant: {} bytes)",
                filePath, file.exists(), file.length());

        // ✅ ComEnt LOCAL au chunk
        ComCon chunkComCon = new ComCon();

        for (ComCon.Con con : chunk) {
            if (con != null) {
                chunkComCon.getCon().add(con);

            }
        }

        if (chunkComCon.getCon().isEmpty()) {
            log.warn("⚠️ Aucun DonneesEnt à écrire !");
            return;
        }

        cleanEmptyStrings(chunkComCon);
        cleanDates(chunkComCon);

        StringWriter sw = new StringWriter();
        marshaller.marshal(chunkComCon, sw);

        String xml = sw.toString();
        log.info("📝 XML BRUT du marshaller :\n{}", xml);   // ✅ AJOUTER CETTE LIGNE

        String innerXml = extractDonneesEntOnly(xml);

        log.info("📝 XML extrait ({} caractères) :\n{}", innerXml.length(), innerXml);

        // ✅ APPEND mode
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(filePath, true), StandardCharsets.UTF_8)) {

            String indented = indentXml(innerXml, "      ");
            writer.write(indented);
            writer.flush();
        }

        log.info("✅ {} DonneesEnt ajoutés — taille fichier: {} bytes",
                chunk.size(), file.length());



    }
    private String extractDonneesEntOnly(String xml) {
        StringBuilder sb = new StringBuilder();

        String[] lines = xml.split("\n");

        for (String line : lines) {
            String trimmed = line.trim();

            // Ignorer les lignes <comEnt> et </comEnt>
            if (trimmed.startsWith("<comEnt") || trimmed.startsWith("</comEnt")) {
                continue;
            }

            // ✅ Convertir tag auto-fermant en tag ouvrant + fermant
            // <donneesEnt dtRefEnt="2025-03-28" actionType="EU"/>
            //   → <donneesEnt dtRefEnt="2025-03-28" actionType="EU">
            //     </donneesEnt>
            if (trimmed.startsWith("<donneesEnt") && trimmed.endsWith("/>")) {
                String openTag = trimmed.substring(0, trimmed.length() - 2) + ">";
                sb.append(openTag).append("\n");
                sb.append("</donneesEnt>").append("\n");
            } else if (!trimmed.isEmpty()) {
                sb.append(line).append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Ajoute une indentation à chaque ligne du XML.
     *
     * Avant :
     *   <DonneesEnt>
     *     <IdClient>CLI001</IdClient>
     *   </DonneesEnt>
     *
     * Après (indent = "      ") :
     *         <DonneesEnt>
     *           <IdClient>CLI001</IdClient>
     *         </DonneesEnt>
     */
    private String indentXml(String xml, String indent) {
        StringBuilder sb = new StringBuilder();
        for (String line : xml.split("\n")) {
            if (!line.isBlank()) {
                sb.append(indent).append(line).append("\n");
            }
        }
        return sb.toString();
    }


    public static void cleanEmptyStrings(Object obj) throws IllegalAccessException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (value instanceof String) {
                if (((String) value).trim().isEmpty()) {
                    field.set(obj, null);
                }
            }
        }
    }



  /*  public static void cleanDates(Object obj) throws IllegalAccessException {

        if (obj == null) return;

        Class<?> clazz = obj.getClass();

        //  Ne jamais toucher aux classes Java internes
        if (clazz.getPackageName().startsWith("java.")) {
            return;
        }

        for (Field field : clazz.getDeclaredFields()) {

            field.setAccessible(true);
            Object value = field.get(obj);

            if (value == null) continue;

            //  Si date XSD
            if (value instanceof XMLGregorianCalendar) {
                XMLGregorianCalendar date = (XMLGregorianCalendar) value;
                date.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
                date.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            }

            //  Si collection (List générée par XSD)
            else if (value instanceof Collection<?>) {
                for (Object item : (Collection<?>) value) {
                    cleanDates(item);
                }
            }

            //  Si objet généré par XSD (et pas Java interne)
            else if (!value.getClass().isPrimitive()
                    && !value.getClass().getPackageName().startsWith("java.")) {
                cleanDates(value);
            }
        }
    }*/



    public static void cleanDates(Object obj) throws IllegalAccessException {
        if (obj == null) return;

        Class<?> clazz = obj.getClass();

        // Ne jamais toucher aux classes Java internes
        if (clazz.getPackageName().startsWith("java.")) {
            return;
        }

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value == null) continue;

            // Si date XSD
            if (value instanceof XMLGregorianCalendar) {
                XMLGregorianCalendar cal = (XMLGregorianCalendar) value;

                // Supprimer tout ce qui n'est pas date (pour éviter 2026-08-24T00:00:00)
                cal.setHour(DatatypeConstants.FIELD_UNDEFINED);
                cal.setMinute(DatatypeConstants.FIELD_UNDEFINED);
                cal.setSecond(DatatypeConstants.FIELD_UNDEFINED);
                cal.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);

                // Optionnel: enlever timezone aussi
                cal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            }

            // Si collection (List générée par XSD)
            else if (value instanceof Collection<?>) {
                for (Object item : (Collection<?>) value) {
                    cleanDates(item);
                }
            }

            // Si objet généré par XSD (et pas Java interne)
            else if (!value.getClass().isPrimitive()
                    && !value.getClass().getPackageName().startsWith("java.")) {
                cleanDates(value);
            }
        }
    }
}
