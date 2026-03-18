package ma.vivalis.BKAM_CDR_API1.client.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_arch_client_stat;


@Entity
@Table(name = "sss_cdr_arch_client_benef")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "client")
public class sss_cdr_arch_client_benef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typIdBenEffect;
    private String idBenEffect;
    private String nomBenEffect;
    private String preBenEffect;
    private String natBenEffect;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_client", referencedColumnName = "id_client"),
            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot"),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction")
    })
    @ToString.Exclude
    private sss_cdr_arch_client_stat client;
}
