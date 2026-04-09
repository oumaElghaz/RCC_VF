package ma.vivalis.BKAM_CDR_API1.garantie.batch.processor;

import jakarta.annotation.PostConstruct;

import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_arch_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.repository.sss_cdr_arch_garantie_repository;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class GarantieCompareProcessor implements ItemProcessor<sss_cdr_snapshot_garantie, sss_cdr_inter_garantie> {
    private final sss_cdr_arch_garantie_repository sss_cdr_arch_garantie_repository;
    private final LotSequenceRepository lotSequenceRepository;
    // Cache : charger TOUS les archivés en mémoire une seule fois
    private Map<String, sss_cdr_arch_garantie> archivCache;
    private static final Logger log = LoggerFactory.getLogger(GarantieCompareProcessor.class);
    private int lot_id;

    private boolean initialized = false;

    public GarantieCompareProcessor(sss_cdr_arch_garantie_repository sssCdrArchGarantieRepository, LotSequenceRepository lotSequenceRepository) {
        sss_cdr_arch_garantie_repository = sssCdrArchGarantieRepository;
        this.lotSequenceRepository = lotSequenceRepository;
    }
    @PostConstruct
    public void loadCache() {
        archivCache = new HashMap<>();
        sss_cdr_arch_garantie_repository.findAllWithRelations().forEach(a ->
                archivCache.put(a.getIdGar(), a));
    }
    @Override
    public @Nullable sss_cdr_inter_garantie process(sss_cdr_snapshot_garantie item) throws Exception {
        // ✅ Initialiser le lot au premier appel
        initLotIfNeeded();
        sss_cdr_arch_garantie archiv = archivCache.get(item.getIdGar());

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

    private @Nullable sss_cdr_inter_garantie buildIntermediaire(sss_cdr_snapshot_garantie item, ActionType actionType, int lotId) {
        LocalDateTime dateDeclaration = LocalDateTime.now();
        sss_cdr_inter_garantie gar = sss_cdr_inter_garantie.builder()

                .idGar(item.getIdGar())
                .id_lot(lotId)
                .dateExtraction(dateDeclaration)
                .actionType(actionType)
                .dtRefGar(item.getDtRefGar())
                .dtCreatGar(item.getDtCreatGar())
                .dtFinGar(item.getDtFinGar())
                .renGar(item.getRenGar())
                .dtRenGar(item.getDtRenGar())
                .dtFinRenGar(item.getDtFinRenGar())
                .tpGar(item.getTpGar())
                .codClient(item.getCodClient())
                .codGarExt(item.getCodGarExt())
                .tpRefExtGar(item.getTpRefExtGar())
                .refExtGar(item.getRefExtGar())
                .prixAcqProp(item.getPrixAcqProp())
                .codLocalGar (item.getCodLocalGar())
                .montGar(item.getMontGar())
                .valOriGar(item.getValOriGar())
                .tpValInGar(item.getTpValInGar())
                .valActGar(item.getValActGar())
                .dtEvalGar(item.getDtEvalGar())
                .tpValActGar(item.getTpValActGar())
                .garEtat(item.getGarEtat())
                .nvGarAdossCred(item.getNvGarAdossCred())
                .etatExecGar(item.getEtatExecGar())
                .dtExecGar(item.getDtExecGar())
            .build();

        return gar;
    }

    private boolean hasChanged(sss_cdr_snapshot_garantie snapshot, sss_cdr_arch_garantie archiv){
        if(equalsNullSafe(snapshot.getIdGar(), archiv.getIdGar())){
            if (!equalsNullSafe(snapshot.getDtRefGar()      ,archiv.getDtRefGar()      )) return true;
            if (!equalsNullSafe(snapshot.getDtCreatGar()    ,archiv.getDtCreatGar()    )) return true;
            if (!equalsNullSafe(snapshot.getDtFinGar()      ,archiv.getDtFinGar()      )) return true;
            if (!equalsNullSafe(snapshot.getRenGar()        ,archiv.getRenGar()        )) return true;
            if (!equalsNullSafe(snapshot.getDtRenGar()      ,archiv.getDtRenGar()      )) return true;
            if (!equalsNullSafe(snapshot.getDtFinRenGar()   ,archiv.getDtFinRenGar()   )) return true;
            if (!equalsNullSafe(snapshot.getTpGar()         ,archiv.getTpGar()         )) return true;
            if (!equalsNullSafe(snapshot.getCodClient()     ,archiv.getCodClient()     )) return true;
            if (!equalsNullSafe(snapshot.getCodGarExt()     ,archiv.getCodGarExt()     )) return true;
            if (!equalsNullSafe(snapshot.getTpRefExtGar()   ,archiv.getTpRefExtGar()   )) return true;
            if (!equalsNullSafe(snapshot.getRefExtGar()     ,archiv.getRefExtGar()     )) return true;
            if (!equalsNullSafe(snapshot.getPrixAcqProp()   ,archiv.getPrixAcqProp()   )) return true;
            if (!equalsNullSafe(snapshot.getCodLocalGar ()  ,archiv.getCodLocalGar ()  )) return true;
            if (!equalsNullSafe(snapshot.getMontGar()       ,archiv.getMontGar()       )) return true;
            if (!equalsNullSafe(snapshot.getValOriGar()     ,archiv.getValOriGar()     )) return true;
            if (!equalsNullSafe(snapshot.getTpValInGar()    ,archiv.getTpValInGar()    )) return true;
            if (!equalsNullSafe(snapshot.getValActGar()     ,archiv.getValActGar()     )) return true;
            if (!equalsNullSafe(snapshot.getDtEvalGar()     ,archiv.getDtEvalGar()     )) return true;
            if (!equalsNullSafe(snapshot.getTpValActGar()   ,archiv.getTpValActGar()   )) return true;
            if (!equalsNullSafe(snapshot.getGarEtat()       ,archiv.getGarEtat()       )) return true;
            if (!equalsNullSafe(snapshot.getNvGarAdossCred(),archiv.getNvGarAdossCred())) return true;
            if (!equalsNullSafe(snapshot.getEtatExecGar()   ,archiv.getEtatExecGar()   )) return true;
            if (!equalsNullSafe(snapshot.getDtExecGar()     ,archiv.getDtExecGar()     )) return true;

        }



        return false;
    }






    private boolean equalsNullSafe(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
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
