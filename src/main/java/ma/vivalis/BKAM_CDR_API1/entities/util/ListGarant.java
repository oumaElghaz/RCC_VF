package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_stat;
@Entity
@Table(name = "ListGarant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListGarant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idGar;

    @ManyToOne
    @JoinColumn(name = "idCont")
    @ToString.Exclude
    sss_cdr_snapshot_contrat_stat contrat;

}
