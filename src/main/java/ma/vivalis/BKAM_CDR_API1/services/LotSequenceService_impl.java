package ma.vivalis.BKAM_CDR_API1.services;

import jakarta.transaction.Transactional;
import ma.vivalis.BKAM_CDR_API1.entities.util.LotSequence;
import ma.vivalis.BKAM_CDR_API1.repositories.util.LotSequenceRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LotSequenceService_impl {
    private final LotSequenceRepository lotSequenceRepository;

    public LotSequenceService_impl(LotSequenceRepository lotSequenceRepository) {
        this.lotSequenceRepository = lotSequenceRepository;
    }
    public Integer getMax(){
        return lotSequenceRepository.findMaxVal();
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
}
