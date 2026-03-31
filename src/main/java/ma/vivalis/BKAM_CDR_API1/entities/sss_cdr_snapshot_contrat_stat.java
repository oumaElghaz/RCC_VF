package ma.vivalis.BKAM_CDR_API1.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListCliContrat;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListConsort;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListGarant;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListLinkContrat;
import org.hibernate.annotations.BatchSize;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "sss_cdr_snapshot_contrat_stat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_snapshot_contrat_stat {
    @Id
    private String idCont;
    private String entObserv ;
    private String entDeclar ;
    private Date dtDeclaration ;
    private String idDest;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private Date dtRefCont;
    private String guichetAgence;
    private String codLocAgence;
    private String tpCont;
    private String tpCred;
    private Date dtlTpCred;
    private String creCoFin;
    private String creConsor;
    private String objCred;
    private String objCredDetail;
    private String monnaie;
    private Double montIniAccord;
    private Double montCreCoFin;
    private Double txChange;
    private Date dtContCredt;
    private Date dtDebloCred;
    private Date dtClotIni;
    private Date dtClotCred;
    private String motClotCont;
    private String flagDiff;
    private Date dtModCondCred;
    private String motModCondCred;
    private Date dtDebPerGraCap;
    private Date dtFinPerGraCap;
    private String modPaiement;
    private String tpEche;
    private String fxEche;
    private Double nombreTotEche;
    private String periodEche;
    private Double mtEche;
    private Date dt1Eche;
    private Double mont1Eche;
    private Double mont1EcheDiv;
    private String flagTxInt;
    private String txRef;
    private Double txAnnuelPourc;
    private Double txTAEG;
    private Double hmRibh;
    private Double cmFxWkl;
    private String freqMiseJourTxInt;
    private Double LTVIni;
    private String tpSecuritization;
    private String exisGarant;
    private Double mntGarant;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    //@Builder.Default
    private List<ListCliContrat> listCliContrat;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    //@Builder.Default
    private List<ListLinkContrat> listLinkContrat;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    //@Builder.Default
    private List<ListConsort> listConsort;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    //@Builder.Default
    private List<ListGarant> listGarant;
    
}
