package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_stat;

@Entity
@Table(name = "ListLinkContrat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListLinkContrat {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
    private String idContAss;
    private String tpConnex;

    @ManyToOne
    @JoinColumn(name = "idCont")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    sss_cdr_snapshot_contrat_stat contrat;

}
