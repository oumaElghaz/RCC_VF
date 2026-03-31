package ma.vivalis.BKAM_CDR_API1.API.model;

public class MyRequestBody {
    private String versionNotice;
    private String fichier;


    // Constructeurs
    public MyRequestBody() {}
    public MyRequestBody(String versionNotice, String fichier) {
        this.versionNotice = versionNotice;
        this.fichier = fichier;
    }

    // Getters et setters
    public String getVersionNotice() { return versionNotice; }
    public void setVersionNotice(String versionNotice) { this.versionNotice = versionNotice; }
    public String getFichier() { return fichier; }
    public void setFichier(String fichier) { this.fichier = fichier; }
}
