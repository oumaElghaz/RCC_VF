package ma.vivalis.BKAM_CDR_API1.common.repository;

import ma.vivalis.BKAM_CDR_API1.common.models.DecisionEnvoiApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DecisionRepository extends JpaRepository<DecisionEnvoiApi, Long> {


    @Query(value = "SELECT c.decision FROM DecisionEnvoiApi c WHERE c.id=(SELECT max(b.id) FROM DecisionEnvoiApi b) ", nativeQuery = true)
    public Boolean shouldRunStep();
}
