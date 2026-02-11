package ma.vivalis.BKAM_CDR_API1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.Flag_envoi;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.NatClient;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "sss_cdr_snapshot_arch_client_stat")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_snapshot_arch_client_stat {
    @Id
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
    @Enumerated(EnumType.STRING)
    private Flag_envoi flag_envoi;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<Adresse_Arch> adresses= new ArrayList<>();

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<DonneesIntPP_Arch> donneesInts_pp= new ArrayList<>();

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<DonneesIntPM_Arch> donneesInts_pm= new ArrayList<>();

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_act_Arch> actionnariats= new ArrayList<>();

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_benef_Arch> benEffects= new ArrayList<>();

}
