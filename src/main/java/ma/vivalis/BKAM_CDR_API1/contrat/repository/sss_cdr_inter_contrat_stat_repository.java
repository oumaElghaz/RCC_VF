package ma.vivalis.BKAM_CDR_API1.contrat.repository;

import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_inter_contrat_stat_repository extends JpaRepository<sss_cdr_inter_contrat_stat, String> {
}
