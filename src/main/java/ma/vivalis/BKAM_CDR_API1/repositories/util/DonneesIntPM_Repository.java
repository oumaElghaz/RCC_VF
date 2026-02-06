package ma.vivalis.BKAM_CDR_API1.repositories.util;

import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesIntPM_Repository extends JpaRepository<DonneesIntPM, Long> {
}
