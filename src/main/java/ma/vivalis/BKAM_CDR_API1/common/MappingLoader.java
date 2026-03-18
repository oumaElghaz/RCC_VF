package ma.vivalis.BKAM_CDR_API1.common;

import jakarta.annotation.PostConstruct;
import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientMappingProcessor;
import ma.vivalis.BKAM_CDR_API1.common.repository.mapping.sss_cdr_mapping_Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MappingLoader {
    private static final Logger log = LoggerFactory.getLogger(ClientMappingProcessor.class);


    private final sss_cdr_mapping_Repository sss_cdr_mapping_Repository;
    // Toutes les correspondances chargées en 1 seule requête
    private Map<String, Map<String, String>> allCorrespondances;

    public MappingLoader(sss_cdr_mapping_Repository sssCdrMappingRepository) {
        sss_cdr_mapping_Repository = sssCdrMappingRepository;
    }

/*    @PostConstruct
    public void init() {
        allCorrespondances = new HashMap<>();
        sss_cdr_mapping_Repository.findAll().forEach(a -> {
            if (a.getCodSrc() != null && a.getCodCibl() != null && a.getCtab() != null) {
                allCorrespondances.put(a.getCtab(), Map.of(a.getCodSrc(), a.getCodCibl()));
            } else {
                System.out.println("ATTENTION: enregistrement avec null détecté: " + a);
            }
        });    }*/
    @PostConstruct
    public void init() {
        allCorrespondances = new HashMap<>();
        sss_cdr_mapping_Repository.findAll().forEach(a -> {
            if (a.getCodSrc() != null && a.getCodCibl() != null && a.getCtab() != null) {
                // On récupère la map interne ou on la crée si besoin
                Map<String, String> sousMap = allCorrespondances.computeIfAbsent(a.getCtab(), k -> new HashMap<>());
                sousMap.put(a.getCodSrc(), a.getCodCibl());
            } else {
                System.out.println("ATTENTION: enregistrement avec null détecté: " + a);
            }
        });
    }

    // ═══════════════════════════════════════════════════════
    // Méthode utilitaire de mapping
    // ═══════════════════════════════════════════════════════
    public String map(String ctab, String codSrc) {
        if (codSrc == null || codSrc.isBlank()) {
            return null;
        }


        Map<String, String> correspondance = allCorrespondances.get(ctab);
        if (correspondance == null) {
            log.error("❌ Type de correspondance '{}' non trouvé", ctab);
            return codSrc;
        }

        String mappedValue = correspondance.get(codSrc);
        if (mappedValue == null) {
            log.warn("⚠️ [{}] : '{}' → valeur source conservée", ctab, codSrc);
            return codSrc;
        }
        //log.info("⚠️ valeur source  [{}] : '{}' → '{}'", ctab, codSrc, mappedValue);
        return mappedValue;
    }
}
