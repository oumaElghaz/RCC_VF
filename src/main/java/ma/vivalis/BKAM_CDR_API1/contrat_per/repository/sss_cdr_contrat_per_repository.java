package ma.vivalis.BKAM_CDR_API1.contrat_per.repository;

import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_contrat_per;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface sss_cdr_contrat_per_repository extends JpaRepository<sss_cdr_contrat_per, String> {
    @Query("SELECT c FROM sss_cdr_contrat_per c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.idCont = :idCont")
    Optional<sss_cdr_contrat_per> findById_lotAndDateExtractionAndIdCont(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("idCont") String idCont
    );
}
