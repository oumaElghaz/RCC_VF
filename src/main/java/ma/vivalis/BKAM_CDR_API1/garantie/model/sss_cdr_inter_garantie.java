package ma.vivalis.BKAM_CDR_API1.garantie.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Table(name = "sss_cdr_inter_garantie", uniqueConstraints = {
        @UniqueConstraint(name = "uk_garantie_inter_composite",
                columnNames = {"idGar", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_inter_garantie {
    @Id
    private String  idGar;
    private Integer id_lot;
    private LocalDateTime dateExtraction;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private Date dtRefGar;
    private Date  dtCreatGar;
    private Date  dtFinGar;
    private Boolean  renGar;
    private Date  dtRenGar;
    private Date  dtFinRenGar;
    private String  tpGar;
    private String  codClient;
    private String  codGarExt;
    private String  tpRefExtGar;
    private String  refExtGar;
    private Double  prixAcqProp;
    private String  codLocalGar;
    private Double  montGar;
    private Double  valOriGar;
    private String  tpValInGar;
    private Double  valActGar;
    private Date  dtEvalGar;
    private String  tpValActGar;
    private Boolean  garEtat;
    private String  nvGarAdossCred;
    private Boolean  etatExecGar;
    private Date  dtExecGar;
}
