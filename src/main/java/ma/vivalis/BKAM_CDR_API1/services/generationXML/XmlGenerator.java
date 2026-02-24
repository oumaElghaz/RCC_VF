package ma.vivalis.BKAM_CDR_API1.services.generationXML;

import generated.ComEnt;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_stat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.logging.Logger;

public class XmlGenerator {

    private static final Logger logger = Logger.getLogger(XmlGenerator.class.getName());
    private static ComEnt globalComEnt = null;

    /**
     * Initialise le fichier avec Controle
     */
    public static void initializeXmlFileWithControle(String filePath, sss_cdr_client_stat client) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            String entObserv = escapeXmlAttribute(client != null ? client.getEntObserv() : "");
            String entDeclar = escapeXmlAttribute(client != null ? client.getEntDeclar() : "");
            String dtCreation = client != null && client.getDateExtraction() != null
                    ? escapeXmlAttribute(client.getDateExtraction().toString()) : "";

            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<RCC version=\"1.0\">\n" +
                    "  <controle entObserv=\"" + entObserv + "\" " +
                    "entDeclar=\"" + entDeclar + "\" " +
                    "dtCreation=\"" + dtCreation + "\"/>\n" +
                    "  <contenu>\n";
            fos.write(header.getBytes("UTF-8"));

            //  Initialiser le ComEnt global
            globalComEnt = new ComEnt();

            logger.info(" Fichier XML initialisé");
        } catch (Exception e) {
            logger.severe(" Erreur initialisation : " + e.getMessage());
            throw new RuntimeException("Erreur initialisation XML", e);
        }
    }

    /**
     *  CORRIGÉ : Accumule les DonneesEnt dans un ComEnt global
     * (ne marshale pas, juste accumule en mémoire)
     */
    public static void appendDonneesEntToXmlFile(String filePath, ComEnt comEnt) {
        try {
            if (comEnt == null || comEnt.getDonneesEnt() == null || comEnt.getDonneesEnt().isEmpty()) {
                logger.warning("ComEnt vide !");
                return;
            }

            int donneesCount = comEnt.getDonneesEnt().size();
            logger.info("Accumulation de " + donneesCount + " donneesEnt dans le buffer...");

            // Ajouter tous les DonneesEnt au ComEnt global
            for (ComEnt.DonneesEnt donnees : comEnt.getDonneesEnt()) {
                if (donnees != null) {
                    globalComEnt.getDonneesEnt().add(donnees);
                    logger.fine("DonneesEnt ajouté au buffer (total : " + globalComEnt.getDonneesEnt().size() + ")");
                } else {
                    logger.warning("DonneesEnt NULL détecté !");
                }
            }

            logger.info(" Batch accumulé (" + donneesCount + " DonneesEnt, total buffer : " + globalComEnt.getDonneesEnt().size() + ")");

        } catch (Exception e) {
            logger.severe(" Erreur append : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur ajout DonneesEnt", e);
        }
    }

    /**
     *  NOUVEAU : Marshale le ComEnt COMPLET une seule fois à la fin
     */
    public static void closeXmlFile(String filePath) {
        try {
            if (globalComEnt == null || globalComEnt.getDonneesEnt().isEmpty()) {
                logger.warning(" Aucun DonneesEnt à marshaler !");
                try (FileOutputStream fos = new FileOutputStream(filePath, true);
                     OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8")) {
                    String footer = "    <comEnt>\n    </comEnt>\n  </contenu>\n</RCC>";
                    osw.write(footer);
                }
                return;
            }

            logger.info(" Marshaling du ComEnt complet (" + globalComEnt.getDonneesEnt().size() + " DonneesEnt)...");

            //  Marshaler le ComEnt (qui possède @XmlRootElement)
            JAXBContext context = JAXBContext.newInstance(ComEnt.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            // Marshaler dans un StringWriter
            StringWriter sw = new StringWriter();
            marshaller.marshal(globalComEnt, sw);

            String xmlContent = sw.toString().trim();
            logger.info("ComEnt marshalisé (" + xmlContent.length() + " caractères)");

            //  Extraire SEULEMENT le contenu entre <comEnt> et </comEnt>
            int startIndex = xmlContent.indexOf(">") + 1;  // Après <comEnt>
            int endIndex = xmlContent.lastIndexOf("</comEnt>");

            String contentInside = "";
            if (startIndex > 0 && endIndex > startIndex) {
                contentInside = xmlContent.substring(startIndex, endIndex).trim();
            }

            //  Écrire dans le fichier
            try (FileOutputStream fos = new FileOutputStream(filePath, true);
                 OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
                 BufferedWriter bw = new BufferedWriter(osw)) {

                bw.write("    <comEnt>\n");

                // Indenter et ajouter le contenu
                if (!contentInside.isEmpty()) {
                    String[] lines = contentInside.split("\n");
                    for (String line : lines) {
                        if (!line.trim().isEmpty()) {
                            bw.write("      " + line + "\n");
                        }
                    }
                }

                bw.write("    </comEnt>\n");
                bw.write("  </contenu>\n");
                bw.write("</RCC>");
                bw.flush();
            }

            logger.info(" Fichier XML finalisé avec " + globalComEnt.getDonneesEnt().size() + " DonneesEnt");

            //  Nettoyer le buffer
            globalComEnt = null;

        } catch (Exception e) {
            logger.severe(" Erreur fermeture : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur fermeture XML", e);
        }
    }

    private static String escapeXmlAttribute(String value) {
        if (value == null) return "";
        return value
                .replace("&", "&amp;")
                .replace("\"", "&quot;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&apos;");
    }

    public static long getXmlFileSize(String filePath) {
        File file = new File(filePath);
        return file.exists() ? file.length() : 0;
    }

    public static boolean deleteXmlFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.delete();
    }
}