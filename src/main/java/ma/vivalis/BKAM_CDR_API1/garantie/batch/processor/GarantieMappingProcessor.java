package ma.vivalis.BKAM_CDR_API1.garantie.batch.processor;

import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class GarantieMappingProcessor  implements ItemProcessor<sss_cdr_inter_garantie, sss_cdr_garantie> {
    private static final Logger log = LoggerFactory.getLogger(GarantieMappingProcessor.class);
    private final MappingLoader mappingLoader;

    public GarantieMappingProcessor(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }

    @Override
    public @Nullable sss_cdr_garantie process(sss_cdr_inter_garantie item) throws Exception {
        sss_cdr_garantie gar = sss_cdr_garantie.builder()

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

        return gar;
    }
}
