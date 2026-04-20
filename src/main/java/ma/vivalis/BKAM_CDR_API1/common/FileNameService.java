package ma.vivalis.BKAM_CDR_API1.common;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileNameService {
    private static final String PATTERN = "yyyyMMdd.HHmmss";
    private static final String DATE_SYSTEM = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern(PATTERN));

    String fileName;

    public static String getFormattedDate() {
        return DATE_SYSTEM; // retourne toujours la même valeur
    }

    public String retournerFileNames(String typeFichier) {


        //LocalDateTime now = LocalDateTime.now();

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");

        String dateFormatee = getFormattedDate();

        fileName="RCC.BAM.415.415."+typeFichier+"."+ dateFormatee.replace("/","")+".xml";
        return fileName;
    }

}
