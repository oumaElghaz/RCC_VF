package ma.vivalis.BKAM_CDR_API1.infoNeg.repository;


import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface sss_cdr_arch_infoNegaRepository  extends JpaRepository<sss_cdr_arch_infoNegative, Long> {

    @Query("SELECT DISTINCT a FROM sss_cdr_arch_infoNegative a " +
            "LEFT JOIN FETCH a.comInfNegs com " +
            "WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_arch_infoNegative c2) ")
    List<sss_cdr_arch_infoNegative> findAllWithRelations();

    //Optional<sss_cdr_arch_infoNegative> findById_lotAndDateExtractionAndId(Integer id_lot, Date dateExtraction, Long id);
    @Query("SELECT c FROM sss_cdr_arch_infoNegative c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.id = :id")
    Optional<sss_cdr_arch_infoNegative> findById_lotAndDateExtractionAndId(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") Date dateExtraction,
            @Param("id") Long id
    );
}
