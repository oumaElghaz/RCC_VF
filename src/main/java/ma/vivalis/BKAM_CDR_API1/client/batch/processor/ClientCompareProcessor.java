package ma.vivalis.BKAM_CDR_API1.client.batch.processor;

import jakarta.annotation.PostConstruct;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_arch_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
import ma.vivalis.BKAM_CDR_API1.client.repository.sss_cdr_arch_client_stat_Repository;
import ma.vivalis.BKAM_CDR_API1.common.models.lotSequence.LotSequence;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.ActionType;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_snapshot_client_stat;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_snapshot_client_act;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_snapshot_client_benef;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ClientCompareProcessor implements ItemProcessor<sss_cdr_snapshot_client_stat, sss_cdr_inter_client_stat> {
    private final sss_cdr_arch_client_stat_Repository sss_cdr_arch_client_stat_Repository;
    private final LotSequenceRepository lotSequenceRepository;
    // Cache : charger TOUS les archivés en mémoire une seule fois
    private Map<String, sss_cdr_arch_client_stat> archivCache;
    private static final Logger log = LoggerFactory.getLogger(ClientCompareProcessor.class);
    private int lot_id;
    private String natMod;
    private boolean initialized = false;
    public ClientCompareProcessor(sss_cdr_arch_client_stat_Repository sssCdrArchClientStatRepository, LotSequenceRepository lotSequenceRepository) {
        sss_cdr_arch_client_stat_Repository = sssCdrArchClientStatRepository;
        this.lotSequenceRepository = lotSequenceRepository;
    }

    @PostConstruct
    public void loadCache() {
        archivCache = new HashMap<>();
        //lot_id = getNextLotId(); // Initialiser le lot_id pour ce batch

        // findAllWithRelations() au lieu de findAll()
        sss_cdr_arch_client_stat_Repository.findAllWithRelations().forEach(a ->
                archivCache.put(a.getId_client(), a));

        //log.info(" Cache archiv chargé (avec relations) : {} entrées",
                //archivCache.size());
        //sss_cdr_arch_client_stat_Repository.findAll().forEach(a -> archivCache.put(a.getId_client(), a));
    }

    public void resetForNewRun() {
        initialized = false;
        archivCache.clear();

        //  Utiliser findAllWithRelations() ici aussi
        sss_cdr_arch_client_stat_Repository.findAllWithRelations().forEach(a ->
                archivCache.put(a.getId_client(), a));

        //log.info( Reset — cache rechargé : {} entrées", archivCache.size());
    }

    @Override
    public @Nullable sss_cdr_inter_client_stat process(sss_cdr_snapshot_client_stat item) throws Exception {
        //  Initialiser le lot au premier appel
        initLotIfNeeded();
        sss_cdr_arch_client_stat archiv = archivCache.get(item.getId_client());

        if (archiv == null) {
            // NOUVEAU CLIENT → à insérer
            return buildIntermediaire(item, ActionType.EI,lot_id,null);
        }

        if (hasChanged(item, archiv)) {
            //  CLIENT MODIFIÉ → à mettre à jour
            return buildIntermediaire(item, ActionType.EU,lot_id, natMod);
        }

        //  INCHANGÉ → null = filtré, pas inséré dans intermédiaire
        return null;
    }

    private boolean hasChanged(sss_cdr_snapshot_client_stat snapshot, sss_cdr_arch_client_stat archiv) {
        // Comparer les champs clés pour détecter un changement
        if (!equalsNullSafe(snapshot.getAltCodClient(), archiv.getAltCodClient())) return true;
        if (!equalsNullSafe(snapshot.getNatClient(), archiv.getNatClient())) return true;
        //if (!equalsNullSafe(snapshot.getCodClient(), archiv.getCodClient())) return true;
        if (!equalsNullSafe(snapshot.getEntLieeEtab(), archiv.getEntLieeEtab())) return true;
        //if (!equalsNullSafe(snapshot.getEntDeclar(), archiv.getEntDeclar())) return true;
        if (!equalsNullSafe(snapshot.getCodAgEcon(), archiv.getCodAgEcon())) return true;

        // Comparer l'adresse
        if (snapshot.getAdresse() != null && archiv.getAdresse() != null) {
            if (!equalsNullSafe(snapshot.getAdresse().getAdresse(), archiv.getAdresse().getAdresse())) {
                natMod="Adresse";
                return true;
            }
            if (!equalsNullSafe(snapshot.getAdresse().getCodPays(), archiv.getAdresse().getCodPays())) return true;
            if (!equalsNullSafe(snapshot.getAdresse().getCodLocal(), archiv.getAdresse().getCodLocal())) return true;
            if (!equalsNullSafe(snapshot.getAdresse().getCodPostal(), archiv.getAdresse().getCodPostal())) return true;
            if (!equalsNullSafe(snapshot.getAdresse().getNumTeleph(), archiv.getAdresse().getNumTeleph())) return true;
        }

        // Comparer les données PP
        if (snapshot.getDonneesInt_pp() != null && archiv.getDonneesInts_pp() != null) {
            if (!equalsNullSafe(snapshot.getDonneesInt_pp().getNomFamille(), archiv.getDonneesInts_pp().getNomFamille())) return true;
            if (!equalsNullSafe(snapshot.getDonneesInt_pp().getPrenom(), archiv.getDonneesInts_pp().getPrenom())) return true;
        }

        // Comparer les données PM
        if (snapshot.getDonneesInt_pm() != null && archiv.getDonneesInts_pm() != null) {
            if (!equalsNullSafe(snapshot.getDonneesInt_pm().getRaisonSocial(), archiv.getDonneesInts_pm().getRaisonSocial())){
                natMod="RaisonSocial";
                return true;}
            if (!equalsNullSafe(snapshot.getDonneesInt_pm().getRegCommerce(), archiv.getDonneesInts_pm().getRegCommerce()))
            {
                log.info("snapshot.getDonneesInt_pm().getRegCommerce()  {}",snapshot.getDonneesInt_pm().getRegCommerce());
                log.info("archiv.getDonneesInts_pm().getRegCommerce()  {}",archiv.getDonneesInts_pm().getRegCommerce());

                //natMod="RegCommerce";
                return true;}
            if (!equalsNullSafe(snapshot.getDonneesInt_pm().getCodTrib(), archiv.getDonneesInts_pm().getCodTrib()))
            {
                //natMod="CodTrib";
                return true;}
            if (!equalsNullSafe(snapshot.getDonneesInt_pm().getFormJur(), archiv.getDonneesInts_pm().getFormJur()))
            {
                //natMod="FormJur";
                return true;}
            if (!equalsNullSafe(snapshot.getDonneesInt_pm().getICE(), archiv.getDonneesInts_pm().getICE()))
            {
                //natMod="ICE";
                return true;}
        }else{
            natMod="";
            return false;
        }

        //Comparer les actionnaires
        if (snapshot.getActionnariats() != null && archiv.getActionnariats() != null) {
            for (sss_cdr_snapshot_client_act act: snapshot.getActionnariats()) {
                boolean found = false;
                for (sss_cdr_arch_client_act actArch : archiv.getActionnariats()) {
                    if (equalsNullSafe(act.getIdPrincAct(), actArch.getIdPrincAct()) &&
                            equalsNullSafe(act.getTpIdPrincAct(), actArch.getTpIdPrincAct())) {
                        found = true;
                        if (!equalsNullSafe(act.getNomRaisonSocAct(), actArch.getNomRaisonSocAct())) return true;
                        if (!equalsNullSafe(act.getNatActionnaire(), actArch.getNatActionnaire())) return true;
                        if (!equalsNullSafe(act.getRegCommerAct(), actArch.getRegCommerAct())) return true;
                        break;
                    }
                }
                if (!found) return true; // actionnaire dans snapshot pas trouvé dans archivé
            }

        }
        //comparer les beneficiaires
        if (snapshot.getBenEffects() != null && archiv.getBenEffects() != null) {
            for (sss_cdr_snapshot_client_benef benef: snapshot.getBenEffects()) {
                boolean found = false;
                for (sss_cdr_arch_client_benef benefArch : archiv.getBenEffects()) {
                    if (equalsNullSafe(benef.getIdBenEffect(), benefArch.getIdBenEffect()) &&
                            equalsNullSafe(benef.getNomBenEffect(), benefArch.getNomBenEffect())) {
                        found = true;
                        if (!equalsNullSafe(benef.getPreBenEffect(), benefArch.getPreBenEffect())) return true;
                        if (!equalsNullSafe(benef.getNatBenEffect(), benefArch.getNatBenEffect())) return true;
                        if (!equalsNullSafe(benef.getTypIdBenEffect(), benefArch.getTypIdBenEffect())) return true;
                        break;
                    }
                }
                if (!found) return true; // actionnaire dans snapshot pas trouvé dans archivé
            }

        }
        return false;
    }

    private boolean equalsNullSafe(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }


    private sss_cdr_inter_client_stat buildIntermediaire(
            sss_cdr_snapshot_client_stat snapshot, ActionType actionType, int lot_id, String natMod) {
//  Diagnostic — vérifier ce que le snapshot contient




                LocalDateTime dateDeclaration = LocalDateTime.now();

        sss_cdr_inter_client_stat inter= sss_cdr_inter_client_stat.builder()
                .id_client(snapshot.getId_client())
                .actionType(actionType)
                .dateExtraction(dateDeclaration)
                .id_lot(lot_id)
                //.entObserv(snapshot.getEntObserv())
                //.entDeclar(snapshot.getEntDeclar())
                .dtRefEnt(snapshot.getDtRefEnt())
                //.codClient(snapshot.getCodClient())
                .altCodClient(snapshot.getAltCodClient())
                .natClient(snapshot.getNatClient())
                .entLieeEtab(snapshot.getEntLieeEtab())
                //.codAgEcon(snapshot.getCodAgEcon())
                .codAgEcon("112")

                .build();
        if (snapshot.getAdresse() != null) {
                // Adresse
            inter.setAdresse(Adresse_interm.builder()
                            .adresse(snapshot.getAdresse().getAdresse())

                .codPostal(snapshot.getAdresse().getCodPostal())
                    .codLocal("780")
                //.codLocal(snapshot.getAdresse().getCodLocal())  en attendant que le client fournie la bonne valeur
                .codPays(snapshot.getAdresse().getCodPays())
                .numTeleph(snapshot.getAdresse().getNumTeleph()).build());}
                // PP
        if (snapshot.getDonneesInt_pp() != null) {
            inter.setDonneesInt_pp(DonneesIntPP_interm.builder()
                        .idPrincipal(snapshot.getDonneesInt_pp().getIdPrincipal())
                        .tpIdPrincipal(snapshot.getDonneesInt_pp().getTpIdPrincipal())
                        .prenom( snapshot.getDonneesInt_pp().getPrenom() )
                        .nomFamille( snapshot.getDonneesInt_pp().getNomFamille())
                        .nationalite( snapshot.getDonneesInt_pp().getNationalite() )
                        .paysDelivrance("MA")
                        //.paysDelivrance(snapshot.getDonneesInt_pp().getPaysDelivrance())
                        .dtDelivrance(snapshot.getDonneesInt_pp().getDtDelivrance())
                        .dtExpiration(snapshot.getDonneesInt_pp().getDtExpiration())
                        //.TypePPPro("112")
                        //.TypePPPro(snapshot.getDonneesInt_pp().getTypePPPro())
                        .RNAE(snapshot.getDonneesInt_pp().getRNAE())
                        .dtNaissance(snapshot.getDonneesInt_pp().getDtNaissance())
                        //.codLocalNaissance(snapshot.getDonneesInt_pp().getCodLocalNaissance())
                        .codLocalNaissance("780")
                        .sexe(snapshot.getDonneesInt_pp().getSexe())
                        .sitFamille(snapshot.getDonneesInt_pp().getSitFamille())
                        .codCatProf(snapshot.getDonneesInt_pp().getCodCatProf())
                        .menage(snapshot.getDonneesInt_pp().getMenage())
                        .qualAcadem(snapshot.getDonneesInt_pp().getQualAcadem())
                        .catClient(snapshot.getDonneesInt_pp().getCatClient())
                        .build());
            if("E".equalsIgnoreCase(snapshot.getNatClient())){
                inter.getDonneesInt_pp().setTypePPPro(snapshot.getDonneesInt_pp().getTypePPPro());
            }else{
                inter.getDonneesInt_pp().setTypePPPro(null);
            }

        }
        if (snapshot.getDonneesInt_pm() != null) {
            inter.setDonneesInt_pm(DonneesIntPM_interm.builder()
                            .raisonSocial( snapshot.getDonneesInt_pm().getRaisonSocial() )
                            .formJur( snapshot.getDonneesInt_pm().getFormJur() )
                            .ICE( snapshot.getDonneesInt_pm().getICE() )
                            .natMod(natMod)
                            .sigle(snapshot.getDonneesInt_pm().getSigle())
                            .codTrib(snapshot.getDonneesInt_pm().getCodTrib())
                            .regCommerce(snapshot.getDonneesInt_pm().getRegCommerce())
                            .idFiscal(snapshot.getDonneesInt_pm().getIdFiscal())
                            .numTaxeProf(snapshot.getDonneesInt_pm().getNumTaxeProf())
                            .idSpecifique(snapshot.getDonneesInt_pm().getIdSpecifique())
                            .codLEI(snapshot.getDonneesInt_pm().getCodLEI())
                            .codActPrinc(snapshot.getDonneesInt_pm().getCodActPrinc())
                            .codActSec(snapshot.getDonneesInt_pm().getCodActSec())
                            .tailleEntrep(snapshot.getDonneesInt_pm().getTailleEntrep())
                            .genre(snapshot.getDonneesInt_pm().getGenre())
                            .dtCreation(snapshot.getDonneesInt_pm().getDtCreation())
                            //.dtMod(snapshot.getDonneesInt_pm().getDtMod())
                            .flagSuc(snapshot.getDonneesInt_pm().getFlagSuc())
                            .tpIdPrincSiege(snapshot.getDonneesInt_pm().getTpIdPrincSiege())
                            .idPrincSiege(snapshot.getDonneesInt_pm().getIdPrincSiege())
                            .raisonSocSiege(snapshot.getDonneesInt_pm().getRaisonSocSiege())
                            .groupAppart(snapshot.getDonneesInt_pm().getGroupAppart())
                            .build());
            if(natMod != null){
                inter.getDonneesInt_pm().setDtMod(snapshot.getDtRefEnt());
            }
        }
        // ── Actionnariats ──
        if (snapshot.getActionnariats() != null && !snapshot.getActionnariats().isEmpty()) {
            inter.setActionnariats(
                    snapshot.getActionnariats().stream().map(act -> {
                        sss_cdr_inter_client_act a = sss_cdr_inter_client_act.builder()
                                .idPrincAct(act.getIdPrincAct())
                                .tpIdPrincAct(act.getTpIdPrincAct())
                                .nomRaisonSocAct(act.getNomRaisonSocAct())
                                .natActionnaire(act.getNatActionnaire())
                                .regCommerAct(act.getRegCommerAct())
                                .formJurAct(act.getFormJurAct())
                                .codTribunAct(act.getCodTribunAct())
                                .idSpecifiqueAct(act.getIdSpecifiqueAct())
                                .ICEAct(act.getICEAct())
                                .LEIAct(act.getLEIAct())
                                .payResAct(act.getPayResAct())
                                .qtpartCapSocAct(act.getQtpartCapSocAct())
                                .build();
                        a.setClient(inter);  //  Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  //  toSet() au lieu de toList()
        }

// ── Bénéficiaires ──
        if (snapshot.getBenEffects() != null && !snapshot.getBenEffects().isEmpty()) {
            inter.setBenEffects(
                    snapshot.getBenEffects().stream().map(benef -> {
                        sss_cdr_inter_client_benef b = sss_cdr_inter_client_benef.builder()
                                .idBenEffect(benef.getIdBenEffect())
                                .nomBenEffect(benef.getNomBenEffect())
                                .preBenEffect(benef.getPreBenEffect())
                                .natBenEffect(benef.getNatBenEffect())
                                .typIdBenEffect(benef.getTypIdBenEffect())
                                .build();
                        b.setClient(inter);  //  Lier au parent
                        return b;
                    }).collect(Collectors.toSet()));  //  toSet() au lieu de toList()
        }

        return inter;

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
