package ma.vivalis.BKAM_CDR_API1.client_per.repository;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_arch_client_per;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface sss_cdr_arch_client_per_repository extends JpaRepository<sss_cdr_arch_client_per, String> {

    @Query("SELECT DISTINCT a FROM sss_cdr_arch_client_per a " //+
           // "WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_arch_client_per c2) "
    )
    List<sss_cdr_arch_client_per> findAllWithRelations();



    @Query("SELECT c FROM sss_cdr_arch_client_per c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.codClient = :codClient")
    Optional<sss_cdr_arch_client_per> findById_lotAndDateExtractionAndId_client(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("codClient") String codClient
    );

}
