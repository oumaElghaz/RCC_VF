package ma.vivalis.BKAM_CDR_API1.repositories;


import ma.vivalis.BKAM_CDR_API1.entities.mapping.sss_cdr_mapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface sss_cdr_mapping_Repository extends JpaRepository <sss_cdr_mapping, Long> {
    @Query(value = "select CODCIBL from SSS_CDR_MAPP where ctab:=ctab  and codesrc:=codesrc ", nativeQuery = true)
    String findCodCiblByCtabAndCodSrc(@Param("ctab") String ctab, @Param("codesrc") String codesrc);

}
