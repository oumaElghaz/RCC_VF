package ma.vivalis.BKAM_CDR_API1.client.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;


@Entity
@Table(name = "sss_cdr_client_act")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "client")
public class sss_cdr_client_act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String natActionnaire;
    private String formJurAct;
    private String tpIdPrincAct;
    private String idPrincAct;
    private String codTribunAct;
    private String regCommerAct;
    private String idSpecifiqueAct;
    private String ICEAct;
    private String LEIAct;
    private String payResAct;
    private String nomRaisonSocAct;
    private Integer qtpartCapSocAct;


    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "id_client", referencedColumnName = "id_client"),
            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot"),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction")
    })
    @ToString.Exclude
    private sss_cdr_client_stat client;
}
