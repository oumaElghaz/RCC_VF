package ma.vivalis.BKAM_CDR_API1.services;

import generated.*;
import jakarta.transaction.Transactional;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_client_act;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_client_benef;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_Adresse;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_DonneesIntPM;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_DonneesIntPP;
import ma.vivalis.BKAM_CDR_API1.services.generationXML.XmlGenerator;
import ma.vivalis.BKAM_CDR_API1.services.mapping.client_stat_mapp;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_inter_client_stat_Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional
public class sss_cdr_client_stat_service_impl {

    private static final Logger logger = Logger.getLogger(sss_cdr_client_stat_service_impl.class.getName());
    private static final int BATCH_SIZE = 500;

    private final sss_cdr_inter_client_stat_service_impl sss_cdr_inter_client_stat_service_impl;
    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    private final client_stat_mapp client_stat_mapp;
    private final LotSequenceService_impl lotSequenceService_impl;

    public sss_cdr_client_stat_service_impl(
            sss_cdr_inter_client_stat_service_impl sssCdrInterClientStatServiceImpl,
            sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository,
            client_stat_mapp clientStatMapp,
            LotSequenceService_impl lotSequenceServiceImpl) {
        sss_cdr_inter_client_stat_service_impl = sssCdrInterClientStatServiceImpl;
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
        client_stat_mapp = clientStatMapp;
        lotSequenceService_impl = lotSequenceServiceImpl;
    }


     //Mapper un batch de clients en ComEnt
     // Ajoute CHAQUE client en DonneesEnt
    public ComEnt mapBatchToComEnt(List<sss_cdr_client_stat> clients) throws DatatypeConfigurationException {
        if (clients == null || clients.isEmpty()) {
            logger.warning("Liste de clients vide !");
            return new ComEnt();
        }

        ComEnt comEnt = new ComEnt();

        logger.info("Mapping " + clients.size() + " clients...");

        for (int i = 0; i < clients.size(); i++) {
            sss_cdr_client_stat client = clients.get(i);
            try {
                ComEnt.DonneesEnt d_ent = mapClientToXmlElement(client);
                comEnt.getDonneesEnt().add(d_ent);
                logger.fine("Client " + (i+1) + "/" + clients.size() + " mappé");
            } catch (Exception e) {
                logger.warning("Erreur client " + (client != null ? client.getId_client() : "null") + " : " + e.getMessage());
                e.printStackTrace();
            }
        }

        logger.info( comEnt.getDonneesEnt().size() + " clients mappés avec succès");
        return comEnt;
    }


