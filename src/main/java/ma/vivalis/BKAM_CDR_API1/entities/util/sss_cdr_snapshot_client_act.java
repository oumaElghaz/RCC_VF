package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;


@Entity
@Table(name = "sss_cdr_snapshot_client_act")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "client")
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
    @ToString.Exclude
    private sss_cdr_snapshot_client_stat client;

}
