package ma.vivalis.BKAM_CDR_API1.client.repository;

import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface sss_cdr_inter_client_stat_Repository extends JpaRepository<sss_cdr_inter_client_stat, String> {

    /**
     *  Compte le nombre de clients pour un lot donné
     */
    @Query("SELECT COUNT(c) FROM sss_cdr_inter_client_stat c WHERE c.id_lot = :idLot")
    long countByIdLot(@Param("idLot") Integer idLot);

    /**
     * PRINCIPAL : Récupère SEULEMENT les clients (sans relations)
     * Les relations seront initialisées séparément
     */
    @Query("SELECT c FROM sss_cdr_inter_client_stat c WHERE c.id_lot = :idLot ORDER BY c.id_client ASC")
    Page<sss_cdr_inter_client_stat> findByIdLot(@Param("idLot") Integer idLot, Pageable pageable);

    /**
     * Récupère TOUS les clients d'un lot (sans relations)
     */
    @Query("SELECT c FROM sss_cdr_inter_client_stat c WHERE c.id_lot = :idLot ORDER BY c.id_client ASC")
    List<sss_cdr_inter_client_stat> findAllByIdLot(@Param("idLot") Integer idLot);

    /**
     * Récupère un client par ID
     */
    @Override
    Optional<sss_cdr_inter_client_stat> findById(String idClient);

    /**
     * Compte le nombre total de clients
     */
    @Query("SELECT COUNT(c) FROM sss_cdr_inter_client_stat c")
    long count();

    /**
     * Compte le nombre de lots distincts
     */
    @Query("SELECT COUNT(DISTINCT c.id_lot) FROM sss_cdr_inter_client_stat c")
    long countDistinctLots();

    /**
     * Récupère tous les IDs de lots
     */
    @Query("SELECT DISTINCT c.id_lot FROM sss_cdr_inter_client_stat c ORDER BY c.id_lot DESC")
    List<Integer> findDistinctLots();

    /**
     * Compte les clients par lot
     */
    @Query("SELECT c.id_lot as lot, COUNT(c) as total FROM sss_cdr_inter_client_stat c GROUP BY c.id_lot ORDER BY c.id_lot DESC")
    List<Object[]> countClientsByLot();

    /**
     * Récupère le lot le plus récent
     */
    @Query("SELECT MAX(c.id_lot) FROM sss_cdr_inter_client_stat c")
    Integer findMaxIdLot();

    /**
     * Récupère le lot le plus ancien
     */
    @Query("SELECT MIN(c.id_lot) FROM sss_cdr_inter_client_stat c")
    Integer findMinIdLot();

    /**
     * Supprime tous les clients d'un lot
     */
    @Query("DELETE FROM sss_cdr_inter_client_stat c WHERE c.id_lot = :idLot")
    void deleteByIdLot(@Param("idLot") Integer idLot);

    /**
     * Vérifie si un lot existe
     */
    @Query("SELECT COUNT(c) > 0 FROM sss_cdr_inter_client_stat c WHERE c.id_lot = :idLot")
    boolean existsByIdLot(@Param("idLot") Integer idLot);

    /**
     * Récupère les clients filtrés par nature de client
     */
    @Query("SELECT c FROM sss_cdr_inter_client_stat c WHERE c.natClient = :natClient AND c.id_lot = :idLot")
    List<sss_cdr_inter_client_stat> findByNatClientAndIdLot(
            @Param("natClient") String natClient,
            @Param("idLot") Integer idLot);

    /**
     * Compte les clients par nature dans un lot
     */
    @Query("SELECT c.natClient as natClient, COUNT(c) as count FROM sss_cdr_inter_client_stat c WHERE c.id_lot = :idLot GROUP BY c.natClient")
    List<Object[]> countByNatClientInLot(@Param("idLot") Integer idLot);
}