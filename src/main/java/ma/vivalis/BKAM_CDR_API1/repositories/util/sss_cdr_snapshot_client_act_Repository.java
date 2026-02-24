package ma.vivalis.BKAM_CDR_API1.repositories.util;

import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_snapshot_client_act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_snapshot_client_act_Repository extends JpaRepository<sss_cdr_snapshot_client_act, Long> {
}
