package ma.vivalis.BKAM_CDR_API1.garantie.repository;

import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_inter_garantie_repository extends JpaRepository<sss_cdr_inter_garantie, String> {
}
