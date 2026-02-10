package ma.vivalis.BKAM_CDR_API1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "sss_cdr_client_act")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "id_client")
    private sss_cdr_client_stat client;
}
