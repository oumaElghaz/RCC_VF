package ma.vivalis.BKAM_CDR_API1.garantie.repository;

import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_arch_garantie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface sss_cdr_arch_garantie_repository  extends JpaRepository<sss_cdr_arch_garantie, String> {
    @Query("SELECT DISTINCT a FROM sss_cdr_arch_garantie a " //+
            //"WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_arch_garantie c2) "
    )
    List<sss_cdr_arch_garantie> findAllWithRelations();



    @Query("SELECT c FROM sss_cdr_arch_garantie c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.idGar = :idGar")
    Optional<sss_cdr_arch_garantie> findById_lotAndDateExtractionAndId_gar(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("idGar") String idGar
    );
}
