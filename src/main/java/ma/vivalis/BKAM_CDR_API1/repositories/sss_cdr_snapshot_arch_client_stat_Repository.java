package ma.vivalis.BKAM_CDR_API1.repositories;

import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_arch_client_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_snapshot_arch_client_stat_Repository extends JpaRepository<sss_cdr_snapshot_arch_client_stat, String> {
    @Query("SELECT c.id_lot FROM sss_cdr_snapshot_arch_client_stat c WHERE c.id_client = :id")
    String findIdLotById(@Param("id") String id);
}
