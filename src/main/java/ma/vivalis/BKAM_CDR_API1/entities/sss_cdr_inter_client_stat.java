package ma.vivalis.BKAM_CDR_API1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.NatClient;
import ma.vivalis.BKAM_CDR_API1.entities.util.Adresse_interm;
import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPM_interm;
import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPP_interm;


import java.util.Date;
import java.util.List;


@Entity
@Table(name = "sss_cdr_inter_client_stat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_inter_client_stat {
    @Id
    private String id_client;
    private String id_lot;
    private Date dateExtraction;
    private String entObserv;
    private String entDeclar;
    private Date dtRefEnt;
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    private String codClient;
    private Boolean altCodClient;
    private String natClient;
    private Boolean entLieeEtab;
    private String codAgEcon;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<Adresse_interm> adresses;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<DonneesIntPP_interm> donneesInts_pp;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<DonneesIntPM_interm> donneesInts_pm;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_act_interm> actionnariats;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_benef_interm> benEffects;




}
