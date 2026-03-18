package ma.vivalis.BKAM_CDR_API1.infoNeg.model.util;

import jakarta.persistence.*;
import lombok.*;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;

import java.util.*;

@Entity
@Table(name = "ComInfNeg_arch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComInfNeg_arch {
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
    private sss_cdr_arch_infoNegative infoNeg;

    @OneToMany(mappedBy = "comInf" , cascade = CascadeType.ALL)
    private List<InfNeg_arch> infNegList= new ArrayList<>();
}
