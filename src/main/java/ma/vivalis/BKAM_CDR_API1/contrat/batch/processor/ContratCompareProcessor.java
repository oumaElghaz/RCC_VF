package ma.vivalis.BKAM_CDR_API1.contrat.batch.processor;

import jakarta.annotation.PostConstruct;
import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_arch_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.*;
import ma.vivalis.BKAM_CDR_API1.contrat.repository.sss_cdr_arch_contrat_stat_repository;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListCliContrat;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListConsort;
import ma.vivalis.BKAM_CDR_API1.entities.util.ListLinkContrat;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ContratCompareProcessor implements ItemProcessor<sss_cdr_snapshot_contrat_stat, sss_cdr_inter_contrat_stat> {
    private final sss_cdr_arch_contrat_stat_repository sss_cdr_arch_contrat_stat_repository;
    private final LotSequenceRepository lotSequenceRepository;
    // Cache : charger TOUS les archivés en mémoire une seule fois
    private Map<String, sss_cdr_arch_contrat_stat> archivCache;
    private static final Logger log = LoggerFactory.getLogger(ContratCompareProcessor.class);
    private int lot_id;
    private String natMod;
    private boolean initialized = false;

    public ContratCompareProcessor(sss_cdr_arch_contrat_stat_repository sssCdrArchContratStatRepository, LotSequenceRepository lotSequenceRepository) {
        sss_cdr_arch_contrat_stat_repository = sssCdrArchContratStatRepository;
        this.lotSequenceRepository = lotSequenceRepository;
    }
   /* @PostConstruct
    public void loadCache() {
        archivCache = new HashMap<>();
        //lot_id = getNextLotId(); // Initialiser le lot_id pour ce batch

        // ✅ findAllWithRelations() au lieu de findAll()
        sss_cdr_arch_contrat_stat_repository.findAllWithRelations().forEach(a ->
                archivCache.put(a.getIdCont(), a));

        log.info("✅ Cache archiv chargé (avec relations) : {} entrées",
                archivCache.size());
        //sss_cdr_arch_client_stat_Repository.findAll().forEach(a -> archivCache.put(a.getId_client(), a));
    }*/

/*    public void resetForNewRun() {
        initialized = false;
        archivCache.clear();

        // ✅ Utiliser findAllWithRelations() ici aussi
        sss_cdr_arch_contrat_stat_repository.findAllWithRelations().forEach(a ->
                archivCache.put(a.getIdCont(), a));

        log.info("🔄 Reset — cache rechargé : {} entrées", archivCache.size());
    }*/


    @Override
    public @Nullable sss_cdr_inter_contrat_stat process(sss_cdr_snapshot_contrat_stat item) throws Exception {
       /* // ✅ Initialiser le lot au premier appel
        initLotIfNeeded();
        sss_cdr_arch_contrat_stat archiv = archivCache.get(item.getIdCont());

        if (archiv == null) {
            // NOUVEAU CONTRAT→ à insérer
            return buildIntermediaire(item, ActionType.EI,lot_id);
        }

        if (hasChanged(item, archiv)) {
            //  CONTRAT MODIFIÉ → à mettre à jour
            return buildIntermediaire(item, ActionType.EU,lot_id);
        }
*/
        //  INCHANGÉ → null = filtré, pas inséré dans intermédiaire
        return null;
    }

    private boolean hasChanged(sss_cdr_snapshot_contrat_stat snapshot,sss_cdr_arch_contrat_stat archiv) {
        if (equalsNullSafe(snapshot.getIdCont(), archiv.getIdCont())) {


                    if (!equalsNullSafe(snapshot.getGuichetAgence(), archiv.getGuichetAgence())) return true;
                    if (!equalsNullSafe(snapshot.getCodLocAgence(), archiv.getCodLocAgence())) return true;
                    if (!equalsNullSafe(snapshot.getTpCont(), archiv.getTpCont())) return true;
                    if (!equalsNullSafe(snapshot.getTpCred(), archiv.getTpCred())) return true;
                    if (!equalsNullSafe(snapshot.getDtlTpCred(), archiv.getDtlTpCred())) return true;
                    if (!equalsNullSafe(snapshot.getCreCoFin(), archiv.getCreCoFin())) return true;
                    if (!equalsNullSafe(snapshot.getCreConsor(), archiv.getCreConsor())) return true;
                    if (!equalsNullSafe(snapshot.getObjCred(), archiv.getObjCred())) return true;
                    if (!equalsNullSafe(snapshot.getObjCredDetail(), archiv.getObjCredDetail())) return true;
                    if (!equalsNullSafe(snapshot.getMonnaie(), archiv.getMonnaie())) return true;
                    if (!equalsNullSafe(snapshot.getMontIniAccord(), archiv.getMontIniAccord())) return true;
                    if (!equalsNullSafe(snapshot.getMontCreCoFin(), archiv.getMontCreCoFin())) return true;
                    if (!equalsNullSafe(snapshot.getTxChange(), archiv.getTxChange())) return true;
                    if (!equalsNullSafe(snapshot.getDtContCredt(), archiv.getDtContCredt())) return true;
                    if (!equalsNullSafe(snapshot.getDtDebloCred(), archiv.getDtDebloCred())) return true;
                    if (!equalsNullSafe(snapshot.getDtClotIni(), archiv.getDtClotIni())) return true;
                    if (!equalsNullSafe(snapshot.getDtClotCred(), archiv.getDtClotCred())) return true;
                    if (!equalsNullSafe(snapshot.getMotClotCont(), archiv.getMotClotCont())) return true;
                    if (!equalsNullSafe(snapshot.getFlagDiff(), archiv.getFlagDiff())) return true;
                    if (!equalsNullSafe(snapshot.getDtModCondCred(), archiv.getDtModCondCred())) return true;
                    if (!equalsNullSafe(snapshot.getMotModCondCred(), archiv.getMotModCondCred())) return true;
                    if (!equalsNullSafe(snapshot.getDtDebPerGraCap(), archiv.getDtDebPerGraCap())) return true;
                    if (!equalsNullSafe(snapshot.getDtFinPerGraCap(), archiv.getDtFinPerGraCap())) return true;
                    if (!equalsNullSafe(snapshot.getModPaiement(), archiv.getModPaiement())) return true;
                    if (!equalsNullSafe(snapshot.getTpEche(), archiv.getTpEche())) return true;
                    if (!equalsNullSafe(snapshot.getFxEche(), archiv.getFxEche())) return true;
                    if (!equalsNullSafe(snapshot.getNombreTotEche(), archiv.getNombreTotEche())) return true;
                    if (!equalsNullSafe(snapshot.getPeriodEche(), archiv.getPeriodEche())) return true;
                    if (!equalsNullSafe(snapshot.getMtEche(), archiv.getMtEche())) return true;
                    if (!equalsNullSafe(snapshot.getDt1Eche(), archiv.getDt1Eche())) return true;
                    if (!equalsNullSafe(snapshot.getMont1Eche(), archiv.getMont1Eche())) return true;
                    if (!equalsNullSafe(snapshot.getMont1EcheDiv(), archiv.getMont1EcheDiv())) return true;
                    if (!equalsNullSafe(snapshot.getFlagTxInt(), archiv.getFlagTxInt())) return true;
                    if (!equalsNullSafe(snapshot.getTxRef(), archiv.getTxRef())) return true;
                    if (!equalsNullSafe(snapshot.getTxAnnuelPourc(), archiv.getTxAnnuelPourc())) return true;
                    if (!equalsNullSafe(snapshot.getTxTAEG(), archiv.getTxTAEG())) return true;
                    if (!equalsNullSafe(snapshot.getHmRibh(), archiv.getHmRibh())) return true;
                    if (!equalsNullSafe(snapshot.getCmFxWkl(), archiv.getCmFxWkl())) return true;
                    if (!equalsNullSafe(snapshot.getFreqMiseJourTxInt(), archiv.getFreqMiseJourTxInt())) return true;
                    if (!equalsNullSafe(snapshot.getLTVIni(), archiv.getLTVIni())) return true;
                    if (!equalsNullSafe(snapshot.getTpSecuritization(), archiv.getTpSecuritization())) return true;
                    if (!equalsNullSafe(snapshot.getExisGarant(), archiv.getExisGarant())) return true;
                    if (!equalsNullSafe(snapshot.getMntGarant(), archiv.getMntGarant())) return true;


            //debut
            if (snapshot.getListCliContrat() != null && archiv.getListCliContrat() != null) {
                for (ListCliContrat cliCont : snapshot.getListCliContrat()) {
                    boolean found = false;
                    for (ListCliContrat_arch cliContArch : archiv.getListCliContrat()) {
                        if (equalsNullSafe(cliCont.getCodClient(), cliContArch.getCodClient())) {
                            found = true;
                            if (!equalsNullSafe(cliCont.getCapAutoriseEnt(), cliContArch.getCapAutoriseEnt()))
                                return true;
                            if (!equalsNullSafe(cliCont.getValProcVersEnt(), cliContArch.getValProcVersEnt()))
                                return true;

                            break;
                        }
                    }
                    if (!found) return true;
                }

            }//fin


            //debut
            if (snapshot.getListLinkContrat() != null && archiv.getListLinkContrat() != null) {
                for (ListLinkContrat linkCont : snapshot.getListLinkContrat()) {
                    boolean found = false;
                    for (ListLinkContrat_arch linkContArch : archiv.getListLinkContrat()) {
                        if (equalsNullSafe(linkCont.getIdContAss(), linkContArch.getIdContAss())) {
                            found = true;
                            if (!equalsNullSafe(linkCont.getTpConnex(), linkContArch.getTpConnex())) return true;


                            break;
                        }
                    }
                    if (!found) return true;
                }

            }//fin

            //debut
            if (snapshot.getListConsort() != null && archiv.getListConsort() != null) {
                for (ListConsort listConst : snapshot.getListConsort()) {
                    boolean found = false;
                    for (ListConsort_arch listConstArch : archiv.getListConsort()) {
                        if (equalsNullSafe(listConst.getIdEnt(), listConstArch.getIdEnt())) {
                            found = true;
                            if (!equalsNullSafe(listConst.getRelEntPart(), listConstArch.getRelEntPart())) return true;


                            break;
                        }
                    }
                    if (!found) return true;
                }

            }//fin


            return false;
        }
        return false;
    }
    private boolean equalsNullSafe(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }
    private sss_cdr_inter_contrat_stat buildIntermediaire(sss_cdr_snapshot_contrat_stat item, ActionType actionType,int lot_id){
        sss_cdr_inter_contrat_stat inter =sss_cdr_inter_contrat_stat.builder()
                .idCont(item.getIdCont())
                .id_lot(lot_id)
                .dateExtraction(item.getDtDeclaration())
                .entObserv(item.getEntObserv())
                .entDeclar(item.getEntDeclar())
                .idDest(item.getIdDest())

                . actionType(actionType)
                .dtRefCont(item.getDtRefCont())
                .guichetAgence(item.getGuichetAgence())
                .codLocAgence(item.getCodLocAgence())
                .tpCont(item.getTpCont())
                .tpCred(item.getTpCred())
                .dtlTpCred(item.getDtlTpCred())
                .creCoFin(item.getCreCoFin())
                .creConsor(item.getCreConsor())
                .objCred(item.getObjCred())
                .objCredDetail(item.getObjCredDetail())
                .monnaie(item.getMonnaie())
                .montIniAccord(item.getMontIniAccord())
                .montCreCoFin(item.getMontCreCoFin())
                .txChange(item.getTxChange())
                .dtContCredt(item.getDtContCredt())
                .dtDebloCred(item.getDtDebloCred())
                .dtClotIni(item.getDtClotIni())
                .dtClotCred(item.getDtClotCred())
                .motClotCont(item.getMotClotCont())
                .flagDiff(item.getFlagDiff())
                .dtModCondCred(item.getDtModCondCred())
                .motModCondCred(item.getMotModCondCred())
                .dtDebPerGraCap(item.getDtDebPerGraCap())
                .dtFinPerGraCap(item.getDtFinPerGraCap())
                .modPaiement(item.getModPaiement())
                .tpEche(item.getTpEche())
                .fxEche(item.getFxEche())
                .nombreTotEche(item.getNombreTotEche())
                .periodEche(item.getPeriodEche())
                .mtEche(item.getMtEche())
                .dt1Eche(item.getDt1Eche())
                .mont1Eche(item.getMont1Eche())
                .mont1EcheDiv(item.getMont1EcheDiv())
                .flagTxInt(item.getFlagTxInt())
                .txRef(item.getTxRef())
                .txAnnuelPourc(item.getTxAnnuelPourc())
                .txTAEG(item.getTxTAEG())
                .hmRibh(item.getHmRibh())
                .cmFxWkl(item.getCmFxWkl())
                .freqMiseJourTxInt(item.getFreqMiseJourTxInt())
                .LTVIni(item.getLTVIni())
                .tpSecuritization(item.getTpSecuritization())
                .exisGarant(item.getExisGarant())
                .mntGarant(item.getMntGarant())
                .build();



        // ── ListCliContrat ──
        if (item.getListCliContrat() != null && !item.getListCliContrat().isEmpty()) {
            inter.setListCliContrat(
                    item.getListCliContrat().stream().map(cliCon -> {
                        ListCliContrat_interm a = ListCliContrat_interm.builder()
                                .codClient(cliCon.getCodClient())
                                .capAutoriseEnt(cliCon.getCapAutoriseEnt())
                                .valProcVersEnt(cliCon.getValProcVersEnt())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toList()));  // ✅ toSet() au lieu de toList()
        }


        // ── ListLinkContrat ──
        if (item.getListLinkContrat() != null && !item.getListLinkContrat().isEmpty()) {
            inter.setListLinkContrat(
                    item.getListLinkContrat().stream().map(linkCon -> {
                        ListLinkContrat_interm a = ListLinkContrat_interm.builder()
                                .idContAss(linkCon.getIdContAss())
                                .tpConnex(linkCon.getTpConnex())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toList()));  // ✅ toSet() au lieu de toList()
        }


        // ── ListConsort ──
        if (item.getListConsort() != null && !item.getListConsort().isEmpty()) {
            inter.setListConsort(
                    item.getListConsort().stream().map(listConsrt -> {
                        ListConsort_interm a = ListConsort_interm.builder()
                                .idEnt(listConsrt.getIdEnt())
                                .relEntPart(listConsrt.getRelEntPart())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toList()));  // ✅ toSet() au lieu de toList()
        }


        //ListGarant
        if (item.getListGarant() != null && !item.getListGarant().isEmpty()) {
            inter.setListGarant(
                    item.getListGarant().stream().map(listGar -> {
                        ListGarant_interm a = ListGarant_interm.builder()
                                .idGar(listGar.getIdGar())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toList()));  // ✅ toSet() au lieu de toList()
        }


        return inter;
    }

    public synchronized int getNextLotId() {

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
    }
}
