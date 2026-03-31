package ma.vivalis.BKAM_CDR_API1.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class XmlToZipBase64 {

    public static String convertXmlToZippedBase64(String xmlFilePath) throws IOException {

        // 1. Lire le fichier XML en bytes
        byte[] xmlBytes = Files.readAllBytes(Paths.get(xmlFilePath));

        // 2. Compresser en ZIP (en mémoire)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);

        ZipEntry zipEntry = new ZipEntry("file.xml"); // nom du fichier dans le zip
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(xmlBytes);
        zipOutputStream.closeEntry();
        zipOutputStream.close();

        // 3. Encoder le ZIP en Base64
        byte[] zippedBytes = byteArrayOutputStream.toByteArray();
        String base64Encoded = Base64.getEncoder().encodeToString(zippedBytes);

        return base64Encoded;
    }
}
