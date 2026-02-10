package ma.vivalis.BKAM_CDR_API1.generationXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlGenerator {
    public static void generateXml(Object jaxbObject, String filePath) {
        try {
            // 1. Créer le contexte JAXB avec la classe racine
            JAXBContext context = JAXBContext.newInstance(jaxbObject.getClass());

            // 2. Créer le Marshaller (convertit objet -> XML)
            Marshaller marshaller = context.createMarshaller();

            // 3. Formatage joli XML (indenté)
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // 4. Générer le fichier XML
            marshaller.marshal(jaxbObject, new File(filePath));

            System.out.println("XML généré avec succès : " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
