package ma.vivalis.BKAM_CDR_API1.mapping;

import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_client_stat_Repository;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_mapping_Repository;



public class client_stat_mapp {
 private final sss_cdr_mapping_Repository sss_cdr_mapping_Repository;
 private final sss_cdr_client_stat_Repository sss_cdr_client_stat_Repository;

    public client_stat_mapp(sss_cdr_mapping_Repository sssCdrMappingRepository, sss_cdr_client_stat_Repository sssCdrClientStatRepository) {
        sss_cdr_mapping_Repository = sssCdrMappingRepository;
        sss_cdr_client_stat_Repository = sssCdrClientStatRepository;
    }

    public sss_cdr_client_stat fct_mapp_donnees_client(sss_cdr_inter_client_stat inter_client_stat){
        sss_cdr_client_stat sss_cdr_client_stat=new sss_cdr_client_stat();

        sss_cdr_client_stat.setId_client(inter_client_stat.getId_client());
        sss_cdr_client_stat.setId_lot(inter_client_stat.getId_lot());
        sss_cdr_client_stat.setDateExtraction(inter_client_stat.getDateExtraction());
        sss_cdr_client_stat.setEntObserv(inter_client_stat.getEntObserv());
        sss_cdr_client_stat.setEntDeclar(inter_client_stat.getEntDeclar());
        sss_cdr_client_stat.setDtRefEnt(inter_client_stat.getDtRefEnt());
        sss_cdr_client_stat.setActionType(inter_client_stat.getActionType());
        sss_cdr_client_stat.setCodClient(inter_client_stat.getCodClient());
        sss_cdr_client_stat.setAltCodClient(inter_client_stat.getAltCodClient());
        // mapping de la nature du client
        String natClient=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CDTYPT",inter_client_stat.getNatClient());
        sss_cdr_client_stat.setNatClient(natClient);

        sss_cdr_client_stat.setEntLieeEtab(inter_client_stat.getEntLieeEtab());
        sss_cdr_client_stat.setCodAgEcon(inter_client_stat.getCodAgEcon());

//--------------adresse-------------------------------------------
        for (Adresse_interm a : inter_client_stat.getAdresses()) {
            sss_cdr_Adresse adr = new sss_cdr_Adresse();
            adr.setAdresse(a.getAdresse());
            adr.setCodPostal(a.getCodPostal());
            adr.setCodLocal(a.getCodLocal());
            //mapping des codes pays
            String codPays=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("PAYS",a.getCodPays());
            adr.setCodPays(codPays);
            adr.setNumTeleph(a.getNumTeleph());
            adr.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getAdresses().add(adr);
        }


//--------------donneeesIntPM-------------------------------------------
        for (DonneesIntPM_interm a : inter_client_stat.getDonneesInts_pm()) {
            sss_cdr_DonneesIntPM ai = new sss_cdr_DonneesIntPM();
            ai.setRaisonSocial(a.getRaisonSocial());
            ai.setSigle(a.getSigle());
            ai.setFormJur(a.getFormJur());
            //mapping du code tribunal
            String codTrib=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CDTR",a.getCodTrib());
            ai.setCodTrib(codTrib);
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
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getDonneesInts_pm().add(ai);
        }




//--------------donneeesIntPP-------------------------------------------
        for (DonneesIntPP_interm a : inter_client_stat.getDonneesInts_pp()) {
            sss_cdr_DonneesIntPP ai = new sss_cdr_DonneesIntPP();
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
            //mapping du sexe
            String sexe=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SEXE",a.getSexe());
            ai.setSexe(sexe);
            ai.setNationalite(a.getNationalite());
            ai.setSitFamille(a.getSitFamille());
            //mapping du code categorie professionnel
            String codCatProf=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("PROF",a.getCodCatProf());
            ai.setCodCatProf(codCatProf);
            ai.setMenage(a.getMenage());
            ai.setQualAcadem(a.getQualAcadem());
            ai.setCatClient(a.getCatClient());
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getDonneesInts_pp().add(ai);
        }


        //-------------------------------ACT----------------------------

        for (sss_cdr_snapshot_client_act_interm a : inter_client_stat.getActionnariats()) {
            sss_cdr_client_act ai = new sss_cdr_client_act();

            ai.setId(a.getId());
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
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getActionnariats().add(ai);
        }


        //-----------------------BENEF------------------------------------
        for (sss_cdr_snapshot_client_benef_interm a : inter_client_stat.getBenEffects()) {
            sss_cdr_client_benef ai = new sss_cdr_client_benef();

            ai.setId(a.getId());
            ai.setTypIdBenEffect(a.getTypIdBenEffect());
            ai.setIdBenEffect(a.getIdBenEffect());
            ai.setNomBenEffect(a.getNomBenEffect());
            ai.setPreBenEffect(a.getPreBenEffect());
            ai.setNatBenEffect(a.getNatBenEffect());
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getBenEffects().add(ai);
        }



//saving sss_cdr_client_stat
        sss_cdr_client_stat_Repository.save(sss_cdr_client_stat);

                return sss_cdr_client_stat;


    }


}
