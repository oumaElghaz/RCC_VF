package ma.vivalis.BKAM_CDR_API1.client.batch.processor;

import jakarta.annotation.PostConstruct;
import ma.vivalis.BKAM_CDR_API1.client.model.dto.ClientChangeDTO;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@StepScope
public class ClientDirectMappingProcessor implements ItemProcessor<ClientChangeDTO, sss_cdr_inter_client_stat> {

    private static final Logger log = LoggerFactory.getLogger(ClientDirectMappingProcessor.class);

    //@Value("#{jobParameters['lot_id']}")
    private int lot_id;
    private boolean initialized = false;

    private final JdbcTemplate jdbcTemplate;
    private final LotSequenceRepository lotSequenceRepository;


    //  Cache chargé UNE SEULE FOIS au démarrage
    private Map<String, List<Map<String, Object>>> actCache;
    private Map<String, List<Map<String, Object>>> benefCache;

    public ClientDirectMappingProcessor(JdbcTemplate jdbcTemplate, LotSequenceRepository lotSequenceRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.lotSequenceRepository = lotSequenceRepository;
    }
    @PostConstruct
    public void initCaches() {
        log.info(" Chargement des caches actionnariats/bénéficiaires...");

        //  UNE requête pour TOUS les actionnariats
        List<Map<String, Object>> allAct = jdbcTemplate.queryForList(
                "SELECT sa.* FROM sss_cdr_snapshot_client_act sa"
        );
        actCache = allAct.stream()
                .collect(Collectors.groupingBy(r -> (String) r.get("id_client")));

        //  UNE requête pour TOUS les bénéficiaires
        List<Map<String, Object>> allBenef = jdbcTemplate.queryForList(
                "SELECT sb.* FROM sss_cdr_snapshot_client_benef sb"
        );
        benefCache = allBenef.stream()
                .collect(Collectors.groupingBy(r -> (String) r.get("id_client")));

        log.info(" Caches chargés : {} actionnariats, {} bénéficiaires",
                allAct.size(), allBenef.size());
    }

