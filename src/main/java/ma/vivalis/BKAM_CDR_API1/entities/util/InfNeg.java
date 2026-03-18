package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.util.Date;
@Entity
@Table(name = "InfNeg")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfNeg {
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
    @JoinColumn(name = "com_inf_neg_id")
    private ComInfNeg comInf;

}
