package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor;

import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_arch_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ContratPerArchProcessor implements ItemProcessor<sss_cdr_inter_contrat_per, sss_cdr_arch_contrat_per> {
    @Override
    public @Nullable sss_cdr_arch_contrat_per process(sss_cdr_inter_contrat_per item) throws Exception {
        sss_cdr_arch_contrat_per archiv=buildArchFromIntermediaire(item);
        return archiv;
    }


    private sss_cdr_arch_contrat_per buildArchFromIntermediaire(sss_cdr_inter_contrat_per item) {
        sss_cdr_arch_contrat_per archiv=sss_cdr_arch_contrat_per.builder()
                .idCont(item.getIdCont())
                .id_lot(item.getId_lot())
                .dateExtraction(item.getDateExtraction())
                .montDu(item.getMontDu())
                .montUtilCred(item.getMontUtilCred())
                .montUtilCredDev(item.getMontUtilCredDev())
                .actionType(item.getActionType())
                .dateRef(item.getDateRef())
                .montRest(item.getMontRest())
                .montRestDev(item.getMontRestDev())
                .montComAg(item.getMontComAg())
                .commSpecif(item.getCommSpecif())
                .interCourMrg(item.getInterCourMrg())
                .caFactor(item.getCaFactor())
                .tpReembAntc(item.getTpReembAntc())
                .montReembAntc(item.getMontReembAntc())
                .dtProcRevTxInt(item.getDtProcRevTxInt())
                .colRefin(item.getColRefin())
                .nbEcheRest(item.getNbEcheRest())
                .dtProcEche(item.getDtProcEche())
                .dtDernEchePay(item.getDtDernEchePay())
                .nbEcheImp(item.getNbEcheImp())
                .montEcheImp(item.getMontEcheImp())
                .stPaiement(item.getStPaiement())
                .dtStPaiement(item.getDtStPaiement())
                .classCreanceSouff(item.getClassCreanceSouff())
                .dtClassCreanceSouff(item.getDtClassCreanceSouff())
                .contentieux(item.getContentieux())
                .creanceProv(item.getCreanceProv())
                .montProv(item.getMontProv())
                .txProvCont(item.getTxProvCont())
                .codClient(item.getCodClient())
                .montEncCli(item.getMontEncCli())
                .LGDCont(item.getLGDCont())
                .dtLGD(item.getDtLGD())
                .EADCont(item.getEADCont())
                .dtEAD(item.getDtEAD())
                .ECLCont(item.getECLCont())
                .dtECL(item.getDtECL())




                .build();
        return archiv;
    }
}
