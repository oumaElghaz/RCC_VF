package ma.vivalis.BKAM_CDR_API1.garantie.repository;

import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_garantie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Repository
public interface sss_cdr_garantie_repository extends JpaRepository<sss_cdr_garantie, String> {
    @Query("SELECT c FROM sss_cdr_garantie c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.idGar = :idGar")
    Optional<sss_cdr_garantie> findById_lotAndDateExtractionAndId_gar(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("idGar") String idGar
    );
}
