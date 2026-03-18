package ma.vivalis.BKAM_CDR_API1.client.repository.util;

import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPM_snap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesIntPM_Repository extends JpaRepository<DonneesIntPM_snap, Long> {
}
