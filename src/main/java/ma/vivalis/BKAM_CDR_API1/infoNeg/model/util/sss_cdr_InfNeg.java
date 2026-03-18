package ma.vivalis.BKAM_CDR_API1.infoNeg.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.util.Date;

@Entity
@Table(name = "sss_cdr_InfNeg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_InfNeg {
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
    private sss_cdr_ComInfNeg comInf;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof sss_cdr_InfNeg other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31 + (id == null ? 0 : id.hashCode());
    }
}
