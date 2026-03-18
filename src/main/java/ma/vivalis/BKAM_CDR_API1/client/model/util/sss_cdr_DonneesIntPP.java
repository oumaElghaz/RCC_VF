package ma.vivalis.BKAM_CDR_API1.client.model.util;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "sss_cdr_DonneesIntPP")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_DonneesIntPP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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




}
