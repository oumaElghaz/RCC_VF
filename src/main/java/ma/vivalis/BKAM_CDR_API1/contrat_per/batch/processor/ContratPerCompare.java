package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor;

import jakarta.annotation.PostConstruct;
import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_arch_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.repository.sss_cdr_arch_contrat_per_repository;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_per;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContratPerCompare implements ItemProcessor<sss_cdr_snapshot_contrat_per, sss_cdr_inter_contrat_per> {
    private final sss_cdr_arch_contrat_per_repository sss_cdr_arch_contrat_per_repository;
    private final LotSequenceRepository lotSequenceRepository;
    // Cache : charger TOUS les archivés en mémoire une seule fois
    private Map<String, sss_cdr_arch_contrat_per> archivCache;
    private static final Logger log = LoggerFactory.getLogger(ContratPerCompare.class);
    private int lot_id;

    private boolean initialized = false;

    public ContratPerCompare(sss_cdr_arch_contrat_per_repository sssCdrArchContratPerRepository, LotSequenceRepository lotSequenceRepository) {
        sss_cdr_arch_contrat_per_repository = sssCdrArchContratPerRepository;
        this.lotSequenceRepository = lotSequenceRepository;
    }


    @PostConstruct
    public void loadCache() {
        archivCache = new HashMap<>();
        sss_cdr_arch_contrat_per_repository.findAllWithRelations().forEach(a ->
                archivCache.put(a.getIdCont(), a));
    }
    @Override
    public @Nullable sss_cdr_inter_contrat_per process(sss_cdr_snapshot_contrat_per item) throws Exception {
        // ✅ Initialiser le lot au premier appel
        initLotIfNeeded();
        sss_cdr_arch_contrat_per archiv = archivCache.get(item.getIdCont());

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

    private boolean hasChanged(sss_cdr_snapshot_contrat_per snapshot, sss_cdr_arch_contrat_per archiv) {
        if(equalsNullSafe(snapshot.getIdCont(), archiv.getIdCont())){
            if(!equalsNullSafe(snapshot.getMontDu               (),archiv.getMontDu               ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontUtilCred         (),archiv.getMontUtilCred         ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontUtilCredDev      (),archiv.getMontUtilCredDev      ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontRest             (),archiv.getMontRest             ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontRestDev          (),archiv.getMontRestDev          ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontComAg            (),archiv.getMontComAg            ()  )) return true;
            if(!equalsNullSafe(snapshot.getCommSpecif           (),archiv.getCommSpecif           ()  )) return true;
            if(!equalsNullSafe(snapshot.getInterCourMrg         (),archiv.getInterCourMrg         ()  )) return true;
            if(!equalsNullSafe(snapshot.getCaFactor             (),archiv.getCaFactor             ()  )) return true;
            if(!equalsNullSafe(snapshot.getTpReembAntc          (),archiv.getTpReembAntc          ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontReembAntc        (),archiv.getMontReembAntc        ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtProcRevTxInt       (),archiv.getDtProcRevTxInt       ()  )) return true;
            if(!equalsNullSafe(snapshot.getColRefin             (),archiv.getColRefin             ()  )) return true;
            if(!equalsNullSafe(snapshot.getNbEcheRest           (),archiv.getNbEcheRest           ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtProcEche           (),archiv.getDtProcEche           ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtDernEchePay        (),archiv.getDtDernEchePay        ()  )) return true;
            if(!equalsNullSafe(snapshot.getNbEcheImp            (),archiv.getNbEcheImp            ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontEcheImp          (),archiv.getMontEcheImp          ()  )) return true;
            if(!equalsNullSafe(snapshot.getStPaiement           (),archiv.getStPaiement           ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtStPaiement         (),archiv.getDtStPaiement         ()  )) return true;
            if(!equalsNullSafe(snapshot.getClassCreanceSouff    (),archiv.getClassCreanceSouff    ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtClassCreanceSouff  (),archiv.getDtClassCreanceSouff  ()  )) return true;
            if(!equalsNullSafe(snapshot.getContentieux          (),archiv.getContentieux          ()  )) return true;
            if(!equalsNullSafe(snapshot.getCreanceProv          (),archiv.getCreanceProv          ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontProv             (),archiv.getMontProv             ()  )) return true;
            if(!equalsNullSafe(snapshot.getTxProvCont           (),archiv.getTxProvCont           ()  )) return true;
            if(!equalsNullSafe(snapshot.getCodClient            (),archiv.getCodClient            ()  )) return true;
            if(!equalsNullSafe(snapshot.getMontEncCli           (),archiv.getMontEncCli           ()  )) return true;
            if(!equalsNullSafe(snapshot.getLGDCont              (),archiv.getLGDCont              ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtLGD                (),archiv.getDtLGD                ()  )) return true;
            if(!equalsNullSafe(snapshot.getEADCont              (),archiv.getEADCont              ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtEAD                (),archiv.getDtEAD                ()  )) return true;
            if(!equalsNullSafe(snapshot.getECLCont              (),archiv.getECLCont              ()  )) return true;
            if(!equalsNullSafe(snapshot.getDtECL                (),archiv.getDtECL                ()  )) return true;

    }



    return false;
    }

    private @Nullable sss_cdr_inter_contrat_per buildIntermediaire(sss_cdr_snapshot_contrat_per item, ActionType actionType, int lotId) {
        LocalDateTime dateDeclaration = LocalDateTime.now();
        sss_cdr_inter_contrat_per inter=sss_cdr_inter_contrat_per.builder()
                .idCont(item.getIdCont())
                .id_lot(lotId)
                .dateExtraction(dateDeclaration)
                .montDu(item.getMontDu())
                .montUtilCred(item.getMontUtilCred())
                .montUtilCredDev(item.getMontUtilCredDev())
                .actionType(actionType)
                .dateRef(item.getDateRef())
                .montRest(item.getMontRest())
                .montRestDev(item.getMontRestDev())
                .montComAg(item.getMontComAg())
                .commSpecif(item.getCommSpecif())
                .interCourMrg(item.getInterCourMrg())
                .caFactor(item.getCaFactor())
                .tpReembAntc(item.getTpReembAntc())
                .montReembAntc(item.getMontReembAntc())
                .dtProcRevTxInt(item.getDtProcRevTxInt())
                .colRefin(item.getColRefin())
                .nbEcheRest(item.getNbEcheRest())
                .dtProcEche(item.getDtProcEche())
                .dtDernEchePay(item.getDtDernEchePay())
                .nbEcheImp(item.getNbEcheImp())
                .montEcheImp(item.getMontEcheImp())
                .stPaiement(item.getStPaiement())
                .dtStPaiement(item.getDtStPaiement())
                .classCreanceSouff(item.getClassCreanceSouff())
                .dtClassCreanceSouff(item.getDtClassCreanceSouff())
                .contentieux(item.getContentieux())
                .creanceProv(item.getCreanceProv())
                .montProv(item.getMontProv())
                .txProvCont(item.getTxProvCont())
                .codClient(item.getCodClient())
                .montEncCli(item.getMontEncCli())
                .LGDCont(item.getLGDCont())
                .dtLGD(item.getDtLGD())
                .EADCont(item.getEADCont())
                .dtEAD(item.getDtEAD())
                .ECLCont(item.getECLCont())
                .dtECL(item.getDtECL())




                .build();
        
        return inter;
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
