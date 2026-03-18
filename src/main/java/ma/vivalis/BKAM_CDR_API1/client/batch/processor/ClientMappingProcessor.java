package ma.vivalis.BKAM_CDR_API1.client.batch.processor;

import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.common.repository.mapping.sss_cdr_mapping_Repository;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;


import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientMappingProcessor implements ItemProcessor<sss_cdr_inter_client_stat, sss_cdr_client_stat> {
    private static final Logger log = LoggerFactory.getLogger(ClientMappingProcessor.class);
    private final MappingLoader mappingLoader;

    public ClientMappingProcessor(sss_cdr_mapping_Repository sssCdrMappingRepository, MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
       
    }
    

    @Override
    public @Nullable sss_cdr_client_stat process(sss_cdr_inter_client_stat inter) throws Exception {
        log.info("inter.getDateExtraction()   "+inter.getDateExtraction());
        log.info("inter.getId_lot()   "+inter.getId_lot());
        log.info("inter.getId_client()   "+inter.getId_client());
        // ── 1. Mapper les données principales du client ──
        sss_cdr_client_stat finalClient = sss_cdr_client_stat.builder()
                .id_client(inter.getId_client())
                .actionType(inter.getActionType())
                .dateExtraction(inter.getDateExtraction())
                .id_lot(inter.getId_lot())
                .entObserv(inter.getEntObserv())
                .entDeclar(inter.getEntDeclar())
                .dtRefEnt(inter.getDtRefEnt())
                .codClient(inter.getCodClient())
                .altCodClient(inter.getAltCodClient())
                .natClient(mappingLoader.map("CDTYPT", inter.getNatClient()))
                //.entLieeEtab(mappingLoader.map("ENTLIEEETAB", inter.getEntLieeEtab()))
                .entLieeEtab("1")
                .codAgEcon(mappingLoader.map("SACT", inter.getCodAgEcon()))
                .build();

        // ── 2. Mapper l'adresse ──
        if (inter.getAdresse() != null) {
            finalClient.setAdresse(sss_cdr_Adresse.builder()
                    .adresse(inter.getAdresse().getAdresse())
                    .codPostal(inter.getAdresse().getCodPostal())
                    .codLocal(mappingLoader.map("CodLocal", inter.getAdresse().getCodLocal()))
                    .codPays(mappingLoader.map("PAYS", inter.getAdresse().getCodPays()))
                    .numTeleph(inter.getAdresse().getNumTeleph())
                    .build());
        }

        // ── 3. Mapper les données PP ──
        if (inter.getDonneesInt_pp() != null) {
            DonneesIntPP_interm pp = inter.getDonneesInt_pp();
            finalClient.setDonneesInt_pp(sss_cdr_DonneesIntPP.builder()
                    .idPrincipal(pp.getIdPrincipal())
                    .tpIdPrincipal(mappingLoader.map("TYPDOC",pp.getTpIdPrincipal()))
                    .prenom(pp.getPrenom())
                    .nomFamille(pp.getNomFamille())
                    .paysDelivrance(mappingLoader.map("PAYS", pp.getPaysDelivrance()))
                    .dtDelivrance(pp.getDtDelivrance())
                    .dtExpiration(pp.getDtExpiration())
                    .TypePPPro(mappingLoader.map("SACT",pp.getTypePPPro()))
                    .RNAE(pp.getRNAE())
                    .dtNaissance(pp.getDtNaissance())
                    .codLocalNaissance(mappingLoader.map("CodLocal", pp.getCodLocalNaissance()))
                    .sexe(mappingLoader.map("SEXE", pp.getSexe()))
                    .nationalite(mappingLoader.map("NATI", pp.getNationalite()))
                    .sitFamille(mappingLoader.map("SIT_F", pp.getSitFamille()))
                    .codCatProf(mappingLoader.map("PROF", pp.getCodCatProf()))
                    .menage(pp.getMenage())
                    .qualAcadem(mappingLoader.map("QualAcadem",pp.getQualAcadem()))
                    .catClient(mappingLoader.map("RSDT",pp.getCatClient()))
                    .build());
        }

        // ── 4. Mapper les données PM ──
        if (inter.getDonneesInt_pm() != null) {
            DonneesIntPM_interm pm = inter.getDonneesInt_pm();
            finalClient.setDonneesInt_pm(sss_cdr_DonneesIntPM.builder()
                    .raisonSocial(pm.getRaisonSocial())
                    .sigle(pm.getSigle())
                    .formJur(mappingLoader.map("FJR", pm.getFormJur()))
                    .codTrib(mappingLoader.map("CDTR",pm.getCodTrib()))
                    .regCommerce(pm.getRegCommerce())
                    .ICE(pm.getICE())
                    .idFiscal(pm.getIdFiscal())
                    .numTaxeProf(pm.getNumTaxeProf())
                    .idSpecifique(pm.getIdSpecifique())
                    .codLEI(pm.getCodLEI())
                    .codActPrinc(mappingLoader.map("SACT",pm.getCodActPrinc()))
                    .codActSec(mappingLoader.map("SACT",pm.getCodActSec()))
                    .tailleEntrep(mappingLoader.map("TailleEntrep", pm.getTailleEntrep()))
                    .genre(pm.getGenre())
                    .dtCreation(pm.getDtCreation())
                    //.natMod(mappingLoader.map("NatMod",pm.getNatMod()))
                    .natMod(pm.getNatMod())
                    .dtMod(pm.getDtMod())
                    .flagSuc(pm.getFlagSuc())
                    .tpIdPrincSiege(mappingLoader.map("TYPDOC",pm.getTpIdPrincSiege()))
                    .idPrincSiege(pm.getIdPrincSiege())
                    .raisonSocSiege(pm.getRaisonSocSiege())
                    .groupAppart(mappingLoader.map("GroupAppart",pm.getGroupAppart()))
                    .build());
        }

        // ── 5. Mapper les actionnariats ──
        if (inter.getActionnariats() != null && !inter.getActionnariats().isEmpty()) {
            Set<sss_cdr_client_act> actsFinal = inter.getActionnariats().stream()
                    .map(act -> {
                        sss_cdr_client_act a = sss_cdr_client_act.builder()
                                .natActionnaire(mappingLoader.map("CDTYPT",act.getNatActionnaire()))
                                .formJurAct(mappingLoader.map("FJR", act.getFormJurAct()))
                                .tpIdPrincAct(mappingLoader.map("TYPDOC",act.getTpIdPrincAct()))
                                .idPrincAct(act.getIdPrincAct())
                                .codTribunAct(mappingLoader.map("CDTR",act.getCodTribunAct()))
                                .regCommerAct(act.getRegCommerAct())
                                .idSpecifiqueAct(act.getIdSpecifiqueAct())
                                .ICEAct(act.getICEAct())
                                .LEIAct(act.getLEIAct())
                                .payResAct(mappingLoader.map("PAYS", act.getPayResAct()))
                                .nomRaisonSocAct(act.getNomRaisonSocAct())
                                .qtpartCapSocAct(act.getQtpartCapSocAct())
                                .build();
                        a.setClient(finalClient);  // ← Lier au parent
                        return a;
                    }).collect(Collectors.toSet());
            finalClient.setActionnariats(actsFinal);
        }

        // ── 6. Mapper les bénéficiaires effectifs ──
        if (inter.getBenEffects() != null && !inter.getBenEffects().isEmpty()) {
            Set<sss_cdr_client_benef> bensFinal = inter.getBenEffects().stream()
                    .map(ben -> {
                        sss_cdr_client_benef b = sss_cdr_client_benef.builder()
                                .typIdBenEffect(mappingLoader.map("TYPDOC",ben.getTypIdBenEffect()))
                                .idBenEffect(ben.getIdBenEffect())
                                .nomBenEffect(ben.getNomBenEffect())
                                .preBenEffect(ben.getPreBenEffect())
                                .natBenEffect(mappingLoader.map("NATI", ben.getNatBenEffect()))
                                .build();
                        b.setClient(finalClient);  // ← Lier au parent
                        return b;
                    }).collect(Collectors.toSet());
            finalClient.setBenEffects(bensFinal);
        }

        return finalClient;
    }

    
}
