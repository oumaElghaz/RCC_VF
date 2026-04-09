package ma.vivalis.BKAM_CDR_API1.contrat.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_arch_contrat_stat;
@Entity
@Table(name = "ListConsort_arch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListConsort_arch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idEnt;
    private Double relEntPart;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCont", referencedColumnName = "idCont"),
            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot"),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction")
    })
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    sss_cdr_arch_contrat_stat contrat;
}
