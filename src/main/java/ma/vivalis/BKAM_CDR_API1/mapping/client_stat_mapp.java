package ma.vivalis.BKAM_CDR_API1.mapping;

import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_client_stat_Repository;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_mapping_Repository;
import org.springframework.stereotype.Service;


@Service
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
        //mapping de ENTLIEEETAB A CHERCHER
        String ENTLIEEETAB=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("ENTLIEEETAB",inter_client_stat.getEntLieeEtab());
        sss_cdr_client_stat.setEntLieeEtab(ENTLIEEETAB);
        //mapping de codeag
        String codAgEcon=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SACT",inter_client_stat.getCodAgEcon());
        sss_cdr_client_stat.setCodAgEcon(codAgEcon);

//--------------adresse-------------------------------------------
        for (Adresse_interm a : inter_client_stat.getAdresses()) {
            sss_cdr_Adresse adr = new sss_cdr_Adresse();
            adr.setAdresse(a.getAdresse());
            adr.setCodPostal(a.getCodPostal());
            //mapping du code local A CHERCHER
            String codLocal=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CodLocal",a.getCodLocal());
            adr.setCodLocal(codLocal);
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
            //mapping de forme juridique
            String formJur=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("FJR",a.getFormJur());
            ai.setFormJur(formJur);
            //mapping du code tribunal
            String codTrib=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CDTR",a.getCodTrib());
            ai.setCodTrib(codTrib);
            ai.setRegCommerce(a.getRegCommerce());
            ai.setICE(a.getICE());
            ai.setIdFiscal(a.getIdFiscal());
            ai.setNumTaxeProf(a.getNumTaxeProf());
            ai.setIdSpecifique(a.getIdSpecifique());
            ai.setCodLEI(a.getCodLEI());
            //mapping du code activite
            String codActPrinc=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SACT",a.getCodActPrinc());
            ai.setCodActPrinc(codActPrinc);
            //mapping du code activite
            String codActSec=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SACT",a.getCodActSec());
            ai.setCodActSec(codActSec);
            //mapping de la taille A CHERCHER
            String tailleEntrep=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("TailleEntrep",a.getTailleEntrep());
            ai.setTailleEntrep(tailleEntrep);
            ai.setGenre(a.getGenre());
            ai.setDtCreation(a.getDtCreation());
            //mapping nature modification A CHERCHER
            String natMod=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("NatMod",a.getNatMod());
            ai.setNatMod(natMod);
            ai.setDtMod(a.getDtMod());
            ai.setFlagSuc(a.getFlagSuc());
            String tpIdPrincSiege=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("TYPDOC",a.getTpIdPrincSiege());
            ai.setTpIdPrincSiege(tpIdPrincSiege);
            ai.setIdPrincSiege(a.getIdPrincSiege());
            ai.setRaisonSocial(a.getRaisonSocial());
            //mapping du gr appt A CHERCHER
            String groupAppart=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("GroupAppart",a.getGroupAppart());
            ai.setGroupAppart(groupAppart);
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getDonneesInts_pm().add(ai);
        }




