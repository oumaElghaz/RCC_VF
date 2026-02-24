package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_stat;


@Entity
@Table(name = "sss_cdr_client_benef")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_client_benef {
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
            @JoinColumn(name = "id_client", referencedColumnName = "id_client", insertable = false, updatable = false),
            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot", insertable = false, updatable = false),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction", insertable = false, updatable = false)
    })
    private sss_cdr_client_stat client;
}
