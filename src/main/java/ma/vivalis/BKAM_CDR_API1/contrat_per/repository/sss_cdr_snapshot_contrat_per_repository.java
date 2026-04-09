package ma.vivalis.BKAM_CDR_API1.contrat_per.repository;

import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_contrat_per;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_snapshot_contrat_per_repository extends JpaRepository<sss_cdr_snapshot_contrat_per, String> {
}
