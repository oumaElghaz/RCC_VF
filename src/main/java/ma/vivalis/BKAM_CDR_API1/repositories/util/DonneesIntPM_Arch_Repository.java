package ma.vivalis.BKAM_CDR_API1.repositories.util;

import ma.vivalis.BKAM_CDR_API1.entities.util.DonneesIntPM_Arch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonneesIntPM_Arch_Repository extends JpaRepository<DonneesIntPM_Arch, Long> {
}
