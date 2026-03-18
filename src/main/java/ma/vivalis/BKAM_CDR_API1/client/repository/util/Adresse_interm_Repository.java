package ma.vivalis.BKAM_CDR_API1.client.repository.util;

import ma.vivalis.BKAM_CDR_API1.client.model.util.Adresse_interm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Adresse_interm_Repository extends JpaRepository<Adresse_interm, Long> {
}
