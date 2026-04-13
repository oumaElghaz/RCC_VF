package ma.vivalis.BKAM_CDR_API1.common;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class FileNameService {

    String fileName;

    public String retournerFileNames(String typeFichier) {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatee = date.format(formatter);

        fileName="RCC.BAM.415.415."+typeFichier+"."+ dateFormatee.replace("/","")+".xml";
        return fileName;
    }
}
