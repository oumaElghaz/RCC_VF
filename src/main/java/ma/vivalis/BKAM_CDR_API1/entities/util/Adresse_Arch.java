package ma.vivalis.BKAM_CDR_API1.entities.util;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_arch_client_stat;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "Adresse_Arch")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adresse_Arch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private String codPostal;
    private String codLocal;
    private String codPays;
    private String numTeleph;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private sss_cdr_snapshot_arch_client_stat client;
}
