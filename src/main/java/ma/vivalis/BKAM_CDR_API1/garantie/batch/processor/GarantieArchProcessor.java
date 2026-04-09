package ma.vivalis.BKAM_CDR_API1.garantie.batch.processor;

import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_arch_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class GarantieArchProcessor implements ItemProcessor<sss_cdr_inter_garantie, sss_cdr_arch_garantie> {
    @Override
    public @Nullable sss_cdr_arch_garantie process(sss_cdr_inter_garantie item) throws Exception {
        sss_cdr_arch_garantie archiv=buildArchFromIntermediaire(item);
        return archiv;
    }

    private sss_cdr_arch_garantie buildArchFromIntermediaire(sss_cdr_inter_garantie item) {
        sss_cdr_arch_garantie arch=sss_cdr_arch_garantie.builder()
                .idGar(item.getIdGar())
                .id_lot(item.getId_lot())
                .dateExtraction(item.getDateExtraction())
                .actionType(item.getActionType())
                .dtRefGar(item.getDtRefGar())
                .dtCreatGar(item.getDtCreatGar())
                .dtFinGar(item.getDtFinGar())
                .renGar(item.getRenGar())
                .dtRenGar(item.getDtRenGar())
                .dtFinRenGar(item.getDtFinRenGar())
                .tpGar(item.getTpGar())
                .codClient(item.getCodClient())
                .codGarExt(item.getCodGarExt())
                .tpRefExtGar(item.getTpRefExtGar())
                .refExtGar(item.getRefExtGar())
                .prixAcqProp(item.getPrixAcqProp())
                .codLocalGar (item.getCodLocalGar())
                .montGar(item.getMontGar())
                .valOriGar(item.getValOriGar())
                .tpValInGar(item.getTpValInGar())
                .valActGar(item.getValActGar())
                .dtEvalGar(item.getDtEvalGar())
                .tpValActGar(item.getTpValActGar())
                .garEtat(item.getGarEtat())
                .nvGarAdossCred(item.getNvGarAdossCred())
                .etatExecGar(item.getEtatExecGar())
                .dtExecGar(item.getDtExecGar())


                .build();

        return arch;
    }
}
