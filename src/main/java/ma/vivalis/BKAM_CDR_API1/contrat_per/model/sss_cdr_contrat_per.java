package ma.vivalis.BKAM_CDR_API1.contrat_per.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "sss_cdr_contrat_per", uniqueConstraints = {
        @UniqueConstraint(name = "uk_contrat_per_final_composite",
                columnNames = {"idCont", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_contrat_per {
    @Id
    private String idCont;
    private Integer id_lot;
    private LocalDateTime dateExtraction;
    private Date dateRef;
    private Double montDu;
    private Double montUtilCred;
    private Double montUtilCredDev;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private Double montRest;
    private Double montRestDev;
    private Double montComAg;
    private Double commSpecif;
    private Double interCourMrg;
    private Double caFactor;
    private String tpReembAntc;
    private Double montReembAntc;
    private Date dtProcRevTxInt;
    private Boolean colRefin;
    private Double nbEcheRest;
    private Date dtProcEche;
    private Date dtDernEchePay;
    private Double nbEcheImp;
    private Double montEcheImp;
    private String stPaiement;
    private Date dtStPaiement;
    private String classCreanceSouff;
    private Date dtClassCreanceSouff;
    private Boolean contentieux;
    private Boolean creanceProv;
    private Double montProv;
    private Double txProvCont;
    private String codClient;
    private Double montEncCli;
    private Double LGDCont;
    private Date dtLGD;
    private Double EADCont;
    private Date dtEAD;
    private Double ECLCont;
    private Date dtECL;
}
