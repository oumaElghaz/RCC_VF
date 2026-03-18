package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
