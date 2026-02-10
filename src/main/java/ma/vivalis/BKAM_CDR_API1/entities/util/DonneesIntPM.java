package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;

import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "DonneesIntPM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonneesIntPM {
    @Id
    private Long id;
    @Column(name = "raisonSocial")
    private String raisonSocial;
    private String sigle;
    @Column(name = "formJur")
    private String formJur;
    @Column(name = "codTrib")
    private String codTrib;
    @Column(name = "regCommerce")
    private String regCommerce;
    private String ICE;
    @Column(name = "idFiscal")
    private String idFiscal;
    @Column(name = "numTaxeProf")
    private String numTaxeProf;
    @Column(name = "idSpecifique")
    private String idSpecifique;
    @Column(name = "codLEI")
    private String codLEI;
    @Column(name = "codActPrinc")
    private String codActPrinc;
    @Column(name = "codActSec")
    private String codActSec;
    @Column(name = "tailleEntrep")
    private String tailleEntrep;
    private String genre;
    @Column(name = "dtCreation")
    private Date dtCreation;
    @Column(name = "natMod")
    private String natMod;
    @Column(name = "dtMod")
    private Date dtMod;
    @Column(name = "flagSuc")
    private Boolean flagSuc;
    @Column(name = "tpIdPrincSiege")
    private String  tpIdPrincSiege;
    @Column(name = "idPrincSiege")
    private String idPrincSiege;
    @Column(name = "raisonSocSiege")
    private String raisonSocSiege;
    @Column(name = "groupAppart")
    private String groupAppart;


    @ManyToOne
    @JoinColumn(name = "id_client")
    private sss_cdr_snapshot_client_stat client;


}
