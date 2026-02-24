package ma.vivalis.BKAM_CDR_API1.services;

import jakarta.transaction.Transactional;
import ma.vivalis.BKAM_CDR_API1.entities.*;
import ma.vivalis.BKAM_CDR_API1.entities.util.*;
import ma.vivalis.BKAM_CDR_API1.repositories.*;
import ma.vivalis.BKAM_CDR_API1.repositories.util.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class sss_cdr_inter_client_stat_service_impl {

    private static final Logger logger = Logger.getLogger(sss_cdr_inter_client_stat_service_impl.class.getName());
    private static final int BATCH_SIZE = 100; // Traiter 100 clients à la fois pour éviter OOM

    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    private final LotSequenceService_impl lotSequenceService_impl;
    private final Comparaison comparaison;
    private final sss_cdr_snapshot_client_stat_Repository sss_cdr_snapshot_client_stat_Repository;
    private final sss_cdr_arch_client_stat_Repository sss_cdr_arch_client_stat_Repository;
    private final Adresse_interm_Repository adresse_interm_Repository;
    private final AdresseRepository adresseRepository;
    private final DonneesIntPM_Repository donneesIntPM_Repository;
    private final DonneesIntPM_interm_Repository donneesIntPM_interm_Repository;
    private final DonneesIntPP_Repository donneesIntPP_Repository;
    private final DonneesIntPP_interm_Repository donneesIntPP_interm_Repository;
    private final sss_cdr_snapshot_client_act_Repository sss_cdr_snapshot_client_act_Repository;
    private final sss_cdr_inter_client_act_Repository sss_cdr_inter_client_act_Repository;
    private final sss_cdr_snapshot_client_benef_Repository sss_cdr_snapshot_client_benef_Repository;
    private final sss_cdr_inter_client_benef_Repository sss_cdr_inter_client_benef_Repository;
    private final sss_cdr_snapshot_arch_client_stat_service_impl sss_cdr_snapshot_arch_client_stat_service_impl;

    public sss_cdr_inter_client_stat_service_impl(
            sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository,
            LotSequenceService_impl lotSequenceServiceImpl,
            Comparaison comparaison,
            sss_cdr_snapshot_client_stat_Repository sssCdrSnapshotClientStatRepository,
            sss_cdr_arch_client_stat_Repository sssCdrSnapshotArchClientStatRepository,
            Adresse_interm_Repository adresseIntermRepository,
            AdresseRepository adresseRepository,
            DonneesIntPM_Repository donneesIntPMRepository,
            DonneesIntPM_interm_Repository donneesIntPMIntermRepository,
            DonneesIntPP_Repository donneesIntPPRepository,
            DonneesIntPP_interm_Repository donneesIntPPIntermRepository,
            sss_cdr_snapshot_client_act_Repository sssCdrSnapshotClientActRepository,
            sss_cdr_inter_client_act_Repository sssCdrSnapshotClientActIntermRepository,
            sss_cdr_snapshot_client_benef_Repository sssCdrSnapshotClientBenefRepository,
            sss_cdr_inter_client_benef_Repository sssCdrSnapshotClientBenefIntermRepository,
            sss_cdr_snapshot_arch_client_stat_service_impl sssCdrSnapshotArchClientStatServiceImpl) {
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
        lotSequenceService_impl = lotSequenceServiceImpl;
        this.comparaison = comparaison;
        sss_cdr_snapshot_client_stat_Repository = sssCdrSnapshotClientStatRepository;
        sss_cdr_arch_client_stat_Repository = sssCdrSnapshotArchClientStatRepository;
        adresse_interm_Repository = adresseIntermRepository;
        this.adresseRepository = adresseRepository;
        donneesIntPM_Repository = donneesIntPMRepository;
        donneesIntPM_interm_Repository = donneesIntPMIntermRepository;
        donneesIntPP_Repository = donneesIntPPRepository;
        donneesIntPP_interm_Repository = donneesIntPPIntermRepository;
        sss_cdr_snapshot_client_act_Repository = sssCdrSnapshotClientActRepository;
        sss_cdr_inter_client_act_Repository = sssCdrSnapshotClientActIntermRepository;
        sss_cdr_snapshot_client_benef_Repository = sssCdrSnapshotClientBenefRepository;
        sss_cdr_inter_client_benef_Repository = sssCdrSnapshotClientBenefIntermRepository;
        sss_cdr_snapshot_arch_client_stat_service_impl = sssCdrSnapshotArchClientStatServiceImpl;
    }

    /**
     * OPTIMISÉ : Traite les clients par batch pour éviter la saturation mémoire
     * - Récupère les clients à traiter
     * - Les traite par groupes de BATCH_SIZE
     * - Libère la mémoire après chaque batch
     */
    public void sss_cdr_inter_client_stat_create() {
        long startTime = System.currentTimeMillis();

        logger.info(" Début du traitement des clients...");

        // Récupérer les IDs des clients modifiés (petit volume)
        List<String> id_clients_modifies = comparaison.findModifiedAndNewIdsClients();
        logger.info( id_clients_modifies.size() + " clients à traiter");

        int idLot = lotSequenceService_impl.getNextLotId();
        logger.info(" Lot ID généré : " + idLot);

        int totalBatches = (int) Math.ceil((double) id_clients_modifies.size() / BATCH_SIZE);

        // Traiter par batchs
        for (int i = 0; i < totalBatches; i++) {
            long batchStart = System.currentTimeMillis();
            int fromIndex = i * BATCH_SIZE;
            int toIndex = Math.min(fromIndex + BATCH_SIZE, id_clients_modifies.size());

            List<String> batchIds = id_clients_modifies.subList(fromIndex, toIndex);
            logger.info(" Traitement batch " + (i + 1) + "/" + totalBatches + " (" + batchIds.size() + " clients)");

            processBatch(batchIds, idLot);

            long batchTime = System.currentTimeMillis() - batchStart;
            logger.info(" Batch " + (i + 1) + " traité en " + batchTime + "ms");
        }

        long totalTime = System.currentTimeMillis() - startTime;
        logger.info(" Tous les batchs traités en " + totalTime + "ms");
    }

    /**
     * Traite un batch de clients
     */
    private void processBatch(List<String> batchIds, int idLot) {
        // Récupérer les snapshots pour ce batch
        List<sss_cdr_snapshot_client_stat> clients_snapshot = batchIds.stream()
                .map(id -> sss_cdr_snapshot_client_stat_Repository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Client introuvable : " + id)))
                .collect(Collectors.toList());

        // Traiter chaque client
        for (sss_cdr_snapshot_client_stat client : clients_snapshot) {
            processSingleClient(client, idLot);
        }
    }

    /**
     * Traite un client unique et l'archive
     */
    private void processSingleClient(sss_cdr_snapshot_client_stat client, int idLot) {
        try {
            sss_cdr_inter_client_stat client_inter = createIntermediateClient(client, idLot);

            // Sauvegarder l'intermédiaire
            sss_cdr_inter_client_stat_Repository.save(client_inter);

            // Archiver
            sss_cdr_snapshot_arch_client_stat_service_impl.create_arch_client_stat(client_inter);

            logger.info("✓ Client " + client.getId_client() + " traité et archivé");
        } catch (Exception e) {
            logger.severe(" Erreur pour client " + client.getId_client() + " : " + e.getMessage());
            // Continuer le traitement des autres clients
        }
    }

    /**
     * Crée l'entité intermédiaire à partir du snapshot
     */
    private sss_cdr_inter_client_stat createIntermediateClient(
            sss_cdr_snapshot_client_stat client, int idLot) {

        sss_cdr_inter_client_stat client_inter = new sss_cdr_inter_client_stat();

        // Infos de base
        client_inter.setId_client(client.getId_client());
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

        // Adresses
            Adresse_snap a = client.getAdresse();
            Adresse_interm a_inter = createIntermediateAdresse(a, client_inter);
            client_inter.setAdresse(a_inter);


        // DonneesIntPM
            DonneesIntPM_snap d_pm = client.getDonneesInt_pm();
            DonneesIntPM_interm ai = createIntermediateDonneesIntPM(d_pm, client_inter);
            client_inter.setDonneesInt_pm(ai);


        // DonneesIntPP
            DonneesIntPP_snap d_pp = client.getDonneesInt_pp();
            DonneesIntPP_interm aii = createIntermediateDonneesIntPP(d_pp, client_inter);
            client_inter.setDonneesInt_pp(aii);


        // Actionnariats
        for (sss_cdr_snapshot_client_act act : client.getActionnariats()) {
            sss_cdr_inter_client_act f = createIntermediateActionnariat(act, client_inter);
            client_inter.getActionnariats().add(f);
        }

        // Bénéficiaires
        for (sss_cdr_snapshot_client_benef b : client.getBenEffects()) {
            sss_cdr_inter_client_benef g = createIntermediateBeneficiaire(b, client_inter);
            client_inter.getBenEffects().add(g);
        }

        return client_inter;
    }

    /**
     * Crée une adresse intermédiaire
     */
    private Adresse_interm createIntermediateAdresse(Adresse_snap a, sss_cdr_inter_client_stat client) {
        Adresse_interm a_inter = new Adresse_interm();
        a_inter.setAdresse(a.getAdresse());
        a_inter.setCodPostal(a.getCodPostal());
        a_inter.setCodLocal(a.getCodLocal());
        a_inter.setCodPays(a.getCodPays());
        a_inter.setNumTeleph(a.getNumTeleph());
        return a_inter;
    }

    /**
     * Crée une DonneesIntPM intermédiaire
     */
    private DonneesIntPM_interm createIntermediateDonneesIntPM(DonneesIntPM_snap a, sss_cdr_inter_client_stat client) {
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
        return ai;
    }

    /**
     * Crée une DonneesIntPP intermédiaire
     */
    private DonneesIntPP_interm createIntermediateDonneesIntPP(DonneesIntPP_snap a, sss_cdr_inter_client_stat client) {
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
        return ai;
    }

    /**
     * Crée un actionnariat intermédiaire
     */
    private sss_cdr_inter_client_act createIntermediateActionnariat(
            sss_cdr_snapshot_client_act a, sss_cdr_inter_client_stat client) {
        sss_cdr_inter_client_act ai = new sss_cdr_inter_client_act();
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
        ai.setClient(client);
        return ai;
    }

    /**
     * Crée un bénéficiaire intermédiaire
     */
    private sss_cdr_inter_client_benef createIntermediateBeneficiaire(
            sss_cdr_snapshot_client_benef a, sss_cdr_inter_client_stat client) {
        sss_cdr_inter_client_benef ai = new sss_cdr_inter_client_benef();
        ai.setTypIdBenEffect(a.getTypIdBenEffect());
        ai.setIdBenEffect(a.getIdBenEffect());
        ai.setNomBenEffect(a.getNomBenEffect());
        ai.setPreBenEffect(a.getPreBenEffect());
        ai.setNatBenEffect(a.getNatBenEffect());
        ai.setClient(client);
        return ai;
    }

    /**
     * Ancienne méthode (NON OPTIMISÉE) - conservée pour compatibilité
     */
    @Deprecated
    public void sss_cdr_inter_client_stat_create_OLD() {
        List<String> id_clients_modifies = comparaison.findModifiedAndNewIdsClients();
        int idLot = lotSequenceService_impl.getNextLotId();

        for (String s : id_clients_modifies) {
            System.out.println("id_clients_modifies" + s);
        }

        List<sss_cdr_snapshot_client_stat> clients_snapshot = new ArrayList<>();
        for (String id : id_clients_modifies) {
            clients_snapshot.add(sss_cdr_snapshot_client_stat_Repository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("client introuvable")));
        }

        for (sss_cdr_snapshot_client_stat client : clients_snapshot) {
            processSingleClient(client, idLot);
        }
    }
}