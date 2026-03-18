package ma.vivalis.BKAM_CDR_API1.infoNeg.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.ComInfNeg_interm;
import org.hibernate.annotations.BatchSize;

import java.util.*;

@Entity
@Table(name = "sss_cdr_inter_infoNegative", uniqueConstraints = {
        @UniqueConstraint(name = "uk_infNeg_inter_composite",
                columnNames = {"id", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_inter_infoNegative {
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
    private List<ComInfNeg_interm> comInfNegs=new ArrayList<>();
}
