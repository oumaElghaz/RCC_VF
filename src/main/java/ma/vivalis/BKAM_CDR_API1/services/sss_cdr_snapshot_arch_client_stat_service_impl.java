package ma.vivalis.BKAM_CDR_API1.services;

import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_arch_client_stat_Repository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class sss_cdr_snapshot_arch_client_stat_service_impl {

    private static final Logger logger = Logger.getLogger(sss_cdr_snapshot_arch_client_stat_service_impl.class.getName());

    private final sss_cdr_arch_client_stat_Repository sss_cdr_arch_client_stat_Repository;

    public sss_cdr_snapshot_arch_client_stat_service_impl(
            sss_cdr_arch_client_stat_Repository sssCdrSnapshotArchClientStatRepository) {
        sss_cdr_arch_client_stat_Repository = sssCdrSnapshotArchClientStatRepository;
    }

    /**
     * OPTIMISÉ : Archive un client intermédiaire
     * - Extrait la logique dans des méthodes séparées pour lisibilité
     * - Gestion d'erreurs améliorée
     */
    public void create_arch_client_stat(sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
        try {
            long startTime = System.currentTimeMillis();

            sss_cdr_arch_client_stat cl_arc = new sss_cdr_arch_client_stat();

            // Infos de base
            mapBasicInfo(cl_arc, sss_cdr_inter_client_stat);

            // Adresses
            mapAdresses(cl_arc, sss_cdr_inter_client_stat);

            // DonneesIntPM
            mapDonneesIntPM(cl_arc, sss_cdr_inter_client_stat);

            // DonneesIntPP
            mapDonneesIntPP(cl_arc, sss_cdr_inter_client_stat);

            // Actionnariats
            mapActionnariats(cl_arc, sss_cdr_inter_client_stat);

            // Bénéficiaires
            mapBeneficiaires(cl_arc, sss_cdr_inter_client_stat);

            // Sauvegarder
            sss_cdr_arch_client_stat_Repository.save(cl_arc);

            long elapsed = System.currentTimeMillis() - startTime;
            logger.info(" Client " + sss_cdr_inter_client_stat.getId_client() + " archivé en " + elapsed + "ms");

        } catch (Exception e) {
            logger.severe(" Erreur lors de l'archivage du client " +
                    sss_cdr_inter_client_stat.getId_client() + " : " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erreur archivage client", e);
        }
    }

    /**
     * Map les informations de base
     */
    private void mapBasicInfo(sss_cdr_arch_client_stat cl_arc,
                              sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
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
    }

    /**
     * Map les adresses
     */
    private void mapAdresses(sss_cdr_arch_client_stat cl_arc,
                             sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
        Adresse_interm a = sss_cdr_inter_client_stat.getAdresse();
            Adresse_Arch a_arch = new Adresse_Arch();
            a_arch.setAdresse(a.getAdresse());
            a_arch.setCodPostal(a.getCodPostal());
            a_arch.setCodLocal(a.getCodLocal());
            a_arch.setCodPays(a.getCodPays());
            a_arch.setNumTeleph(a.getNumTeleph());
            cl_arc.setAdresse(a_arch);


    }

    /**
     * Map les DonneesIntPM
     */
    private void mapDonneesIntPM(sss_cdr_arch_client_stat cl_arc,
                                 sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
        DonneesIntPM_interm a = sss_cdr_inter_client_stat.getDonneesInt_pm();
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
            cl_arc.setDonneesInts_pm(ai);

    }

    /**
     * Map les DonneesIntPP
     */
    private void mapDonneesIntPP(sss_cdr_arch_client_stat cl_arc,
                                 sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
        DonneesIntPP_interm a = sss_cdr_inter_client_stat.getDonneesInt_pp();
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
            cl_arc.setDonneesInts_pp(ai);

    }

    /**
     * Map les actionnariats
     */
    private void mapActionnariats(sss_cdr_arch_client_stat cl_arc,
                                  sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
        for (sss_cdr_inter_client_act a : sss_cdr_inter_client_stat.getActionnariats()) {
            sss_cdr_arch_client_act ai = new sss_cdr_arch_client_act();
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
    }

    /**
     * Map les bénéficiaires
     */
    private void mapBeneficiaires(sss_cdr_arch_client_stat cl_arc,
                                  sss_cdr_inter_client_stat sss_cdr_inter_client_stat) {
        for (sss_cdr_inter_client_benef a : sss_cdr_inter_client_stat.getBenEffects()) {
            sss_cdr_arch_client_benef ai = new sss_cdr_arch_client_benef();
            ai.setTypIdBenEffect(a.getTypIdBenEffect());
            ai.setIdBenEffect(a.getIdBenEffect());
            ai.setNomBenEffect(a.getNomBenEffect());
            ai.setPreBenEffect(a.getPreBenEffect());
            ai.setNatBenEffect(a.getNatBenEffect());
            ai.setClient(cl_arc);
            cl_arc.getBenEffects().add(ai);
        }
    }
}