package ma.vivalis.BKAM_CDR_API1.contrat.model;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.ListCliContrat_arch;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.ListConsort_arch;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.ListGarant_arch;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.ListLinkContrat_arch;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sss_cdr_arch_contrat_stat", uniqueConstraints = {
        @UniqueConstraint(name = "uk_contrat_arch_composite",
                columnNames = {"idCont", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_arch_contrat_stat {
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
    private String montIniAccord;
    private String montCreCoFin;
    private String txChange;
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
    private String nombreTotEche;
    private String periodEche;
    private String mtEche;
    private Date dt1Eche;
    private String mont1Eche;
    private String mont1EcheDiv;
    private String flagTxInt;
    private String txRef;
    private String txAnnuelPourc;
    private String txTAEG;
    private String hmRibh;
    private String cmFxWkl;
    private String freqMiseJourTxInt;
    private String LTVIni;
    private String tpSecuritization;
    private String exisGarant;
    private String mntGarant;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListCliContrat_arch> listCliContrat;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListLinkContrat_arch> listLinkContrat;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListConsort_arch> listConsort;

    @OneToMany(mappedBy = "contrat" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<ListGarant_arch> listGarant;
}
