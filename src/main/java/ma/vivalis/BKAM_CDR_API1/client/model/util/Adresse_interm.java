package ma.vivalis.BKAM_CDR_API1.client.model.util;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Adresse_interm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse_interm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private String codPostal;
    private String codLocal;
    private String codPays;
    private String numTeleph;


}
