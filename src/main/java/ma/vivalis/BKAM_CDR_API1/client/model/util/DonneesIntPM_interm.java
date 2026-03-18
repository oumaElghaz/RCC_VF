package ma.vivalis.BKAM_CDR_API1.client.model.util;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "DonneesIntPM_interm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonneesIntPM_interm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String raisonSocial;
    private String sigle;
    private String formJur;
    private String codTrib;
    private String regCommerce;
    private String ICE;
    private String idFiscal;
    private String numTaxeProf;
    private String idSpecifique;
    private String codLEI;
    private String codActPrinc;
    private String codActSec;
    private String tailleEntrep;
    private String genre;
    private Date dtCreation;
    private String natMod;
    private Date dtMod;
    private Boolean flagSuc;
    private String  tpIdPrincSiege;
    private String idPrincSiege;
    private String raisonSocSiege;
    private String groupAppart;



}
