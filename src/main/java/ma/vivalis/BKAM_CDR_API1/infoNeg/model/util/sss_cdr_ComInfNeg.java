package ma.vivalis.BKAM_CDR_API1.infoNeg.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;

import java.util.*;

@Entity
@Table(name = "sss_cdr_ComInfNeg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_ComInfNeg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dtRef;

    @ManyToOne
    @JoinColumns({

            @JoinColumn(name = "id_lot", referencedColumnName = "id_lot"),
            @JoinColumn(name = "dateExtraction", referencedColumnName = "dateExtraction"),
            @JoinColumn(name = "inf_neg_stat_id", referencedColumnName = "id")
    })
    @ToString.Exclude
    private sss_cdr_infoNegative infoNeg;

    @OneToMany(mappedBy = "comInf" , cascade = CascadeType.ALL)
    private List<sss_cdr_InfNeg> infNegList= new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof sss_cdr_ComInfNeg other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31 + (id == null ? 0 : id.hashCode());
    }
}
