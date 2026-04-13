package ma.vivalis.BKAM_CDR_API1.contrat.batch.processor;

import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_arch_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.*;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContratArchProcessor implements ItemProcessor<sss_cdr_inter_contrat_stat, sss_cdr_arch_contrat_stat> {
    @Override
    public @Nullable sss_cdr_arch_contrat_stat process(sss_cdr_inter_contrat_stat item) throws Exception {
        sss_cdr_arch_contrat_stat archiv = buildArchivFromIntermediaire(item);
        return archiv;
    }

    private sss_cdr_arch_contrat_stat buildArchivFromIntermediaire(sss_cdr_inter_contrat_stat item) {
        sss_cdr_arch_contrat_stat arch =sss_cdr_arch_contrat_stat.builder()
                .idCont(item.getIdCont())
                .id_lot(item.getId_lot())
                .dateExtraction(item.getDateExtraction())
                //.entObserv(item.getEntObserv())
                //.entDeclar(item.getEntDeclar())
                //.idDest(item.getIdDest())

                .actionType(item.getActionType())
                .dtRefCont(item.getDtRefCont())
                .guichetAgence(item.getGuichetAgence())
                .codLocAgence(item.getCodLocAgence())
                .tpCont(item.getTpCont())
                .tpCred(item.getTpCred())
                .dtlTpCred(item.getDtlTpCred())
                .creCoFin(item.getCreCoFin())
                .creConsor(item.getCreConsor())
                .objCred(item.getObjCred())
                .objCredDetail(item.getObjCredDetail())
                .monnaie(item.getMonnaie())
                .montIniAccord(item.getMontIniAccord())
                .montCreCoFin(item.getMontCreCoFin())
                .txChange(item.getTxChange())
                .dtContCredt(item.getDtContCredt())
                .dtDebloCred(item.getDtDebloCred())
                .dtClotIni(item.getDtClotIni())
                .dtClotCred(item.getDtClotCred())
                .motClotCont(item.getMotClotCont())
                .flagDiff(item.getFlagDiff())
                .dtModCondCred(item.getDtModCondCred())
                .motModCondCred(item.getMotModCondCred())
                .dtDebPerGraCap(item.getDtDebPerGraCap())
                .dtFinPerGraCap(item.getDtFinPerGraCap())
                .modPaiement(item.getModPaiement())
                .tpEche(item.getTpEche())
                .fxEche(item.getFxEche())
                .nombreTotEche(item.getNombreTotEche())
                .periodEche(item.getPeriodEche())
                .mtEche(item.getMtEche())
                .dt1Eche(item.getDt1Eche())
                .mont1Eche(item.getMont1Eche())
                .mont1EcheDiv(item.getMont1EcheDiv())
                .flagTxInt(item.getFlagTxInt())
                .txRef(item.getTxRef())
                .txAnnuelPourc(item.getTxAnnuelPourc())
                .txTAEG(item.getTxTAEG())
                .hmRibh(item.getHmRibh())
                .cmFxWkl(item.getCmFxWkl())
                .freqMiseJourTxInt(item.getFreqMiseJourTxInt())
                .LTVIni(item.getLTVIni())
                .tpSecuritization(item.getTpSecuritization())
                .exisGarant(item.getExisGarant())
                .mntGarant(item.getMntGarant())
                .build();



        // ── ListCliContrat ──
        if (item.getListCliContrat() != null && !item.getListCliContrat().isEmpty()) {
            arch.setListCliContrat(
                    item.getListCliContrat().stream().map(cliCon -> {
                        ListCliContrat_arch a = ListCliContrat_arch.builder()
                                .codClient(cliCon.getCodClient())
                                .capAutoriseEnt(cliCon.getCapAutoriseEnt())
                                .valProcVersEnt(cliCon.getValProcVersEnt())
                                .build();
                        a.setContrat(arch);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  // ✅ toSet() au lieu de toList()
        }


        // ── ListLinkContrat ──
        if (item.getListLinkContrat() != null && !item.getListLinkContrat().isEmpty()) {
            arch.setListLinkContrat(
                    item.getListLinkContrat().stream().map(linkCon -> {
                        ListLinkContrat_arch a = ListLinkContrat_arch.builder()
                                .idContAss(linkCon.getIdContAss())
                                .tpConnex(linkCon.getTpConnex())
                                .build();
                        a.setContrat(arch);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  // ✅ toSet() au lieu de toList()
        }


        // ── ListConsort ──
        if (item.getListConsort() != null && !item.getListConsort().isEmpty()) {
            arch.setListConsort(
                    item.getListConsort().stream().map(listConsrt -> {
                        ListConsort_arch a = ListConsort_arch.builder()
                                .idEnt(listConsrt.getIdEnt())
                                .relEntPart(listConsrt.getRelEntPart())
                                .build();
                        a.setContrat(arch);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  // ✅ toSet() au lieu de toList()
        }


        //ListGarant
        if (item.getListGarant() != null && !item.getListGarant().isEmpty()) {
            arch.setListGarant(
                    item.getListGarant().stream().map(listGar -> {
                        ListGarant_arch a = ListGarant_arch.builder()
                                .idGar(listGar.getIdGar())
                                .build();
                        a.setContrat(arch);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet())); // ✅ toSet() au lieu de toList()
        }
        return arch;
    }
}