    @Override
    public @Nullable sss_cdr_inter_client_stat process(ClientChangeDTO dto) throws Exception {
        //  Initialiser le lot au premier appel
        initLotIfNeeded();
        sss_cdr_inter_client_stat client = sss_cdr_inter_client_stat.builder()
                .id_client(dto.getIdClient())
                .id_lot(lot_id)
                .dateExtraction(dto.getDateExtraction())
                //.entObserv(dto.getEntObserv())
                //.entDeclar(dto.getEntDeclar())
                .dtRefEnt(dto.getDtRefEnt())
                .actionType(ActionType.valueOf(dto.getActionType()))
                //.codClient(dto.getCodClient())
                .altCodClient(dto.getAltCodClient())
                .natClient(dto.getNatClient())
                .entLieeEtab(dto.getEntLieeEtab())
                .codAgEcon(dto.getCodAgEcon())
                .build();

        // ── Adresse ──
        if (dto.getAdresse() != null || dto.getCodPostal() != null || dto.getCodPays() != null) {
            client.setAdresse(Adresse_interm.builder()
                    .adresse(dto.getAdresse())
                    .codPostal(dto.getCodPostal())
                    .codLocal(dto.getCodLocal())
                    .codPays(dto.getCodPays())
                    .numTeleph(dto.getNumTeleph())
                    .build());
        }

        // ── PP ──
        if (dto.getIdPrincipal() != null) {
            client.setDonneesInt_pp(DonneesIntPP_interm.builder()
                    .idPrincipal(dto.getIdPrincipal())
                    .tpIdPrincipal(dto.getTpIdPrincipal())
                    .prenom(dto.getPrenom())
                    .nomFamille(dto.getNomFamille())
                    .nationalite(dto.getNationalite())
                    .sexe(dto.getSexe())
                    .dtNaissance(dto.getDtNaissance())
                    .codLocalNaissance(dto.getCodLocalNaissance())
                    .dtDelivrance(dto.getDtDelivrance())
                    .dtExpiration(dto.getDtExpiration())
                    .paysDelivrance(dto.getPaysDelivrance())
                    .catClient(dto.getCatClient())
                    .codCatProf(dto.getCodCatProf())
                    .sitFamille(dto.getSitFamille())
                    .qualAcadem(dto.getQualAcadem())
                    .RNAE(dto.getRNAE())
                    .menage(dto.getMenage())
                    .TypePPPro(dto.getTypePPPro())
                    .build());
        }

        // ── PM ──
        if (dto.getRaisonSocial() != null) {
            client.setDonneesInt_pm(DonneesIntPM_interm.builder()
                    .raisonSocial(dto.getRaisonSocial())
                    .formJur(dto.getFormJur())
                    .ICE(dto.getICE())
                    .codLEI(dto.getCodLEI())
                    .regCommerce(dto.getRegCommerce())
                    .codTrib(dto.getCodTrib())
                    .idFiscal(dto.getIdFiscal())
                    .numTaxeProf(dto.getNumTaxeProf())
                    .codActPrinc(dto.getCodActPrinc())
                    .codActSec(dto.getCodActSec())
                    .tailleEntrep(dto.getTailleEntrep())
                    .sigle(dto.getSigle())
                    .groupAppart(dto.getGroupAppart())
                    .genre(dto.getGenre())
                    .flagSuc(dto.getFlagSuc())
                    .dtCreation(dto.getPmDtCreation())
                    .dtMod(dto.getDtMod())
                    .natMod(dto.getNatMod())
                    .idPrincSiege(dto.getIdPrincSiege())
                    .tpIdPrincSiege(dto.getTpIdPrincSiege())
                    .raisonSocSiege(dto.getRaisonSocSiege())
                    .idSpecifique(dto.getIdSpecifique())
                    .build());
        }

        // ── Actionnariats (depuis le cache) ──
        List<Map<String, Object>> acts = actCache.get(dto.getIdClient());
        if (acts != null && !acts.isEmpty()) {
            Set<sss_cdr_inter_client_act> actSet = acts.stream().map(row -> {
                sss_cdr_inter_client_act a = sss_cdr_inter_client_act.builder()
                        .idPrincAct((String) row.get("idPrincAct"))
                        .tpIdPrincAct((String) row.get("tpIdPrincAct"))
                        .nomRaisonSocAct((String) row.get("nomRaisonSocAct"))
                        .natActionnaire((String) row.get("natActionnaire"))
                        .formJurAct((String) row.get("formJurAct"))
                        .regCommerAct((String) row.get("regCommerAct"))
                        .codTribunAct((String) row.get("codTribunAct"))
                        .idSpecifiqueAct((String) row.get("idSpecifiqueAct"))
                        .ICEAct((String) row.get("ICEAct"))
                        .LEIAct((String) row.get("LEIAct"))
                        .payResAct((String) row.get("payResAct"))
                        .qtpartCapSocAct(row.get("qtpartCapSocAct") != null
                                ? ((Number) row.get("qtpartCapSocAct")).intValue()
                                : null)
                        .client(client)
                        .build();
                return a;
            }).collect(Collectors.toSet());
            client.setActionnariats(actSet);
        }

        // ── Bénéficiaires (depuis le cache) ──
        List<Map<String, Object>> benefs = benefCache.get(dto.getIdClient());
        if (benefs != null && !benefs.isEmpty()) {
            Set<sss_cdr_inter_client_benef> benefSet = benefs.stream().map(row -> {
                sss_cdr_inter_client_benef b = sss_cdr_inter_client_benef.builder()
                        .idBenEffect((String) row.get("idBenEffect"))
                        .nomBenEffect((String) row.get("nomBenEffect"))
                        .preBenEffect((String) row.get("preBenEffect"))
                        .natBenEffect((String) row.get("natBenEffect"))
                        .typIdBenEffect((String) row.get("typIdBenEffect"))
                        .client(client)
                        .build();
                return b;
            }).collect(Collectors.toSet());
            client.setBenEffects(benefSet);
        }

        return client;
    }


    public synchronized int getNextLotId() {

        LotSequence seq = lotSequenceRepository.findById(1)
                .orElseGet(() -> {
                    LotSequence s = new LotSequence();
                    s.setVal(0);
                    return lotSequenceRepository.save(s);
                });

        int current = seq.getVal();

        int next;
        if (current >= 999999) {
            next = 0;
        } else {
            next = current + 1;
        }

        seq.setVal(next);
        lotSequenceRepository.save(seq);
        return next;
    }

    private synchronized void initLotIfNeeded() {
        if (!initialized) {
            lot_id = getNextLotId();
            initialized = true;
            log.info(" Lot ID initialisé = {}", lot_id);
        }
    }

}
