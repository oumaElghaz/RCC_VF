package ma.vivalis.BKAM_CDR_API1.client.repository;

import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface sss_cdr_client_stat_Repository extends JpaRepository<sss_cdr_client_stat, String> {
    //Optional<sss_cdr_client_stat> findById_lotAndDateExtractionAndId_client(Integer id_lot, Date dateExtraction, String id_client);
    @Query("SELECT c FROM sss_cdr_client_stat c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.id_client = :idClient")
    Optional<sss_cdr_client_stat> findById_lotAndDateExtractionAndId_client(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("idClient") String idClient
    );


}
