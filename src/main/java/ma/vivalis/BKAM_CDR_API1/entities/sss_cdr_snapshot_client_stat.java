package ma.vivalis.BKAM_CDR_API1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "sss_cdr_snapshot_client_stat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_snapshot_client_stat {
    @Id
    private String id_client;
    private Date dateDeclaration;
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
    @JoinColumn(name = "adresse_id")
    private Adresse_snap adresse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donneesInt_pp_id")
    private DonneesIntPP_snap donneesInt_pp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "donneesInt_pm_id")
    private DonneesIntPM_snap donneesInt_pm;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_act> actionnariats;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_benef> benEffects;

}
