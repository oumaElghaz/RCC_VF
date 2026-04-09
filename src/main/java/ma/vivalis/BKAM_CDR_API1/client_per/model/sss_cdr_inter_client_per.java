package ma.vivalis.BKAM_CDR_API1.client_per.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "sss_cdr_inter_client_per", uniqueConstraints = {
        @UniqueConstraint(name = "uk_client_per_inter_composite",
                columnNames = {"codClient", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_inter_client_per {
    @Id
    private String codClient ;
    private Integer id_lot;
    private LocalDateTime dateExtraction;
    //private String entObserv ;
    //private String entDeclar ;
    //private Date dtCreation ;
    //private String idDest;
    private Date   dtRef;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private String watchList ;
    private String etatAvProcJud ;
    private Date   dtEtatAvProcJud ;
    private Double revenu ;
    private Date   dtRevenu;
    private Integer annExercCompt ;
    private Double capSocial ;
    private Double capPropres ;
    private Double actImmobilises ;
    private Double totBilan ;
    private Double chiffreAffaire ;
    private Double dtAffairesAnExp ;
    private Double detteBancLMT ;
    private Double detteBancCT ;
    private Double passifCirculant ;
    private Double dettesFourn ;
    private Double compteCourAssoc ;
    private Double tresoreriePassif ;
    private Double actifCirculant ;
    private Double créancesClients ;
    private Double tresorerieActif ;
    private Double caisse ;
    private Double achatsRevendus ;
    private Double achatsConsom ;
    private Double chargesExternes;
    private Double chargesInterets ;
    private Double resultatNet;
    private String tpResultat;
    private Double PDCont ;
    private Date   dtEvalRisques ;
    private String modIRBCont;
    private String coteCli ;
    private Date   dateCoteCli;
    private String modCoteCli ;
    private String notAgence ;
    private String NomAgence ;
    private Date   dtnotAgc;
}
