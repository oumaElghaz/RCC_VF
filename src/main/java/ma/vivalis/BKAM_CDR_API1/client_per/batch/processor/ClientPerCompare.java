package ma.vivalis.BKAM_CDR_API1.client_per.batch.processor;

import jakarta.annotation.PostConstruct;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_arch_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.repository.sss_cdr_arch_client_per_repository;
import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.common.service.lotSequenceService;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_per;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class ClientPerCompare implements ItemProcessor<sss_cdr_snapshot_client_per, sss_cdr_inter_client_per> {
    private final sss_cdr_arch_client_per_repository sss_cdr_arch_client_per_repository;
    //private final LotSequenceRepository lotSequenceRepository;
    private final lotSequenceService lotSequenceService;
    // Cache : charger TOUS les archivés en mémoire une seule fois
    private Map<String, List<sss_cdr_arch_client_per>> archivCache;
    private static final Logger log = LoggerFactory.getLogger(ClientPerCompare.class);
    private int lot_id;

    private boolean initialized = false;

    public ClientPerCompare(sss_cdr_arch_client_per_repository sssCdrArchClientPerRepository, lotSequenceService lotSequenceService) {
        sss_cdr_arch_client_per_repository = sssCdrArchClientPerRepository;

        this.lotSequenceService = lotSequenceService;
    }
    @PostConstruct
    public void loadCache() {
        archivCache = new HashMap<>();
        //sss_cdr_arch_client_per_repository.findAllWithRelations().forEach(a ->
                //archivCache.put(a.getCodClient(), a));

        sss_cdr_arch_client_per_repository.findAllWithRelations().forEach(a ->
                archivCache.computeIfAbsent(a.getCodClient(), k -> new ArrayList<>()).add(a));
    }


    @Override
    public @Nullable sss_cdr_inter_client_per process(sss_cdr_snapshot_client_per item) throws Exception {
        // ✅ Initialiser le lot au premier appel
        //initLotIfNeeded();
        lot_id = lotSequenceService.retournerCurrentLotId();
        log.info("Lot ID utilisé client per pour ce run : {}", lot_id);
        List<sss_cdr_arch_client_per> archivList=new ArrayList<>();
                archivList = archivCache.get(item.getCodClient());
        if (archivList == null) {
            archivList = Collections.emptyList();
        }

        // Trouver directement l'objet avec le max id_lot
        sss_cdr_arch_client_per archiv=new sss_cdr_arch_client_per();
                archiv = archivList.stream()
                .max(Comparator.comparingInt(sss_cdr_arch_client_per::getId_lot))
                .orElse(null);


        if (archiv == null) {
            // NOUVEAU CLIENT → à insérer
            return buildIntermediaire(item, ActionType.EI,lot_id);
        }

        if (hasChanged(item, archiv)) {
            //  CLIENT MODIFIÉ → à mettre à jour
            return buildIntermediaire(item, ActionType.EU,lot_id);
        }

        //  INCHANGÉ → null = filtré, pas inséré dans intermédiaire
        return null;

    }

private sss_cdr_inter_client_per buildIntermediaire(sss_cdr_snapshot_client_per snap,ActionType actionType, int lot_id){
    LocalDateTime dateDeclaration = LocalDateTime.now();
        sss_cdr_inter_client_per inter=sss_cdr_inter_client_per.builder()
            .codClient(snap.getCodClient())
            .id_lot(lot_id)
            .dateExtraction(dateDeclaration)
            //.entObserv (snap.getEntObserv())
            //.entDeclar (snap.getEntDeclar())
            //.dtCreation (snap.getDtCreation())
            //.idDest(snap.getIdDest())
            .dtRef(snap.getDtRef())
            .actionType(actionType)
            .watchList (snap.getWatchList())
            .etatAvProcJud(snap.getEtatAvProcJud())
            .dtEtatAvProcJud(snap.getDtEtatAvProcJud())
            .revenu (snap.getRevenu())
            .dtRevenu(snap.getDtRevenu())
            .annExercCompt(snap.getAnnExercCompt())
            .capSocial(snap.getCapSocial())
            .capPropres(snap.getCapPropres())
            .actImmobilises(snap.getActImmobilises())
            .totBilan(snap.getTotBilan())
            .chiffreAffaire(snap.getChiffreAffaire())
            .dtAffairesAnExp(snap.getDtAffairesAnExp())
            .detteBancLMT(snap.getDetteBancLMT())
            .detteBancCT (snap.getDetteBancCT())
            .passifCirculant(snap.getPassifCirculant())
            .dettesFourn (snap.getDettesFourn())
            .compteCourAssoc(snap.getCompteCourAssoc())
            .tresoreriePassif(snap.getTresoreriePassif())
            .actifCirculant(snap.getActifCirculant())
            .créancesClients(snap.getCréancesClients())
            .tresorerieActif(snap.getTresorerieActif())
            .caisse (snap.getCaisse())
            .achatsRevendus(snap.getAchatsRevendus())
            .achatsConsom(snap.getAchatsConsom())
            .chargesExternes(snap.getChargesExternes())
            .chargesInterets(snap.getChargesInterets())
            .resultatNet(snap.getResultatNet())
            .tpResultat(snap.getTpResultat())
            .PDCont (snap.getPDCont())
            .dtEvalRisques(snap.getDtEvalRisques())
            .modIRBCont(snap.getModIRBCont())
            .coteCli (snap.getCoteCli())
            .dateCoteCli(snap.getDateCoteCli())
            .modCoteCli (snap.getModCoteCli())
            .notAgence (snap.getNotAgence())
            .NomAgence (snap.getNomAgence())
            .dtnotAgc(snap.getDtnotAgc())

            .build();

    return inter;
}


private boolean hasChanged(sss_cdr_snapshot_client_per snapshot, sss_cdr_arch_client_per archiv){
        if(equalsNullSafe(snapshot.getCodClient(), archiv.getCodClient())){
            if (!equalsNullSafe(snapshot.getChiffreAffaire(), archiv.getChiffreAffaire())) return true;
            if (!equalsNullSafe(snapshot.getWatchList(), archiv.getWatchList())) return true;
            if (!equalsNullSafe(snapshot.getEtatAvProcJud(), archiv.getEtatAvProcJud())) return true;
            if (!equalsNullSafe(snapshot.getDtEtatAvProcJud(), archiv.getDtEtatAvProcJud())) return true;
            if (!equalsNullSafe(snapshot.getRevenu (), archiv.getRevenu())) return true;
            if (!equalsNullSafe(snapshot.getDtRevenu(), archiv.getDtRevenu())) return true;
            if (!equalsNullSafe(snapshot.getAnnExercCompt(), archiv.getAnnExercCompt())) return true;
            if (!equalsNullSafe(snapshot.getCapSocial(), archiv.getCapSocial())) return true;
            if (!equalsNullSafe(snapshot.getCapPropres(), archiv.getCapPropres())) return true;
            if (!equalsNullSafe(snapshot.getActImmobilises(), archiv.getActImmobilises())) return true;
            if (!equalsNullSafe(snapshot.getTotBilan(), archiv.getTotBilan())) return true;

            if (!equalsNullSafe(snapshot.getDtAffairesAnExp(), archiv.getDtAffairesAnExp())) return true;
            if (!equalsNullSafe(snapshot.getDetteBancLMT(), archiv.getDetteBancLMT())) return true;
            if (!equalsNullSafe(snapshot.getDetteBancCT (), archiv.getDetteBancCT())) return true;
            if (!equalsNullSafe(snapshot.getPassifCirculant(), archiv.getPassifCirculant())) return true;
            if (!equalsNullSafe(snapshot.getDettesFourn (), archiv.getDettesFourn())) return true;
            if (!equalsNullSafe(snapshot.getCompteCourAssoc(), archiv.getCompteCourAssoc())) return true;
            if (!equalsNullSafe(snapshot.getTresoreriePassif(), archiv.getTresoreriePassif())) return true;
            if (!equalsNullSafe(snapshot.getActifCirculant(), archiv.getActifCirculant())) return true;
            if (!equalsNullSafe(snapshot.getCréancesClients(), archiv.getCréancesClients())) return true;
            if (!equalsNullSafe(snapshot.getTresorerieActif(), archiv.getTresorerieActif())) return true;
            if (!equalsNullSafe(snapshot.getCaisse (), archiv.getCaisse())) return true;
            if (!equalsNullSafe(snapshot.getAchatsRevendus(), archiv.getAchatsRevendus())) return true;
            if (!equalsNullSafe(snapshot.getAchatsConsom(), archiv.getAchatsConsom())) return true;
            if (!equalsNullSafe(snapshot.getChargesExternes(), archiv.getChargesExternes())) return true;
            if (!equalsNullSafe(snapshot.getChargesInterets(), archiv.getChargesInterets())) return true;
            if (!equalsNullSafe(snapshot.getResultatNet(), archiv.getResultatNet())) return true;
            if (!equalsNullSafe(snapshot.getTpResultat(), archiv.getTpResultat())) return true;
            if (!equalsNullSafe(snapshot.getPDCont (), archiv.getPDCont())) return true;
            if (!equalsNullSafe(snapshot.getDtEvalRisques(), archiv.getDtEvalRisques())) return true;
            if (!equalsNullSafe(snapshot.getModIRBCont(), archiv.getModIRBCont())) return true;
            if (!equalsNullSafe(snapshot.getCoteCli (), archiv.getCoteCli())) return true;
            if (!equalsNullSafe(snapshot.getDateCoteCli(), archiv.getDateCoteCli())) return true;
            if (!equalsNullSafe(snapshot.getModCoteCli (), archiv.getModCoteCli())) return true;
            if (!equalsNullSafe(snapshot.getNotAgence (), archiv.getNotAgence())) return true;
            if (!equalsNullSafe(snapshot.getNomAgence (), archiv.getNomAgence())) return true;
            if (!equalsNullSafe(snapshot.getDtnotAgc(), archiv.getDtnotAgc())) return true;

        }



    return false;
}

    private boolean equalsNullSafe(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        log.info("l objet a est {} ",a.toString());
        log.info("l objet b est {} ",b.toString());
        log.info("la comparaison est {} ",a.equals(b));
        return a.equals(b);
    }
/*    public synchronized int getNextLotId() {

        LotSequence seq = lotSequenceRepository.findById(1)
                .orElseGet(() -> {
                    LotSequence s = new LotSequence();
                    s.setVal(0);
                    return lotSequenceRepository.save(s);
                });

        int current = seq.getVal();

        int next;
        if (current >= 999999) {
            next = 0;
        } else {
            next = current + 1;
        }

        seq.setVal(next);
        lotSequenceRepository.save(seq);
        return next;
    }

    private synchronized void initLotIfNeeded() {
        if (!initialized) {
            lot_id = getNextLotId();
            initialized = true;
            log.info("🔢 Lot ID initialisé = {}", lot_id);
        }
    }*/
}
