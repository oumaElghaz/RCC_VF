package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor;

import jakarta.annotation.PostConstruct;


import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_infoNega_stat;
import ma.vivalis.BKAM_CDR_API1.entities.util.ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.entities.util.InfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_inter_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.ComInfNeg_arch;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.ComInfNeg_interm;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.InfNeg_arch;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.InfNeg_interm;
import ma.vivalis.BKAM_CDR_API1.infoNeg.repository.sss_cdr_arch_infoNegaRepository;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InfoNegaCompareProcessor implements ItemProcessor<sss_cdr_snapshot_infoNega_stat, sss_cdr_inter_infoNegative> {
    private Map<Long, sss_cdr_arch_infoNegative> archivCache;
    private final sss_cdr_arch_infoNegaRepository sss_cdr_arch_infoNegaRepository;
    private static final Logger log = LoggerFactory.getLogger(InfoNegaCompareProcessor.class);
    private final LotSequenceRepository lotSequenceRepository;

    private int lot_id;
    private boolean initialized = false;

    public InfoNegaCompareProcessor(sss_cdr_arch_infoNegaRepository sssCdrArchInfoNegaRepository, LotSequenceRepository lotSequenceRepository) {
        sss_cdr_arch_infoNegaRepository = sssCdrArchInfoNegaRepository;
        this.lotSequenceRepository = lotSequenceRepository;
    }

    @Transactional
    public void loadCache() {
        archivCache = new HashMap<>();
        List<sss_cdr_arch_infoNegative> results = sss_cdr_arch_infoNegaRepository.findAllWithRelations();
        for (sss_cdr_arch_infoNegative a : results) {
            if (a.getComInfNegs() != null) {
                for (ComInfNeg_arch com : a.getComInfNegs()) {
                    if (com.getInfNegList() != null) {
                        com.getInfNegList().size();  // force le chargement
                    }
                }
            }
            archivCache.put(a.getId(), a);
        }
        log.info("Cache archiv chargé (avec relations) : {} entrées", archivCache.size());
    }

    @Override
    public @Nullable sss_cdr_inter_infoNegative process(sss_cdr_snapshot_infoNega_stat item) throws Exception {
        // ✅ Initialiser le lot au premier appel
        initLotIfNeeded();

                sss_cdr_arch_infoNegative archiv = archivCache.get(item.getId());
                if (archiv == null) {
                    // NOUVEAU info → à insérer
                    return buildIntermediaire(item, ActionType.EI,lot_id);
                }

                if (hasChanged(item, archiv)) {
                    //  info MODIFIÉ → à mettre à jour
                    return buildIntermediaire(item, ActionType.EU,lot_id);
                }

                //  INCHANGÉ → null = filtré, pas inséré dans intermédiaire
                return null;


    }

    private boolean hasChanged(sss_cdr_snapshot_infoNega_stat item, sss_cdr_arch_infoNegative archiv) {



        if (item.getComInfNegs() != null && archiv.getComInfNegs() != null) {
            for (ComInfNeg comInf: item.getComInfNegs()) {
                boolean found = false;
                for(ComInfNeg_arch ar: archiv.getComInfNegs()) {
                    for (InfNeg i : comInf.getInfNegList()) {
                        for (InfNeg_arch ii: ar.getInfNegList()) {


                            if (equalsNullSafe(i.getRefInfoNeg(), ii.getRefInfoNeg()) &&
                                    equalsNullSafe(i.getCodClient(), ii.getCodClient())) {
                                found = true;
                                if (!equalsNullSafe(i.getTpInfNegInc(), ii.getTpInfNegInc())) return true;
                                if (!equalsNullSafe(i.getDtObsInfNegInc(), ii.getDtObsInfNegInc())) return true;
                                if (!equalsNullSafe(i.getMontInc(), ii.getMontInc())) return true;
                                if (!equalsNullSafe(i.getStatInfoNeg(), ii.getStatInfoNeg())) return true;
                                if (!equalsNullSafe(i.getDtSortie(), ii.getDtSortie())) return true;
                                break;
                            }


                        }
                    }

                }





                if (!found) return true;
            }

        }


        return false;
    }

    private sss_cdr_inter_infoNegative buildIntermediaire(sss_cdr_snapshot_infoNega_stat snap, ActionType actionType, int lot_id){
        log.info("🔢 snap = {}", snap.toString());
        LocalDateTime dateDeclaration = LocalDateTime.now();
     sss_cdr_inter_infoNegative interi =sss_cdr_inter_infoNegative.builder()
             .id(snap.getId())
             .id_lot(lot_id)
             .dateExtraction(dateDeclaration)
             //.idDest(snap.getIdDest())
             //.entDeclar(snap.getEntDeclar())
             //.entObserv(snap.getEntObserv())

             .build();


     if (snap.getComInfNegs() != null) {
         List<ComInfNeg_interm> infInter = snap.getComInfNegs().stream()
                 .map(inf -> {
                     ComInfNeg_interm a = ComInfNeg_interm.builder()
                             .dtRef(inf.getDtRef())
                             .build();
                     if(inf.getInfNegList()!= null){
                         log.info("size liste des infods nega = {}",inf.getInfNegList().size());
                         List<InfNeg_interm> g=inf.getInfNegList().stream().map(r-> {InfNeg_interm e=InfNeg_interm.builder()
                                 .actionType(actionType)
                                 .codClient(r.getCodClient())
                                 .dtObsInfNegInc(r.getDtObsInfNegInc())
                                 .dtSortie(r.getDtSortie())
                                 .montInc(r.getMontInc())
                                 .refInfoNeg(r.getRefInfoNeg())
                                 .statInfoNeg(r.getStatInfoNeg())
                                 .tpInfNegInc(r.getTpInfNegInc())
                                 .build();
                             e.setComInf(a);;  // ← Lier au parent comInf
                             return e;

                         }).collect(Collectors.toList());
                         a.setInfNegList(g);
                     log.info("size de g est ={}",g.size());}

                     a.setInfoNeg(interi); // ← Lier au parent archiv
                     return a;
                 }).collect(Collectors.toList());
         interi.setComInfNegs(infInter);
     }





     return interi;
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
