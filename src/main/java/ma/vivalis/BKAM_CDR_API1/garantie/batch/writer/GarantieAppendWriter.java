package ma.vivalis.BKAM_CDR_API1.garantie.batch.writer;

import generated.ComGar;
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
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Component
public class GarantieAppendWriter implements ItemWriter<ComGar> {
    private static final Logger log = LoggerFactory.getLogger(GarantieAppendWriter.class);

    @Value("${batch.output.dir:output/}")
    private String outputDir;

    @Value("${batch.output.garantie.file:garantie_cdr.xml}")
    private String fileName;
    private Marshaller marshaller;

    @PostConstruct
    public void init() throws Exception {
        JAXBContext context = JAXBContext.newInstance(ComGar.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // ⚠️ Ne PAS écrire le <?xml ...?> pour chaque DonneesEnt
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

    }
    @Override
    public void write(Chunk<? extends ComGar> chunk) throws Exception {
        String filePath = outputDir + fileName;
        File file = new File(filePath);
        log.info("📁 Écriture dans : {} (existe: {}, taille avant: {} bytes)",
                filePath, file.exists(), file.length());


        for (ComGar com : chunk) {
            if (com != null) {
                cleanEmptyStrings(com);
                cleanDates(com);
                StringWriter sw = new StringWriter();
                marshaller.marshal(com, sw);
                String xml = sw.toString();
                log.info("📝 XML BRUT du marshaller :\n{}", xml);
                try (OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(filePath, true), StandardCharsets.UTF_8)) {

                    //String indented = indentXml(innerXml, "      ");
                    String indented = indentXml(xml, "      ");
                    writer.write(indented);
                    writer.flush();
                }
            }
        }

    }


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
        if (obj == null) {
            return;
        }

        // Ignore les objets du JDK/JRE, traitement seulement de vos beans
        Package objPackage = obj.getClass().getPackage();
        if (objPackage != null) {
            String packageName = objPackage.getName();
            // Exclure les types internes du JDK
            if (packageName.startsWith("java.") || packageName.startsWith("javax.")) {
                return;
            }
        }

        for (Field field : obj.getClass().getDeclaredFields()) {
            // On ne rend accessible que ce qui est "à nous"
            field.setAccessible(true);
            Object value = field.get(obj);

            if (value instanceof String) {
                if (((String) value).trim().isEmpty()) {
                    field.set(obj, null);
                }
            }
            // Optionnel : traitement récursif pour vos sous-objets/beans (décommentez si besoin)
        /*
        else if (value != null && !field.getType().isPrimitive() && !field.getType().isEnum()) {
            cleanEmptyStrings(value);
        }
        */
        }
    }





    public static void cleanDates(Object obj) throws IllegalAccessException {
        if (obj == null) return;
        Class<?> clazz = obj.getClass();
        if (clazz.getPackageName().startsWith("java.")) return;

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value == null) continue;

            // XMLGregorianCalendar
            if (value instanceof XMLGregorianCalendar) {
                XMLGregorianCalendar cal = (XMLGregorianCalendar) value;
                cal.setHour(DatatypeConstants.FIELD_UNDEFINED);
                cal.setMinute(DatatypeConstants.FIELD_UNDEFINED);
                cal.setSecond(DatatypeConstants.FIELD_UNDEFINED);
                cal.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
                cal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
            }
            // java.util.Date (et sous-classes)
            else if (value instanceof Date) {
                Date old = (Date) value;
                Calendar cal = Calendar.getInstance();
                cal.setTime(old);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                field.set(obj, cal.getTime());
            }
            // Traitement des String format date
            else if (value instanceof String) {
                String str = (String) value;
                // Si format "yyyy-MM-dd HH:mm:ss.S" ou "yyyy-MM-dd HH:mm:ss" ou "yyyy-MM-dd HH:mm:ss.0"
                // On ne touche qu'aux valeurs qui ressemblent à une date classique avec partie heure
                if (str.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(\\.\\d+)?")) {
                    // On garde que la partie yyyy-MM-dd
                    String clean = str.substring(0, 10);
                    field.set(obj, clean);
                }
            }
            // Collection (List, Set)
            else if (value instanceof Collection<?>) {
                for (Object item : (Collection<?>) value) {
                    cleanDates(item);
                }
            }
            // Objet métier non java.*
            else if (!value.getClass().isPrimitive()
                    && !value.getClass().getPackageName().startsWith("java.")) {
                cleanDates(value);
            }
        }
    }

}
