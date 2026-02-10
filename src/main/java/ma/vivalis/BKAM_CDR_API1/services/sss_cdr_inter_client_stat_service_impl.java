package ma.vivalis.BKAM_CDR_API1.services;


import jakarta.transaction.Transactional;
import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.mapping.*;
import ma.vivalis.BKAM_CDR_API1.repositories.*;
import ma.vivalis.BKAM_CDR_API1.repositories.util.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class sss_cdr_inter_client_stat_service_impl {

    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    private final LotSequenceService_impl lotSequenceService_impl;
    private final Comparaison comparaison;
    private final sss_cdr_snapshot_client_stat_Repository sss_cdr_snapshot_client_stat_Repository;
    private final sss_cdr_snapshot_arch_client_stat_Repository sss_cdr_snapshot_arch_client_stat_Repository;
    private final Adresse_interm_Repository adresse_interm_Repository;
    private final AdresseRepository adresseRepository;

    private final DonneesIntPM_Repository donneesIntPM_Repository;
    private final DonneesIntPM_interm_Repository donneesIntPM_interm_Repository;

    private final DonneesIntPP_Repository donneesIntPP_Repository;
    private final DonneesIntPP_interm_Repository donneesIntPP_interm_Repository;

    private final sss_cdr_snapshot_client_act_Repository sss_cdr_snapshot_client_act_Repository;
    private final sss_cdr_snapshot_client_act_interm_Repository sss_cdr_snapshot_client_act_interm_Repository;

    private final sss_cdr_snapshot_client_benef_Repository sss_cdr_snapshot_client_benef_Repository;
    private final sss_cdr_snapshot_client_benef_interm_Repository sss_cdr_snapshot_client_benef_interm_Repository;

    private final sss_cdr_snapshot_arch_client_stat_service_impl sss_cdr_snapshot_arch_client_stat_service_impl;
    public sss_cdr_inter_client_stat_service_impl(sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository, LotSequenceService_impl lotSequenceServiceImpl, Comparaison comparaison, sss_cdr_snapshot_client_stat_Repository sssCdrSnapshotClientStatRepository, sss_cdr_snapshot_arch_client_stat_Repository sssCdrSnapshotArchClientStatRepository, Adresse_interm_Repository adresseIntermRepository, AdresseRepository adresseRepository, DonneesIntPM_Repository donneesIntPMRepository, DonneesIntPM_interm_Repository donneesIntPMIntermRepository, DonneesIntPP_Repository donneesIntPPRepository, DonneesIntPP_interm_Repository donneesIntPPIntermRepository, sss_cdr_snapshot_client_act_Repository sssCdrSnapshotClientActRepository, sss_cdr_snapshot_client_act_interm_Repository sssCdrSnapshotClientActIntermRepository, sss_cdr_snapshot_client_benef_Repository sssCdrSnapshotClientBenefRepository, sss_cdr_snapshot_client_benef_interm_Repository sssCdrSnapshotClientBenefIntermRepository, sss_cdr_snapshot_arch_client_stat_service_impl sssCdrSnapshotArchClientStatServiceImpl) {
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
        lotSequenceService_impl = lotSequenceServiceImpl;
        this.comparaison = comparaison;
        sss_cdr_snapshot_client_stat_Repository = sssCdrSnapshotClientStatRepository;
        sss_cdr_snapshot_arch_client_stat_Repository = sssCdrSnapshotArchClientStatRepository;
        adresse_interm_Repository = adresseIntermRepository;
        this.adresseRepository = adresseRepository;
        donneesIntPM_Repository = donneesIntPMRepository;
        donneesIntPM_interm_Repository = donneesIntPMIntermRepository;
        donneesIntPP_Repository = donneesIntPPRepository;
        donneesIntPP_interm_Repository = donneesIntPPIntermRepository;
        sss_cdr_snapshot_client_act_Repository = sssCdrSnapshotClientActRepository;
        sss_cdr_snapshot_client_act_interm_Repository = sssCdrSnapshotClientActIntermRepository;
        sss_cdr_snapshot_client_benef_Repository = sssCdrSnapshotClientBenefRepository;
        sss_cdr_snapshot_client_benef_interm_Repository = sssCdrSnapshotClientBenefIntermRepository;
        sss_cdr_snapshot_arch_client_stat_service_impl = sssCdrSnapshotArchClientStatServiceImpl;
    }


    public void sss_cdr_inter_client_stat_create() {
        List<String> id_clients_modifies = comparaison.findModifiedAndNewIdsClients();
        int idLot = lotSequenceService_impl.getNextLotId();
        for (String s:id_clients_modifies){
        System.out.println("id_clients_modifies"+s);}
        List<sss_cdr_snapshot_client_stat> clients_snapshot = new ArrayList<>();

        for (String id : id_clients_modifies) {
            clients_snapshot.add(sss_cdr_snapshot_client_stat_Repository.findById(id).orElseThrow(() -> new NoSuchElementException("client introuvable")));
        }


        for (sss_cdr_snapshot_client_stat client : clients_snapshot) {
            sss_cdr_inter_client_stat client_inter = new sss_cdr_inter_client_stat();
            client_inter.setId_client(client.getId_client());
            //client_inter.setId_lot(sss_cdr_snapshot_arch_client_stat_Repository.findIdLotById(client.getId_client()));
            client_inter.setId_lot(idLot);
            client_inter.setDateExtraction(client.getDateDeclaration());
            client_inter.setEntObserv(client.getEntObserv());
            client_inter.setEntDeclar(client.getEntDeclar());
            client_inter.setDtRefEnt(client.getDtRefEnt());
            client_inter.setActionType(client.getActionType());
            client_inter.setCodClient(client.getCodClient());
            client_inter.setAltCodClient(client.getAltCodClient());
            client_inter.setNatClient(client.getNatClient());
            client_inter.setEntLieeEtab(client.getEntLieeEtab());
            client_inter.setCodAgEcon(client.getCodAgEcon());
            for (Adresse a : client.getAdresses()) {
                Adresse_interm a_inter = new Adresse_interm();
                a_inter.setAdresse(a.getAdresse());
                a_inter.setCodPostal(a.getCodPostal());
                a_inter.setCodLocal(a.getCodLocal());
                a_inter.setCodPays(a.getCodPays());
                a_inter.setNumTeleph(a.getNumTeleph());
                a_inter.setClient(client_inter);
                client_inter.getAdresses().add(a_inter);
            }

            for (DonneesIntPM a : client.getDonneesInts_pm()) {
                DonneesIntPM_interm ai = new DonneesIntPM_interm();
                ai.setRaisonSocial(a.getRaisonSocial());
                ai.setSigle(a.getSigle());
                ai.setFormJur(a.getFormJur());
                ai.setCodTrib(a.getCodTrib());
                ai.setRegCommerce(a.getRegCommerce());
                ai.setICE(a.getICE());
                ai.setIdFiscal(a.getIdFiscal());
                ai.setNumTaxeProf(a.getNumTaxeProf());
                ai.setIdSpecifique(a.getIdSpecifique());
                ai.setCodLEI(a.getCodLEI());
                ai.setCodActPrinc(a.getCodActPrinc());
                ai.setCodActSec(a.getCodActSec());
                ai.setTailleEntrep(a.getTailleEntrep());
                ai.setGenre(a.getGenre());
                ai.setDtCreation(a.getDtCreation());
                ai.setNatMod(a.getNatMod());
                ai.setDtMod(a.getDtMod());
                ai.setFlagSuc(a.getFlagSuc());
                ai.setTpIdPrincSiege(a.getTpIdPrincSiege());
                ai.setIdPrincSiege(a.getIdPrincSiege());
                ai.setRaisonSocial(a.getRaisonSocial());
                ai.setGroupAppart(a.getGroupAppart());
                ai.setClient(client_inter);
                client_inter.getDonneesInts_pm().add(ai);
            }
            for (DonneesIntPP a : client.getDonneesInts_pp()) {
                DonneesIntPP_interm ai = new DonneesIntPP_interm();
                ai.setIdPrincipal(a.getIdPrincipal());
                ai.setTpIdPrincipal(a.getTpIdPrincipal());
                ai.setPrenom(a.getPrenom());
                ai.setNomFamille(a.getNomFamille());
                ai.setPaysDelivrance(a.getPaysDelivrance());
                ai.setDtDelivrance(a.getDtDelivrance());
                ai.setDtExpiration(a.getDtExpiration());
                ai.setTypePPPro(a.getTypePPPro());
                ai.setRNAE(a.getRNAE());
                ai.setDtNaissance(a.getDtNaissance());
                ai.setCodLocalNaissance(a.getCodLocalNaissance());
                ai.setSexe(a.getSexe());
                ai.setNationalite(a.getNationalite());
                ai.setSitFamille(a.getSitFamille());
                ai.setCodCatProf(a.getCodCatProf());
                ai.setMenage(a.getMenage());
                ai.setQualAcadem(a.getQualAcadem());
                ai.setCatClient(a.getCatClient());
                ai.setClient(client_inter);
                client_inter.getDonneesInts_pp().add(ai);
            }
            for (sss_cdr_snapshot_client_act a : client.getActionnariats()) {
                sss_cdr_snapshot_client_act_interm ai = new sss_cdr_snapshot_client_act_interm();

                //ai.setId(a.getId());
                ai.setNatActionnaire(a.getNatActionnaire());
                ai.setFormJurAct(a.getFormJurAct());
                ai.setTpIdPrincAct(a.getTpIdPrincAct());
                ai.setIdPrincAct(a.getIdPrincAct());
                ai.setCodTribunAct(a.getCodTribunAct());
                ai.setRegCommerAct(a.getRegCommerAct());
                ai.setIdSpecifiqueAct(a.getIdSpecifiqueAct());
                ai.setICEAct(a.getICEAct());
                ai.setLEIAct(a.getLEIAct());
                ai.setPayResAct(a.getPayResAct());
                ai.setNomRaisonSocAct(a.getNomRaisonSocAct());
                ai.setQtpartCapSocAct(a.getQtpartCapSocAct());
                ai.setClient(client_inter);
                client_inter.getActionnariats().add(ai);
            }

            for (sss_cdr_snapshot_client_benef a : client.getBenEffects()) {
                sss_cdr_snapshot_client_benef_interm ai = new sss_cdr_snapshot_client_benef_interm();

                //ai.setId(a.getId());
                ai.setTypIdBenEffect(a.getTypIdBenEffect());
                ai.setIdBenEffect(a.getIdBenEffect());
                ai.setNomBenEffect(a.getNomBenEffect());
                ai.setPreBenEffect(a.getPreBenEffect());
                ai.setNatBenEffect(a.getNatBenEffect());
                ai.setClient(client_inter);
                client_inter.getBenEffects().add(ai);
            }


            sss_cdr_inter_client_stat_Repository.save(client_inter);
            //Appel service de archivage des clients
            sss_cdr_snapshot_arch_client_stat_service_impl.create_arch_client_stat(client_inter);




        }

        //suppression des snapshot client apres traitement
        sss_cdr_snapshot_client_stat_Repository.deleteAll();
    }







}






