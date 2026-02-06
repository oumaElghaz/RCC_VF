package ma.vivalis.BKAM_CDR_API1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "sss_cdr_snapshot_client_benef")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_snapshot_client_benef {
    @Id
    private Long id;
    private String typIdBenEffect;
    private String idBenEffect;
    private String nomBenEffect;
    private String preBenEffect;
    private String natBenEffect;


    @ManyToOne
    @JoinColumn(name = "id_client")
    private sss_cdr_snapshot_client_stat client;


}
