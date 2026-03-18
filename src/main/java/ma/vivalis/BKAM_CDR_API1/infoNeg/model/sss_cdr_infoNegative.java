package ma.vivalis.BKAM_CDR_API1.infoNeg.model;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_ComInfNeg;
import org.hibernate.annotations.BatchSize;

import java.util.*;

@Entity
@Table(name = "sss_cdr_infoNegative", uniqueConstraints = {
        @UniqueConstraint(name = "uk_sss_cdr_infNeg_composite",
                columnNames = {"id", "id_lot", "dateExtraction"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_infoNegative {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer id_lot;
    private Date dateExtraction;
    private String entObserv;
    private String entDeclar;
    private String idDest;


    @OneToMany(mappedBy = "infoNeg" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @Builder.Default
    private List<sss_cdr_ComInfNeg> comInfNegs=new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof sss_cdr_infoNegative other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31 + (id == null ? 0 : id.hashCode());
    }
}
