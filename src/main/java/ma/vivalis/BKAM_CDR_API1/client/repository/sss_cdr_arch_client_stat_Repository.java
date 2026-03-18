package ma.vivalis.BKAM_CDR_API1.client.repository;

import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_arch_client_stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface sss_cdr_arch_client_stat_Repository extends JpaRepository<sss_cdr_arch_client_stat, String> {
    @Query("SELECT c.id_lot FROM sss_cdr_arch_client_stat c WHERE c.id_client = :id")
    String findIdLotById(@Param("id") String id);

    // ✅ Charger TOUT en une seule requête (pas de LAZY)
    @Query("SELECT DISTINCT a FROM sss_cdr_arch_client_stat a " +
            "LEFT JOIN FETCH a.adresse " +
            "LEFT JOIN FETCH a.donneesInts_pp " +
            "LEFT JOIN FETCH a.donneesInts_pm " +
            "LEFT JOIN FETCH a.actionnariats " +
            "LEFT JOIN FETCH a.benEffects " +
            "WHERE a.id_lot = (SELECT MAX(c2.id_lot) FROM sss_cdr_arch_client_stat c2) "
    )
    List<sss_cdr_arch_client_stat> findAllWithRelations();

    //Optional<sss_cdr_arch_client_stat> findById_lotAndDateExtractionAndId_client(Integer id_lot, Date dateExtraction, String id_client);

    @Query("SELECT c FROM sss_cdr_arch_client_stat c " +
            "WHERE c.id_lot = :idLot " +
            "AND c.dateExtraction = :dateExtraction " +
            "AND c.id_client = :idClient")
    Optional<sss_cdr_arch_client_stat> findById_lotAndDateExtractionAndId_client(
            @Param("idLot") Integer idLot,
            @Param("dateExtraction") Date dateExtraction,
            @Param("idClient") String idClient
    );
}
