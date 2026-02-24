package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_arch_client_stat;


import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "DonneesIntPP_Arch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonneesIntPP_Arch {
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
