package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;


@Entity
@Table(name = "sss_cdr_snapshot_client_benef")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "client")
public class sss_cdr_snapshot_client_benef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "typIdBenEffect")
    private String typIdBenEffect;
    @Column(name = "idBenEffect")
    private String idBenEffect;
    @Column(name = "nomBenEffect")
    private String nomBenEffect;
    @Column(name = "preBenEffect")
    private String preBenEffect;
    @Column(name = "natBenEffect")
    private String natBenEffect;


    @ManyToOne
    @JoinColumn(name = "id_client")
    @ToString.Exclude
    private sss_cdr_snapshot_client_stat client;


}
