package ma.vivalis.BKAM_CDR_API1.common.repository.mapping;


import ma.vivalis.BKAM_CDR_API1.common.models.mapping.sss_cdr_mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface sss_cdr_mapping_Repository extends JpaRepository <sss_cdr_mapping, Long> {
    //@Query(value = "select codCibl from sss_cdr_mapping where ctab= :ctab  and codSrc= :codesrc ", nativeQuery = true)
    //String findCodCiblByCtabAndCodSrc(@Param("ctab") String ctab, @Param("codesrc") String codesrc);


    /**
     * Récupère un mapping spécifique par table de code et code source
     * @param ctab Code table (ex: "CDTYPT", "PAYS", etc.)
     * @param codSrc Code source à mapper
     * @return Le code cible mappé, ou null si pas trouvé
     */
    @Query("SELECT m.codCibl FROM sss_cdr_mapping m WHERE m.ctab = :ctab AND m.codSrc = :codSrc")
    String findCodCiblByCtabAndCodSrc(@Param("ctab") String ctab, @Param("codSrc") String codSrc);

    /**
     * OPTIMISÉ : Récupère TOUS les mappings d'une table de code
     * Utilisé pour pré-charger le cache au démarrage
     * @param ctab Code table (ex: "CDTYPT", "PAYS", etc.)
     * @return Liste complète des mappings pour cette table
     */
    @Query("SELECT m.codSrc , m.codCibl FROM sss_cdr_mapping m WHERE m.ctab = :ctab ORDER BY m.codSrc ASC")
    List<sss_cdr_mapping> findAllByCtab(@Param("ctab") String ctab);

    /**
     * Alternative avec pagination (si la table est très grosse)
     * @param ctab Code table
     * @return Liste des mappings avec pagination possible
     */
    List<sss_cdr_mapping> findByCtab(String ctab);

    /**
     * Compte le nombre de mappings pour une table donnée
     * @param ctab Code table
     * @return Nombre total de mappings
     */
    @Query("SELECT COUNT(m) FROM sss_cdr_mapping m WHERE m.ctab = :ctab")
    long countByCtab(@Param("ctab") String ctab);

    /**
     * Récupère les mappings pour plusieurs tables (batch)
     * @param ctabs Liste des codes tables
     * @return Liste des mappings pour ces tables
     */
    @Query("SELECT m FROM sss_cdr_mapping m WHERE m.ctab IN :ctabs ORDER BY m.ctab, m.codSrc")
    List<sss_cdr_mapping> findByCtabIn(@Param("ctabs") List<String> ctabs);

    /**
     * Cherche un mapping par pattern (utile pour debug)
     * @param ctab Code table
     * @param codSrcPattern Pattern de recherche
     * @return Liste des mappings correspondants
     */
    @Query("SELECT m FROM sss_cdr_mapping m WHERE m.ctab = :ctab AND m.codSrc LIKE %:pattern%")
    List<sss_cdr_mapping> findByCtabAndCodSrcContains(@Param("ctab") String ctab, @Param("pattern") String codSrcPattern);

}
