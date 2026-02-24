package ma.vivalis.BKAM_CDR_API1.services.mapping;

import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.mapping.sss_cdr_mapping;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_client_stat_Repository;
import ma.vivalis.BKAM_CDR_API1.repositories.mapping.sss_cdr_mapping_Repository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class client_stat_mapp {

    private static final Logger logger = Logger.getLogger(client_stat_mapp.class.getName());

    private final sss_cdr_mapping_Repository sss_cdr_mapping_Repository;
    private final sss_cdr_client_stat_Repository sss_cdr_client_stat_Repository;

    // Cache en mémoire pour les mappings
    private final Map<String, Map<String, String>> mappingCache = new HashMap<>();
    private final Object cacheLock = new Object();

    public client_stat_mapp(sss_cdr_mapping_Repository sssCdrMappingRepository,
                            sss_cdr_client_stat_Repository sssCdrClientStatRepository) {
        sss_cdr_mapping_Repository = sssCdrMappingRepository;
        sss_cdr_client_stat_Repository = sssCdrClientStatRepository;
    }


     //OPTIMISÉ : Précharge TOUS les mappings une seule fois
     //Appel cette méthode au démarrage pour remplir le cache
    public void initializeMappingCache() {
        logger.info("Initialisation du cache de mappings...");

        synchronized (cacheLock) {
            long startTime = System.currentTimeMillis();

            // Lister tous les codes de table uniques utilisés dans l'application
            List<String> ctabs = Arrays.asList(
                    "CDTYPT",        // Type de client (PP/PM)
                    "ENTLIEEETAB",   // Entité liée établissement
                    "SACT",          // Secteur/activité
                    "CodLocal",      // Code localité
                    "PAYS",          // Pays
                    "FJR",           // Forme juridique
                    "CDTR",          // Code tribunal
                    "TailleEntrep",  // Taille entreprise
                    "NatMod",        // Nature modification
                    "TYPDOC",        // Type document
                    "GroupAppart",   // Groupe appartenance
                    "SEXE",          // Sexe
                    "NATI",          // Nationalité
                    "SIT_F",         // Situation familiale
                    "PROF",          // Catégorie professionnelle
                    "QualAcadem",    // Qualification académique
                    "RSDT"           // Catégorie client (Résident/Non-résident)
            );

            for (String ctab : ctabs) {
                try {
                    logger.info(" Chargement des mappings pour : " + ctab);

                    // Charger TOUS les mappings pour cette table
                    Map<String, String> mapping = sss_cdr_mapping_Repository
                            .findAllByCtab(ctab)
                            .stream()
                            .collect(Collectors.toMap(
                                    sss_cdr_mapping::getCodSrc,
                                    sss_cdr_mapping::getCodCibl,
                                    (existing, replacement) -> existing // En cas de doublon, garder le premier
                            ));

                    mappingCache.put(ctab, mapping);
                    logger.info( ctab + " : " + mapping.size() + " mappings chargés");

                } catch (Exception e) {
                    logger.warning("Erreur lors du chargement de " + ctab + " : " + e.getMessage());
                    // Continuer avec les autres mappings
                }
            }

            long elapsed = System.currentTimeMillis() - startTime;
            logger.info("Cache de mappings initialisé en " + elapsed + "ms");
            logger.info("Total tables en cache : " + mappingCache.size());
        }
    }


     //Récupère un mapping depuis le cache
    private String getMappingFromCache(String ctab, String codSrc) {
        if (codSrc == null || codSrc.isEmpty()) {
            return null;
        }

        Map<String, String> mapping = mappingCache.get(ctab);
        if (mapping == null) {
            // Charger à la demande si pas en cache
            logger.warning("Cache manquant pour " + ctab + ". Fallback sur DB pour : " + codSrc);
            return sss_cdr_mapping_Repository.findCodCiblByCtabAndCodSrc(ctab, codSrc);
        }

        // Retourner le mapping ou codSrc si pas trouvé
        return mapping.getOrDefault(codSrc, codSrc);
    }

   // Mappe un client intermédiaire en client final
    public sss_cdr_client_stat fct_mapp_donnees_client(sss_cdr_inter_client_stat inter_client_stat) {


        long startTime = System.currentTimeMillis();
        sss_cdr_client_stat client_stat =mapp_donnees_client_inter_final(inter_client_stat) ;


        // Sauvegarder
        sss_cdr_client_stat_Repository.save(client_stat);

        long elapsed = System.currentTimeMillis() - startTime;
        logger.fine("✓ Client " + inter_client_stat.getId_client() + " mappé en " + elapsed + "ms");

        return client_stat;
    }

    // Map les adresses avec cache de mappings
    private void mapAdresses(sss_cdr_client_stat client_stat, sss_cdr_inter_client_stat inter_client_stat) {
            Adresse_interm a = inter_client_stat.getAdresse();
            sss_cdr_Adresse adr = new sss_cdr_Adresse();
            adr.setAdresse(a.getAdresse());
            adr.setCodPostal(a.getCodPostal());
            adr.setCodLocal(getMappingFromCache("CodLocal", a.getCodLocal()));
            adr.setCodPays(getMappingFromCache("PAYS", a.getCodPays()));
            adr.setNumTeleph(a.getNumTeleph());
            client_stat.setAdresse(adr);

    }


     //Map les DonneesIntPM avec cache
    private void mapDonneesIntPM(sss_cdr_client_stat client_stat, sss_cdr_inter_client_stat inter_client_stat) {
            DonneesIntPM_interm a = inter_client_stat.getDonneesInt_pm();
            sss_cdr_DonneesIntPM ai = new sss_cdr_DonneesIntPM();
            ai.setRaisonSocial(a.getRaisonSocial());
            ai.setSigle(a.getSigle());
            ai.setFormJur(getMappingFromCache("FJR", a.getFormJur()));
            ai.setCodTrib(getMappingFromCache("CDTR", a.getCodTrib()));
            ai.setRegCommerce(a.getRegCommerce());
            ai.setICE(a.getICE());
            ai.setIdFiscal(a.getIdFiscal());
            ai.setNumTaxeProf(a.getNumTaxeProf());
            ai.setIdSpecifique(a.getIdSpecifique());
            ai.setCodLEI(a.getCodLEI());
            ai.setCodActPrinc(getMappingFromCache("SACT", a.getCodActPrinc()));
            ai.setCodActSec(getMappingFromCache("SACT", a.getCodActSec()));
            ai.setTailleEntrep(getMappingFromCache("TailleEntrep", a.getTailleEntrep()));
            ai.setGenre(a.getGenre());
            ai.setDtCreation(a.getDtCreation());
            ai.setNatMod(getMappingFromCache("NatMod", a.getNatMod()));
            ai.setDtMod(a.getDtMod());
            ai.setFlagSuc(a.getFlagSuc());
            ai.setTpIdPrincSiege(getMappingFromCache("TYPDOC", a.getTpIdPrincSiege()));
            ai.setIdPrincSiege(a.getIdPrincSiege());
            ai.setRaisonSocial(a.getRaisonSocial());
            ai.setGroupAppart(getMappingFromCache("GroupAppart", a.getGroupAppart()));
            client_stat.setDonneesInt_pm(ai);

    }


     // Map les DonneesIntPP avec cache
    private void mapDonneesIntPP(sss_cdr_client_stat client_stat, sss_cdr_inter_client_stat inter_client_stat) {
            DonneesIntPP_interm a = inter_client_stat.getDonneesInt_pp();
            sss_cdr_DonneesIntPP ai = new sss_cdr_DonneesIntPP();
            ai.setIdPrincipal(a.getIdPrincipal());
            ai.setTpIdPrincipal(getMappingFromCache("TYPDOC", a.getTpIdPrincipal()));
            ai.setPrenom(a.getPrenom());
            ai.setNomFamille(a.getNomFamille());
            ai.setPaysDelivrance(getMappingFromCache("PAYS", a.getPaysDelivrance()));
            ai.setDtDelivrance(a.getDtDelivrance());
            ai.setDtExpiration(a.getDtExpiration());
            ai.setTypePPPro(getMappingFromCache("SACT", a.getTypePPPro()));
            ai.setRNAE(a.getRNAE());
            ai.setDtNaissance(a.getDtNaissance());
            ai.setCodLocalNaissance(getMappingFromCache("CodLocal", a.getCodLocalNaissance()));
            ai.setSexe(getMappingFromCache("SEXE", a.getSexe()));
            ai.setNationalite(getMappingFromCache("NATI", a.getNationalite()));
            ai.setSitFamille(getMappingFromCache("SIT_F", a.getSitFamille()));
            ai.setCodCatProf(getMappingFromCache("PROF", a.getCodCatProf()));
            ai.setMenage(a.getMenage());
            ai.setQualAcadem(getMappingFromCache("QualAcadem", a.getQualAcadem()));
            ai.setCatClient(getMappingFromCache("RSDT", a.getCatClient()));
            client_stat.setDonneesInt_pp(ai);

    }


     // Map les actionnariats avec cache
    private void mapActionnariats(sss_cdr_client_stat client_stat, sss_cdr_inter_client_stat inter_client_stat) {
        for (sss_cdr_inter_client_act a : inter_client_stat.getActionnariats()) {
            sss_cdr_client_act ai = new sss_cdr_client_act();

            ai.setNatActionnaire(getMappingFromCache("CDTYPT", a.getNatActionnaire()));
            ai.setFormJurAct(getMappingFromCache("FJR", a.getFormJurAct()));
            ai.setTpIdPrincAct(getMappingFromCache("TYPDOC", a.getTpIdPrincAct()));
            ai.setIdPrincAct(a.getIdPrincAct());
            ai.setCodTribunAct(getMappingFromCache("CDTR", a.getCodTribunAct()));
            ai.setRegCommerAct(a.getRegCommerAct());
            ai.setIdSpecifiqueAct(a.getIdSpecifiqueAct());
            ai.setICEAct(a.getICEAct());
            ai.setLEIAct(a.getLEIAct());
            ai.setPayResAct(getMappingFromCache("PAYS", a.getPayResAct()));
            ai.setNomRaisonSocAct(a.getNomRaisonSocAct());
            ai.setQtpartCapSocAct(a.getQtpartCapSocAct());
            ai.setClient(client_stat);
            client_stat.getActionnariats().add(ai);
        }
    }


     // Map les bénéficiaires avec cache
    private void mapBeneficiaires(sss_cdr_client_stat client_stat, sss_cdr_inter_client_stat inter_client_stat) {
        for (sss_cdr_inter_client_benef a : inter_client_stat.getBenEffects()) {
            sss_cdr_client_benef ai = new sss_cdr_client_benef();

            ai.setTypIdBenEffect(getMappingFromCache("TYPDOC", a.getTypIdBenEffect()));
            ai.setIdBenEffect(a.getIdBenEffect());
            ai.setNomBenEffect(a.getNomBenEffect());
            ai.setPreBenEffect(a.getPreBenEffect());
            ai.setNatBenEffect(getMappingFromCache("NATI", a.getNatBenEffect()));
            ai.setClient(client_stat);
            client_stat.getBenEffects().add(ai);
        }
    }


     // Vider le cache
    public void clearMappingCache() {
        synchronized (cacheLock) {
            mappingCache.clear();
            logger.info(" Cache de mappings vidé");
        }
    }


     //Obtenir les statistiques du cache
    public Map<String, Integer> getCacheStats() {
        synchronized (cacheLock) {
            Map<String, Integer> stats = new HashMap<>();
            for (Map.Entry<String, Map<String, String>> entry : mappingCache.entrySet()) {
                stats.put(entry.getKey(), entry.getValue().size());
            }
            return stats;
        }
    }

    public sss_cdr_client_stat mapp_donnees_client_inter_final(sss_cdr_inter_client_stat inter_client_stat) {
        sss_cdr_client_stat client_stat = new sss_cdr_client_stat();



        // Infos de base
        client_stat.setId_client(inter_client_stat.getId_client());

        client_stat.setId_lot(inter_client_stat.getId_lot());
        logger.info("Mapping du client " + client_stat.getId_client() + " (Lot " + client_stat.getId_lot() + ")");
        client_stat.setDateExtraction(inter_client_stat.getDateExtraction());
        logger.info("lllllllllll   "+client_stat.getDateExtraction());
        client_stat.setEntObserv(inter_client_stat.getEntObserv());
        client_stat.setEntDeclar(inter_client_stat.getEntDeclar());
        client_stat.setDtRefEnt(inter_client_stat.getDtRefEnt());
        client_stat.setActionType(inter_client_stat.getActionType());
        client_stat.setCodClient(inter_client_stat.getCodClient());
        client_stat.setAltCodClient(inter_client_stat.getAltCodClient());

        // Mapping avec CACHE
        client_stat.setNatClient(getMappingFromCache("CDTYPT", inter_client_stat.getNatClient()));
        client_stat.setEntLieeEtab(getMappingFromCache("ENTLIEEETAB", inter_client_stat.getEntLieeEtab()));
        client_stat.setCodAgEcon(getMappingFromCache("SACT", inter_client_stat.getCodAgEcon()));

        // Adresses
        if (inter_client_stat.getAdresse() != null ) {
            mapAdresses(client_stat, inter_client_stat);
        }

        // DonneesIntPM
        if (inter_client_stat.getDonneesInt_pm() != null ) {
            mapDonneesIntPM(client_stat, inter_client_stat);
        }

        // DonneesIntPP
        if (inter_client_stat.getDonneesInt_pp() != null ) {
            mapDonneesIntPP(client_stat, inter_client_stat);
        }

        // Actionnariats
        if (inter_client_stat.getActionnariats() != null && !inter_client_stat.getActionnariats().isEmpty()) {
            mapActionnariats(client_stat, inter_client_stat);
        }

        // Bénéficiaires
        if (inter_client_stat.getBenEffects() != null && !inter_client_stat.getBenEffects().isEmpty()) {
            mapBeneficiaires(client_stat, inter_client_stat);
        }

        return client_stat;
    }


     //Afficher les statistiques du cache dans les logs
    public void logCacheStats() {
        Map<String, Integer> stats = getCacheStats();
        logger.info("═══════════════════════════════════════════════");
        logger.info(" STATISTIQUES DU CACHE DE MAPPINGS");
        logger.info("═══════════════════════════════════════════════");

        int totalMappings = 0;
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            logger.info("  • " + String.format("%-15s", entry.getKey()) + " : " + entry.getValue() + " mappings");
            totalMappings += entry.getValue();
        }

        logger.info("═══════════════════════════════════════════════");
        logger.info("TOTAL : " + totalMappings + " mappings en mémoire");
        logger.info("═══════════════════════════════════════════════");
    }

}