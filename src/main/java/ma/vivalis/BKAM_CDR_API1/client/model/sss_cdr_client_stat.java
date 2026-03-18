package ma.vivalis.BKAM_CDR_API1.client.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import org.hibernate.annotations.BatchSize;

import java.util.*;


@Entity
@Table(name = "sss_cdr_client_stat", uniqueConstraints = {
        @UniqueConstraint(name = "uk_client_arch_composite",
                columnNames = {"id_client", "id_lot", "dateExtraction"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_client_stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String id_client;
    private Integer id_lot;
    private Date dateExtraction;
    private String entObserv;
    private String entDeclar;
    private Date dtRefEnt;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private String codClient;
    private String altCodClient;
    private String natClient;
    private String entLieeEtab;
    private String codAgEcon;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sss_cdr_adresse_id")
    private sss_cdr_Adresse adresse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donneesInt_pp_id")
    private sss_cdr_DonneesIntPP donneesInt_pp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donneesInt_pm_id")
    private sss_cdr_DonneesIntPM donneesInt_pm;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @Builder.Default
    private Set<sss_cdr_client_act> actionnariats= new HashSet<>();

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    @BatchSize(size = 50)
    @Builder.Default
    private Set<sss_cdr_client_benef> benEffects= new HashSet<>();

}
