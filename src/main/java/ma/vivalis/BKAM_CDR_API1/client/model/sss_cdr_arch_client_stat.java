package ma.vivalis.BKAM_CDR_API1.client.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "sss_cdr_arch_client_stat", uniqueConstraints = {
        @UniqueConstraint(name = "uk_client_arch_composite",
                columnNames = {"id_client", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_arch_client_stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id_client;
    private Integer id_lot;
    private LocalDateTime dateExtraction;
    //private String entObserv;
    //private String entDeclar;
    private Date dtRefEnt;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    //private String codClient;
    private String altCodClient;
    private String natClient;
    private String entLieeEtab;
    private String codAgEcon;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_archiv_id")
    private Adresse_Arch adresse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donneesInts_pp_id")
    private DonneesIntPP_Arch donneesInts_pp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donneesInts_pm_id")
    private DonneesIntPM_Arch donneesInts_pm;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    @Builder.Default
    private Set<sss_cdr_arch_client_act> actionnariats= new HashSet<>();

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    @Builder.Default
    private Set<sss_cdr_arch_client_benef> benEffects= new HashSet<>();

}
