package ma.vivalis.BKAM_CDR_API1.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientChangeDTO {
    // ── Client ──
    private String idClient;
    private LocalDateTime dateExtraction;
    private String entObserv;
    private String entDeclar;
    private Date dtRefEnt;
    private String codClient;
    private String altCodClient;
    private String natClient;
    private String entLieeEtab;
    private String codAgEcon;
    private String actionType;   // EI, EU, ES

    // ── Adresse ──
    private String adresse;
    private String codPostal;
    private String codLocal;
    private String codPays;
    private String numTeleph;

    // ── PP ──
    private String idPrincipal;
    private String tpIdPrincipal;
    private String prenom;
    private String nomFamille;
    private String nationalite;
    private String sexe;
    private Date dtNaissance;
    private String codLocalNaissance;
    private Date dtDelivrance;
    private Date dtExpiration;
    private String paysDelivrance;
    private String catClient;
    private String codCatProf;
    private String sitFamille;
    private String qualAcadem;
    private String RNAE;
    private Integer menage;
    private String typePPPro;

    // ── PM ──
    private String raisonSocial;
    private String formJur;
    private String ICE;
    private String codLEI;
    private String regCommerce;
    private String codTrib;
    private String idFiscal;
    private String numTaxeProf;
    private String codActPrinc;
    private String codActSec;
    private String tailleEntrep;
    private String sigle;
    private String groupAppart;
    private String genre;
    private Boolean flagSuc;
    private Date pmDtCreation;
    private Date dtMod;
    private String natMod;
    private String idPrincSiege;
    private String tpIdPrincSiege;
    private String raisonSocSiege;
    private String idSpecifique;
}
