package ma.vivalis.BKAM_CDR_API1.services;

import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_snapshot_arch_client_stat_Repository;
import org.springframework.stereotype.Service;

@Service
public class sss_cdr_snapshot_arch_client_stat_service_impl {

    private final sss_cdr_snapshot_arch_client_stat_Repository sss_cdr_snapshot_arch_client_stat_Repository;

    public sss_cdr_snapshot_arch_client_stat_service_impl(sss_cdr_snapshot_arch_client_stat_Repository sssCdrSnapshotArchClientStatRepository) {
        sss_cdr_snapshot_arch_client_stat_Repository = sssCdrSnapshotArchClientStatRepository;
    }


    public void create_arch_client_stat(sss_cdr_inter_client_stat sss_cdr_inter_client_stat){
        sss_cdr_snapshot_arch_client_stat cl_arc=new sss_cdr_snapshot_arch_client_stat();
        //mapping
        cl_arc.setId_client(sss_cdr_inter_client_stat.getId_client());
        cl_arc.setId_lot(sss_cdr_inter_client_stat.getId_lot());
        cl_arc.setDateExtraction(sss_cdr_inter_client_stat.getDateExtraction());
        cl_arc.setEntObserv(sss_cdr_inter_client_stat.getEntObserv());
        cl_arc.setEntDeclar(sss_cdr_inter_client_stat.getEntDeclar());
        cl_arc.setDtRefEnt(sss_cdr_inter_client_stat.getDtRefEnt());
        cl_arc.setActionType(sss_cdr_inter_client_stat.getActionType());
        cl_arc.setCodClient(sss_cdr_inter_client_stat.getCodClient());
        cl_arc.setAltCodClient(sss_cdr_inter_client_stat.getAltCodClient());
        cl_arc.setNatClient(sss_cdr_inter_client_stat.getNatClient());
        cl_arc.setEntLieeEtab(sss_cdr_inter_client_stat.getEntLieeEtab());
        cl_arc.setCodAgEcon(sss_cdr_inter_client_stat.getCodAgEcon());
        cl_arc.setFlag_envoi(sss_cdr_inter_client_stat.getFlag_envoi());
        for (Adresse_interm a : sss_cdr_inter_client_stat.getAdresses()) {
            Adresse_Arch a_inter = new Adresse_Arch();
            //a_inter.setId(a.getId());

            a_inter.setAdresse(a.getAdresse());
            a_inter.setCodPostal(a.getCodPostal());
            a_inter.setCodLocal(a.getCodLocal());
            a_inter.setCodPays(a.getCodPays());
            a_inter.setNumTeleph(a.getNumTeleph());
            a_inter.setClient(cl_arc);
            cl_arc.getAdresses().add(a_inter);
        }

        for (DonneesIntPM_interm a : sss_cdr_inter_client_stat.getDonneesInts_pm()) {
            DonneesIntPM_Arch ai = new DonneesIntPM_Arch();

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
            ai.setClient(cl_arc);
            cl_arc.getDonneesInts_pm().add(ai);
        }
        for (DonneesIntPP_interm a : sss_cdr_inter_client_stat.getDonneesInts_pp()) {
            DonneesIntPP_Arch ai = new DonneesIntPP_Arch();
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
            ai.setClient(cl_arc);
            cl_arc.getDonneesInts_pp().add(ai);
        }
        for (sss_cdr_snapshot_client_act_interm a : sss_cdr_inter_client_stat.getActionnariats()) {
            sss_cdr_snapshot_client_act_Arch ai = new sss_cdr_snapshot_client_act_Arch();


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
            ai.setClient(cl_arc);
            cl_arc.getActionnariats().add(ai);
        }

        for (sss_cdr_snapshot_client_benef_interm a : sss_cdr_inter_client_stat.getBenEffects()) {
             sss_cdr_snapshot_client_benef_Arch ai = new sss_cdr_snapshot_client_benef_Arch();


            ai.setTypIdBenEffect(a.getTypIdBenEffect());
            ai.setIdBenEffect(a.getIdBenEffect());
            ai.setNomBenEffect(a.getNomBenEffect());
            ai.setPreBenEffect(a.getPreBenEffect());
            ai.setNatBenEffect(a.getNatBenEffect());
            ai.setClient(cl_arc);
            cl_arc.getBenEffects().add(ai);
        }
        
        
        // mapping
        sss_cdr_snapshot_arch_client_stat_Repository.save(cl_arc);
    }
}
