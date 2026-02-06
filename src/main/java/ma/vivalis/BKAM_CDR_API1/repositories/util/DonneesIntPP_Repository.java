package ma.vivalis.BKAM_CDR_API1.repositories.util;

import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesIntPP_Repository extends JpaRepository<DonneesIntPP, Long> {
}
