package ma.vivalis.BKAM_CDR_API1.entities.mapping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "sss_cdr_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_mapping {
    @Id
    private Long id;
    private String ctab ;
    private String codSrc ;
    private String DescEvo;
    private String dom;
    private String codCibl ;
    private String att1 ;
    private String att2 ;
    private String att3 ;
    private String uti ;
    private Date dou ;
    private Date dmo ;
    private String libelle;
}
