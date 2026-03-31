package ma.vivalis.BKAM_CDR_API1.contrat.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_contrat_stat;
@Entity
@Table(name = "sss_cdr_ListLinkContrat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_ListLinkContrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idContAss;
    private String tpConnex;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idCont", referencedColumnName = "idCont"),
            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot"),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction")
    })
    @ToString.Exclude
    sss_cdr_contrat_stat contrat;
}