     //CORRECTION : Mapper un client en DonneesEnt
    private ComEnt.DonneesEnt mapClientToXmlElement(sss_cdr_client_stat client) throws DatatypeConfigurationException {
        if (client == null) {
            throw new IllegalArgumentException("Client null !");
        }

        ComEnt.DonneesEnt d_ent = new ComEnt.DonneesEnt();

        // Infos de base
        d_ent.setDtRefEnt(convertDateToXml(client.getDtRefEnt()));

        if (client.getActionType() != null) {
            d_ent.setActionType(client.getActionType().toString());
        } else {
            d_ent.setActionType("");
        }

        d_ent.setCodClient(client.getCodClient());
        d_ent.setNatClient(client.getNatClient());
        d_ent.setEntLieeEtab(client.getEntLieeEtab());
        d_ent.setCodAgEcon(client.getCodAgEcon());

        // Données Personne Physique
        if (client.getDonneesInt_pp() != null) {
            mapDonneesIntPP(d_ent, client.getDonneesInt_pp());
        }

        // Données Personne Morale
        if (client.getDonneesInt_pm() != null) {
            mapDonneesIntPM(d_ent, client.getDonneesInt_pm());
        }

        // Adresses
        if (client.getAdresse() != null) {
                sss_cdr_Adresse a = client.getAdresse();

                    ComEnt.DonneesEnt.Address adr = new ComEnt.DonneesEnt.Address();
                    adr.setAdresse(a.getAdresse());
                    adr.setCodPostal(a.getCodPostal());
                    adr.setCodLocal(a.getCodLocal());
                    adr.setCodPays(a.getCodPays());
                    adr.setNumTeleph(a.getNumTeleph());
                    d_ent.getAddress().add(adr);


        }

        // Actionnariats
        if (client.getActionnariats() != null && !client.getActionnariats().isEmpty()) {
            ComEnt.DonneesEnt.LstActionnariat t = new ComEnt.DonneesEnt.LstActionnariat();
            for (sss_cdr_client_act act : client.getActionnariats()) {
                if (act != null) {
                    ComEnt.DonneesEnt.LstActionnariat.Actionnariat b = new ComEnt.DonneesEnt.LstActionnariat.Actionnariat();
                    b.setNatActionnaire(act.getNatActionnaire());
                    b.setFormJurAct(act.getFormJurAct());
                    b.setCodTribunAct(act.getCodTribunAct());
                    b.setRegCommerAct(act.getRegCommerAct());
                    b.setICEAct(act.getICEAct());
                    b.setLEIAct(act.getLEIAct());
                    b.setPayResAct(act.getPayResAct());
                    b.setNomRaisonSocAct(act.getNomRaisonSocAct());
                    if (act.getQtpartCapSocAct() != null) {
                        b.setQtpartCapSocAct(BigDecimal.valueOf(act.getQtpartCapSocAct()));
                    }
                    t.getActionnariat().add(b);
                }
            }
            if (!t.getActionnariat().isEmpty()) {
                d_ent.getLstActionnariat().add(t);
            }
        }

        // Bénéficiaires
        if (client.getBenEffects() != null && !client.getBenEffects().isEmpty()) {
            ComEnt.DonneesEnt.LstBenEffect f = new ComEnt.DonneesEnt.LstBenEffect();
            for (sss_cdr_client_benef bnf : client.getBenEffects()) {
                if (bnf != null) {
                    ComEnt.DonneesEnt.LstBenEffect.BenEffect n = new ComEnt.DonneesEnt.LstBenEffect.BenEffect();
                    n.setTypIdBenEffect(bnf.getTypIdBenEffect());
                    n.setIdBenEffect(bnf.getIdBenEffect());
                    n.setNomBenEffect(bnf.getNomBenEffect());
                    n.setPreBenEffect(bnf.getPreBenEffect());
                    n.setNatBenEffect(bnf.getNatBenEffect());
                    f.getBenEffect().add(n);
                }
            }
            if (!f.getBenEffect().isEmpty()) {
                d_ent.getLstBenEffect().add(f);
            }
        }

        return d_ent;
    }

    private void mapDonneesIntPP(ComEnt.DonneesEnt d_ent, sss_cdr_DonneesIntPP pp) throws DatatypeConfigurationException {
        if (pp == null) return;

        d_ent.setTpIdPrincipal(pp.getTpIdPrincipal());
        d_ent.setIdPrincipal(pp.getIdPrincipal());
        d_ent.setPrenom(pp.getPrenom());
        d_ent.setNomFamille(pp.getNomFamille());
        d_ent.setPaysDelivrance(pp.getPaysDelivrance());
        d_ent.setDtDelivrance(convertDateToXml(pp.getDtDelivrance()));
        d_ent.setDtExpiration(convertDateToXml(pp.getDtExpiration()));
        d_ent.setTypePPPro(pp.getTypePPPro());
        d_ent.setRNAE(pp.getRNAE());
        d_ent.setDtNaissance(convertDateToXml(pp.getDtNaissance()));
        d_ent.setCodLocalNaissance(pp.getCodLocalNaissance());
        d_ent.setSexe(pp.getSexe());
        d_ent.setNationalite(pp.getNationalite());
        d_ent.setSitFamille(pp.getSitFamille());
        d_ent.setCodCatProf(pp.getCodCatProf());
        if (pp.getMenage() != null) {
            d_ent.setMenage(BigInteger.valueOf(pp.getMenage()));
        }
        d_ent.setQualAcadem(pp.getQualAcadem());
        d_ent.setCatClient(pp.getCatClient());
    }

