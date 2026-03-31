package ma.vivalis.BKAM_CDR_API1.common.service;

import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class lotSequenceService {
    private final LotSequenceRepository lotSequenceRepository;
    private boolean initialized = false;
    private int lot_id;
    private static final Logger log = LoggerFactory.getLogger(lotSequenceService.class);

    public lotSequenceService(LotSequenceRepository lotSequenceRepository) {
        this.lotSequenceRepository = lotSequenceRepository;
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

    public synchronized int initLotIfNeeded() {
        if (!initialized) {
            lot_id = getNextLotId();
            initialized = true;
            log.info("🔢 Lot ID initialisé = {}", lot_id);
        }
        return lot_id;
    }

}
