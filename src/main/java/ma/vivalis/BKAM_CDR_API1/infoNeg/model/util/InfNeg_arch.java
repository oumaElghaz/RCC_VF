package ma.vivalis.BKAM_CDR_API1.infoNeg.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.util.Date;
@Entity
@Table(name = "InfNeg_arch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfNeg_arch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private String codClient;
    private String refInfoNeg;
    private String tpInfNegInc;
    private Date dtObsInfNegInc;
    private Integer montInc;
    private String statInfoNeg;
    private Date dtSortie;

    @ManyToOne
     @JoinColumn(name = "com_inf_neg_id", referencedColumnName = "id")
     @ToString.Exclude
    private ComInfNeg_arch comInf;
}
