package ma.vivalis.BKAM_CDR_API1.client_per.repository;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface sss_cdr_client_per_repository extends JpaRepository<sss_cdr_client_per, String> {
    @Query("SELECT c FROM sss_cdr_client_per c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.codClient = :codClient")
    Optional<sss_cdr_client_per> findById_lotAndDateExtractionAndId_client(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("codClient") String codClient
    );
}
