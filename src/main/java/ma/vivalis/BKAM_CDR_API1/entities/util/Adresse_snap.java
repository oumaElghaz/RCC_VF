package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Adresse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse_snap {
    @Id
    private Long id;
    private String adresse;
    @Column(name = "codPostal")
    private String codPostal;
    @Column(name = "codLocal")
    private String codLocal;
    @Column(name = "codPays")
    private String codPays;
    @Column(name = "numTeleph")
    private String numTeleph;



}
