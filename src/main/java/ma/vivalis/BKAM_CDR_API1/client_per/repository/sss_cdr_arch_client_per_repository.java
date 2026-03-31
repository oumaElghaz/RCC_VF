package ma.vivalis.BKAM_CDR_API1.client_per.repository;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_arch_client_per;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sss_cdr_arch_client_per_repository extends JpaRepository<sss_cdr_arch_client_per, String> {

    @Query("SELECT DISTINCT a FROM sss_cdr_arch_client_per a " +
            "WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_arch_client_per c2) "
    )
    List<sss_cdr_arch_client_per> findAllWithRelations();

}
