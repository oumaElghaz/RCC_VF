package ma.vivalis.BKAM_CDR_API1.infoNeg.repository;

import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_infoNega_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_snapshot_infoNega_statRepository  extends JpaRepository<sss_cdr_snapshot_infoNega_stat, Long> {
}
