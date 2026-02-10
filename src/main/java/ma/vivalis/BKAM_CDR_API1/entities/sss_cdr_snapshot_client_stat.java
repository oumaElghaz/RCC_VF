package ma.vivalis.BKAM_CDR_API1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.NatClient;
import ma.vivalis.BKAM_CDR_API1.entities.util.Adresse;
import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPM;
import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPP;

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
    //@Column(name = "dateDeclaration")
    private Date dateDeclaration;
    //@Column(name = "entObserv")
    private String entObserv;
    //@Column(name = "entDeclar")
    private String entDeclar;
    //@Column(name = "dtRefEnt")
    private Date dtRefEnt;
    //@Column(name = "actionType")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    //@Column(name = "codClient")
    private String codClient;
    //@Column(name = "altCodClient")
    private String altCodClient;
    //@Column(name = "natClient")
    private String natClient;
    //@Column(name = "entLieeEtab")
    private String entLieeEtab;
    //@Column(name = "codAgEcon")
    private String codAgEcon;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<Adresse> adresses;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<DonneesIntPP> donneesInts_pp;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<DonneesIntPM> donneesInts_pm;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_act> actionnariats;

    @OneToMany(mappedBy = "client" , cascade = CascadeType.ALL)
    private List<sss_cdr_snapshot_client_benef> benEffects;

}
