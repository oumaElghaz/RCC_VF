package ma.vivalis.BKAM_CDR_API1.client_per.repository;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_client_per_repository extends JpaRepository<sss_cdr_client_per, String> {
}
