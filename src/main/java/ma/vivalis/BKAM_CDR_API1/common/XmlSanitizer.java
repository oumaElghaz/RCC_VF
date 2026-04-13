package ma.vivalis.BKAM_CDR_API1.common;

public class XmlSanitizer {
    public static String removeInvalidXmlChars(String input) {
        if (input == null) return null;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // Garder seulement les caractères valides selon la spec XML 1.0
            // Il existe aussi des regex mais elles ne gèrent pas bien tous les cas Unicode/BMP
            if (c == 0x9 || c == 0xA || c == 0xD ||
                    (c >= 0x20 && c <= 0xD7FF) ||
                    (c >= 0xE000 && c <= 0xFFFD)) {
                out.append(c);
            }
            // Sinon, ignorer le caractère (le supprimer donc)
        }
        return out.toString();
    }
}
