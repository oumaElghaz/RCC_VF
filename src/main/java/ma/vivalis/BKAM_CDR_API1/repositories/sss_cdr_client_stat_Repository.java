package ma.vivalis.BKAM_CDR_API1.repositories;

import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_client_stat_Repository extends JpaRepository<sss_cdr_client_stat, String> {
}
