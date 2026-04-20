package ma.vivalis.BKAM_CDR_API1.contrat.repository;

import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_arch_contrat_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface sss_cdr_arch_contrat_stat_repository extends JpaRepository<sss_cdr_arch_contrat_stat, String> {
    @Query("SELECT c.id_lot FROM sss_cdr_arch_contrat_stat c WHERE c.idCont = :id")
    String findIdLotById(@Param("id") String id);

    // ✅ Charger TOUT en une seule requête (pas de LAZY)
    @Query("SELECT DISTINCT a FROM sss_cdr_arch_contrat_stat a " +
            "LEFT JOIN FETCH a.listCliContrat " +
            "LEFT JOIN FETCH a.listLinkContrat " +
            "LEFT JOIN FETCH a.listConsort " +
            "LEFT JOIN FETCH a.listGarant " //+
            //"WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_arch_contrat_stat c2) "
    )
    List<sss_cdr_arch_contrat_stat> findAllWithRelations();



    @Query("SELECT c FROM sss_cdr_arch_contrat_stat c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.idCont = :idCont")
    Optional<sss_cdr_arch_contrat_stat> findById_lotAndDateExtractionAndIdCont(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") LocalDateTime dateExtraction,
            @Param("idCont") String idCont
    );
}
