package ma.vivalis.BKAM_CDR_API1.repositories.util;

import ma.vivalis.BKAM_CDR_API1.entities.util.LotSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LotSequenceRepository  extends JpaRepository<LotSequence, Integer> {
    @Query(value = "SELECT max(val) FROM lot_sequence", nativeQuery = true)
    public Integer findMaxVal();
}
