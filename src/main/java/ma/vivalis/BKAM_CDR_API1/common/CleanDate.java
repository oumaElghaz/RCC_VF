package ma.vivalis.BKAM_CDR_API1.common;

import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Field;
import java.util.Collection;

@Component
public class CleanDate {

    public void cleanDates(Object obj) throws IllegalAccessException {
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
