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
    private Date dateDeclaration;
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