    private void mapDonneesIntPM(ComEnt.DonneesEnt d_ent, sss_cdr_DonneesIntPM pm) throws DatatypeConfigurationException {
        if (pm == null) return;

        d_ent.setRaisonSocial(pm.getRaisonSocial());
        d_ent.setSigle(pm.getSigle());
        d_ent.setFormJur(pm.getFormJur());
        d_ent.setCodTrib(pm.getCodTrib());
        d_ent.setRegCommerce(pm.getRegCommerce());
        d_ent.setICE(pm.getICE());
        d_ent.setIdFiscal(pm.getIdFiscal());
        d_ent.setNumTaxeProf(pm.getNumTaxeProf());
        d_ent.setIdSpecifique(pm.getIdSpecifique());
        d_ent.setCodLEI(pm.getCodLEI());
        d_ent.setCodActPrinc(pm.getCodActPrinc());
        d_ent.setCodActSec(pm.getCodActSec());
        d_ent.setTailleEntrep(pm.getTailleEntrep());
        d_ent.setGenre(pm.getGenre());
        d_ent.setDtCreation(convertDateToXml(pm.getDtCreation()));
        d_ent.setNatMod(pm.getNatMod());
        d_ent.setDtMod(convertDateToXml(pm.getDtMod()));
        d_ent.setFlagSuc(pm.getFlagSuc());
        d_ent.setTpIdPrincSiege(pm.getTpIdPrincSiege());
        d_ent.setIdPrincSiege(pm.getIdPrincSiege());
        d_ent.setRaisonSocSiege(pm.getRaisonSocSiege());
        d_ent.setGroupAppart(pm.getGroupAppart());
    }

    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    public void generer_xml_clientStat_optimized(String file) throws DatatypeConfigurationException {
        long globalStart = System.currentTimeMillis();
        logger.info("DÉBUT GÉNÉRATION XML");

        // Étape 1 : Créer les intermédiaires
        logger.info("Création des données intermédiaires...");
        sss_cdr_inter_client_stat_service_impl.sss_cdr_inter_client_stat_create();

        // Étape 2 : Récupérer le lot
        Integer maxLot = lotSequenceService_impl.getMax();
        if (maxLot == null) {
            throw new RuntimeException("ERREUR : Aucun lot trouvé dans la base !");
        }
        logger.info("Lot trouvé : " + maxLot);

        // Étape 3 : Compter les clients
        long totalClients = sss_cdr_inter_client_stat_Repository.countByIdLot(maxLot);
        logger.info("Total clients dans lot " + maxLot + " : " + totalClients);

        if (totalClients == 0) {
            throw new RuntimeException("ERREUR : Aucun client trouvé pour le lot " + maxLot);
        }

        long totalPages = (totalClients + BATCH_SIZE - 1) / BATCH_SIZE;
        logger.info(" Nombre de batchs : " + totalPages);

        // Récupérer le premier client pour l initialisation du fichier
        logger.info("Récupération des infos pour le Controle...");
        sss_cdr_client_stat firstClient = null;
        Pageable firstPageable = PageRequest.of(0, 1);
        Page<sss_cdr_inter_client_stat> firstPage = sss_cdr_inter_client_stat_Repository.findByIdLot(maxLot, firstPageable);

        if (firstPage.hasContent()) {
            sss_cdr_inter_client_stat inter = firstPage.getContent().get(0);
            //initializeCollections(inter);
            firstClient = client_stat_mapp.mapp_donnees_client_inter_final(inter);
        }

        // Initialiser le fichier
        logger.info("Initialisation du fichier XML...");
        XmlGenerator.initializeXmlFileWithControle(file, firstClient);

        // ============= TRAITER PAR BATCHS =============
        logger.info("Traitement des batchs...");
        long clientsProcessed = 0;
        for (int pageNum = 0; pageNum < totalPages; pageNum++) {
            long batchStart = System.currentTimeMillis();

            try {
                logger.info("Récupération batch " + (pageNum + 1) + "/" + totalPages);

                Pageable pageable = PageRequest.of(pageNum, BATCH_SIZE);
                Page<sss_cdr_inter_client_stat> pageResults = sss_cdr_inter_client_stat_Repository.findByIdLot(maxLot, pageable);

                List<sss_cdr_inter_client_stat> batchClientsInter = pageResults.getContent();
                logger.info("Batch reçu : " + batchClientsInter.size() + " clients intermédiaires");

                if (!batchClientsInter.isEmpty()) {
                    // Forcer initialisation pour tous les clients du batch
                    //for (sss_cdr_inter_client_stat inter : batchClientsInter) {
                        //initializeCollections(inter);
                    //}

                    logger.info("Mapping batch...");
                    List<sss_cdr_client_stat> batchClientsMapped = new ArrayList<>();
                    for (sss_cdr_inter_client_stat inter : batchClientsInter) {
                        try {
                            sss_cdr_client_stat mapped = client_stat_mapp.fct_mapp_donnees_client(inter);
                            batchClientsMapped.add(mapped);


                        } catch (Exception e) {
                            logger.warning(" Erreur mapping client " + inter.getId_client() + " : " + e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    logger.info("Batch mappé : " + batchClientsMapped.size() + " clients finaux");

                    if (!batchClientsMapped.isEmpty()) {
                        ComEnt comEnt = mapBatchToComEnt(batchClientsMapped);

                        if (comEnt != null && comEnt.getDonneesEnt() != null && !comEnt.getDonneesEnt().isEmpty()) {
                            logger.info("ComEnt créé avec " + comEnt.getDonneesEnt().size() + " DonneesEnt");
                            XmlGenerator.appendDonneesEntToXmlFile(file, comEnt);
                            logger.info("Ajouté au fichier");
                        } else {
                            logger.warning("ComEnt vide après mapping !");
                        }

                        clientsProcessed += batchClientsMapped.size();
                        long batchTime = System.currentTimeMillis() - batchStart;
                        double progress = (clientsProcessed * 100.0) / totalClients;

                        logger.info(String.format(
                                " Batch %d/%d : %dms - Progression : %.1f%% (%d/%d clients)",
                                pageNum + 1, totalPages, batchTime, progress, clientsProcessed, totalClients));
                    }
                } else {
                    logger.warning("Batch vide !");
                }
            } catch (Exception e) {
                logger.severe("ERREUR batch " + (pageNum + 1) + " : " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Erreur batch " + (pageNum + 1), e);
            }
        }

        // Fermer le fichier
        logger.info(" Fermeture du fichier...");
        XmlGenerator.closeXmlFile(file);

        long globalTime = System.currentTimeMillis() - globalStart;
        long fileSize = XmlGenerator.getXmlFileSize(file);

        logger.info("════════════════════════════════════════════════");
        logger.info(" SUCCÈS - GÉNÉRATION TERMINÉE !");
        logger.info("════════════════════════════════════════════════");
        logger.info("   Temps total : " + globalTime + "ms");
        logger.info("   Clients traités : " + clientsProcessed);
        logger.info("   Taille fichier : " + (fileSize / 1024.0 / 1024.0) + " MB");
        logger.info("   Fichier : " + file);
        logger.info("════════════════════════════════════════════════");
    }


    /**
     *  NOUVEAU : Force l'initialisation de toutes les collections
     * Cela garantit que les données sont chargées DANS la transaction
     */
    private void initializeCollections(sss_cdr_inter_client_stat client) {
        try {
            // Forcer le chargement en accédant aux collections
            if (client.getActionnariats() != null) {
                client.getActionnariats().size();
            }
            if (client.getBenEffects() != null) {
                client.getBenEffects().size();
            }
            logger.fine("Collections initialisées pour client " + client.getId_client());
        } catch (Exception e) {
            logger.warning("Erreur initialisation collections : " + e.getMessage());
        }
    }
}