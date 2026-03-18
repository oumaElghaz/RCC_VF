package ma.vivalis.BKAM_CDR_API1.entities.util;


import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_infoNega_stat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ComInfNeg")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComInfNeg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dtRef;


    @ManyToOne
    @JoinColumn(name = "inf_neg_stat_id", referencedColumnName = "id")
    @ToString.Exclude
    private sss_cdr_snapshot_infoNega_stat infoNeg;

    @OneToMany(mappedBy = "comInf" , cascade = CascadeType.ALL)
    private List<InfNeg> infNegList;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComInfNeg other)) return false;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return 31 + (id == null ? 0 : id.hashCode());
    }
}

