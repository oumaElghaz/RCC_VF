package ma.vivalis.BKAM_CDR_API1.entities.util;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;

import java.util.Date;


@Entity
@Table(name = "DonneesIntPP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonneesIntPP {
    @Id
    @Column(name = "idPrincipal")
    private String idPrincipal;
    @Column(name = "tpIdPrincipal")
    private String tpIdPrincipal;
    private String prenom;
    @Column(name = "nomFamille")
    private String nomFamille;
    @Column(name = "paysDelivrance")
    private String paysDelivrance;
    @Column(name = "dtDelivrance")
    private Date dtDelivrance;
    @Column(name = "dtExpiration")
    private Date dtExpiration;
    @Column(name = "TypePPPro")
    private String TypePPPro;
    private String RNAE;
    @Column(name = "dtNaissance")
    private Date dtNaissance;
    @Column(name = "codLocalNaissance")
    private String codLocalNaissance;
    private String sexe;
    private String nationalite;
    @Column(name = "sitFamille")
    private String sitFamille;
    @Column(name = "codCatProf")
    private String codCatProf;
    private Integer menage;
    @Column(name = "qualAcadem")
    private String qualAcadem;
    @Column(name = "catClient")
    private String catClient;


    @ManyToOne
    @JoinColumn(name = "id_client")
    private sss_cdr_snapshot_client_stat client;

}
