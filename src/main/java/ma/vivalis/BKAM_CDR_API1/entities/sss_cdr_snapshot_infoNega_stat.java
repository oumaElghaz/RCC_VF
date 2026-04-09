package ma.vivalis.BKAM_CDR_API1.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.ComInfNeg;
import org.hibernate.annotations.BatchSize;

import java.util.*;

@Entity
@Table(name = "sss_cdr_snapshot_infoNega_stat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_snapshot_infoNega_stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateDeclaration;
    //private String entObserv;
    //private String entDeclar;
    //private String idDest;


    @OneToMany(mappedBy = "infoNeg" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @Builder.Default
    private List<ComInfNeg> comInfNegs=new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof sss_cdr_snapshot_infoNega_stat other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31 + (id == null ? 0 : id.hashCode());
    }
}