//--------------donneeesIntPP-------------------------------------------
        for (DonneesIntPP_interm a : inter_client_stat.getDonneesInts_pp()) {
            sss_cdr_DonneesIntPP ai = new sss_cdr_DonneesIntPP();
            ai.setIdPrincipal(a.getIdPrincipal());
            //mapping du type de document A CHERCHER
            String tpIdPrincipal=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("TYPDOC",a.getTpIdPrincipal());
            ai.setTpIdPrincipal(tpIdPrincipal);
            ai.setPrenom(a.getPrenom());
            ai.setNomFamille(a.getNomFamille());
            //mapping du pays de delivrance
            String paysDelivrance=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("PAYS",a.getPaysDelivrance());
            ai.setPaysDelivrance(paysDelivrance);
            ai.setDtDelivrance(a.getDtDelivrance());
            ai.setDtExpiration(a.getDtExpiration());
            //mapping de type
            String typePPPro=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SACT",a.getTypePPPro());
            ai.setTypePPPro(typePPPro);
            ai.setRNAE(a.getRNAE());
            ai.setDtNaissance(a.getDtNaissance());
            //mapping du code localite A CHERCHER
            String CodLocalNaissance=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CodLocal",a.getCodLocalNaissance());
            ai.setCodLocalNaissance(CodLocalNaissance);
            //mapping du sexe
            String sexe=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SEXE",a.getSexe());
            ai.setSexe(sexe);
            //mapping de la nationalite
            String nationalite=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("NATI",a.getNationalite());
            ai.setNationalite(nationalite);
            //mapping de etat civil SIT_F
            String sit_f=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("SIT_F",a.getSitFamille());
            ai.setSitFamille(sit_f);
            //mapping du code categorie professionnel
            String codCatProf=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("PROF",a.getCodCatProf());
            ai.setCodCatProf(codCatProf);
            ai.setMenage(a.getMenage());
            //mapping de nuveau academique A CHERCHER
            String qualAcadem=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("QualAcadem",a.getQualAcadem());
            ai.setQualAcadem(qualAcadem);
            //mapping de la categorie client
            String catClient=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("RSDT",a.getCatClient());
            ai.setCatClient(catClient);
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getDonneesInts_pp().add(ai);
        }


        //-------------------------------ACT----------------------------

        for (sss_cdr_snapshot_client_act_interm a : inter_client_stat.getActionnariats()) {
            sss_cdr_client_act ai = new sss_cdr_client_act();

            //ai.setId(a.getId());
            //mapping du nature actionnaire
            String natActionnaire=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CDTYPT",a.getNatActionnaire());
            ai.setNatActionnaire(natActionnaire);
            //mapping du forme juridique
            String FormJurAct=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("FJR",a.getFormJurAct());
            ai.setFormJurAct(FormJurAct);
            //mapping de type document A CHERCHER
            String TpIdPrincAct=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("TYPDOC",a.getTpIdPrincAct());
            ai.setTpIdPrincAct(TpIdPrincAct);
            ai.setIdPrincAct(a.getIdPrincAct());
            //mapping code tribunal
            String codTribAct=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("CDTR",a.getCodTribunAct());
            ai.setCodTribunAct(codTribAct);
            ai.setRegCommerAct(a.getRegCommerAct());
            ai.setIdSpecifiqueAct(a.getIdSpecifiqueAct());
            ai.setICEAct(a.getICEAct());
            ai.setLEIAct(a.getLEIAct());
            //mapping du pays
            String codPaysAct=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("PAYS",a.getPayResAct());
            ai.setPayResAct(codPaysAct);
            ai.setNomRaisonSocAct(a.getNomRaisonSocAct());
            ai.setQtpartCapSocAct(a.getQtpartCapSocAct());
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getActionnariats().add(ai);
        }


        //-----------------------BENEF------------------------------------
        for (sss_cdr_snapshot_client_benef_interm a : inter_client_stat.getBenEffects()) {
            sss_cdr_client_benef ai = new sss_cdr_client_benef();

            //ai.setId(a.getId());
            //mapping du type document A CHERCHER
            String TypIdBenEffect=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("TYPDOC",a.getTypIdBenEffect());
            ai.setTypIdBenEffect(TypIdBenEffect);
            ai.setIdBenEffect(a.getIdBenEffect());
            ai.setNomBenEffect(a.getNomBenEffect());
            ai.setPreBenEffect(a.getPreBenEffect());
            //mapping de la nationalite
            String natBenEffect=sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc("NATI",a.getNatBenEffect());
            ai.setNatBenEffect(natBenEffect);
            ai.setClient(sss_cdr_client_stat);
            sss_cdr_client_stat.getBenEffects().add(ai);
        }



        //saving sss_cdr_client_stat
        sss_cdr_client_stat_Repository.save(sss_cdr_client_stat);

                return sss_cdr_client_stat;


    }




}
