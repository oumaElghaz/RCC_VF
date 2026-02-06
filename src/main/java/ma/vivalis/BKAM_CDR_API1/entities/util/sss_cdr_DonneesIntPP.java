package ma.vivalis.BKAM_CDR_API1.entities.util;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;

import java.util.Date;


@Entity
@Table(name = "sss_cdr_DonneesIntPP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_DonneesIntPP {
    @Id
    private String idPrincipal;
    private String tpIdPrincipal;
    private String prenom;
    private String nomFamille;
    private String paysDelivrance;
    private Date dtDelivrance;
    private Date dtExpiration;
    private String TypePPPro;
    private String RNAE;
    private Date dtNaissance;
    private String codLocalNaissance;
    private String sexe;
    private String nationalite;
    private String sitFamille;
    private String codCatProf;
    private Integer menage;
    private String qualAcadem;
    private String catClient;


    @ManyToOne
    @JoinColumn(name = "id_client")
    private sss_cdr_client_stat client;

}
