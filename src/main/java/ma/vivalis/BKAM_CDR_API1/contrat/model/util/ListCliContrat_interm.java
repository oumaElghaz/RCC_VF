package ma.vivalis.BKAM_CDR_API1.contrat.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
@Entity
@Table(name = "ListCliContrat_interm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListCliContrat_interm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codClient;
    private Double capAutoriseEnt;
    private Double valProcVersEnt;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCont", referencedColumnName = "idCont"),
            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot"),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction")
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    sss_cdr_inter_contrat_stat contrat;
}
