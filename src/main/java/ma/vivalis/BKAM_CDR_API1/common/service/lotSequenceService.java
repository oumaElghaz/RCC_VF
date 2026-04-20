package ma.vivalis.BKAM_CDR_API1.common.service;

import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

@Service
public class lotSequenceService {

    private static final Logger log = LoggerFactory.getLogger(lotSequenceService.class);

    private final LotSequenceRepository lotSequenceRepository;

    // ✅ Numéro de lot fixé pour toute la durée du lancement (génération)
    private int currentLotId;

    public lotSequenceService(LotSequenceRepository lotSequenceRepository) {
        this.lotSequenceRepository = lotSequenceRepository;
    }

    /**
     * Appelé automatiquement au démarrage de l'application.
     * Génère UN SEUL numéro de lot pour toute la génération (les 4 extractions).
     */
    @PostConstruct
    @Transactional
    public synchronized void initLotId() {

        LotSequence seq = lotSequenceRepository.findById(1)
                .orElseGet(() -> {
                    LotSequence s = new LotSequence();
                    s.setVal(0);
                    return lotSequenceRepository.save(s);
                });

        int current = seq.getVal();
        int next = (current >= 999999) ? 0 : current + 1;

        seq.setVal(next);
        lotSequenceRepository.save(seq);

        // ✅ Stocké en mémoire pour toute la génération
        this.currentLotId = next;

        log.info("✅ Lot ID initialisé pour cette génération : {}", this.currentLotId);
    }


    public int retournerCurrentLotId() {
        return this.currentLotId;
    }
}
