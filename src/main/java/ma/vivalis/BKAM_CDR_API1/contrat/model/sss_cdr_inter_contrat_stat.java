package ma.vivalis.BKAM_CDR_API1.contrat.model;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.*;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sss_cdr_inter_contrat_stat", uniqueConstraints = {
        @UniqueConstraint(name = "uk_contrat_inter_composite",
                columnNames = {"idCont", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_inter_contrat_stat {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private String idCont;
    private Integer id_lot;
    private LocalDateTime dateExtraction;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListCliContrat_interm> listCliContrat;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListLinkContrat_interm> listLinkContrat;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListConsort_interm> listConsort;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListGarant_interm> listGarant;
}
