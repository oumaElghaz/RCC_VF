package ma.vivalis.BKAM_CDR_API1.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "sss_cdr_snapshot_client_act")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_snapshot_client_act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "natActionnaire")
    private String natActionnaire;
    @Column(name = "formJurAct")
    private String formJurAct;
    @Column(name = "tpIdPrincAct")
    private String tpIdPrincAct;
    @Column(name = "idPrincAct")
    private String idPrincAct;
    @Column(name = "codTribunAct")
    private String codTribunAct;
    @Column(name = "regCommerAct")
    private String regCommerAct;
    @Column(name = "idSpecifiqueAct")
    private String idSpecifiqueAct;
    @Column(name = "ICEAct")
    private String ICEAct;
    @Column(name = "LEIAct")
    private String LEIAct;
    @Column(name = "payResAct")
    private String payResAct;
    @Column(name = "nomRaisonSocAct")
    private String nomRaisonSocAct;
    @Column(name = "qtpartCapSocAct")
    private Integer qtpartCapSocAct;


    @ManyToOne
    @JoinColumn(name = "id_client")
    private sss_cdr_snapshot_client_stat client;

}
