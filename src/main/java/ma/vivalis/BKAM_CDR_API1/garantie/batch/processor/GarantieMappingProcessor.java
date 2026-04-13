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
                //.renGar(item.getRenGar())
                .renGar(Boolean.valueOf("N"))
                .dtRenGar(item.getDtRenGar())
                .dtFinRenGar(item.getDtFinRenGar())
                .tpGar(mappingLoader.map("T_TPG",item.getTpGar()))
                .codClient(item.getCodClient())
                .codGarExt(item.getCodGarExt())
                .tpRefExtGar(mappingLoader.map("T_TRE",item.getTpRefExtGar()))
                .refExtGar(item.getRefExtGar())
                .prixAcqProp(item.getPrixAcqProp())
                .codLocalGar (mappingLoader.map("T_CNC",item.getCodLocalGar()))
                .montGar(item.getMontGar())
                .valOriGar(item.getValOriGar())
                //.tpValInGar(item.getTpValInGar())
                .tpValInGar("2")
                .valActGar(item.getValActGar())
                .dtEvalGar(item.getDtEvalGar())
                .tpValActGar(item.getTpValActGar())
                //.garEtat(item.getGarEtat())
                .garEtat(Boolean.valueOf("N"))
                .nvGarAdossCred(item.getNvGarAdossCred())
                .etatExecGar(item.getEtatExecGar())
                .dtExecGar(item.getDtExecGar())
                .build();

        if(item.getEtatExecGar() ==null){
            gar.setEtatExecGar(Boolean.valueOf("N"));
        }

        if((item.getRefExtGar() ==null || item.getRefExtGar().isEmpty()) && (item.getTpRefExtGar() ==null || item.getTpRefExtGar().isEmpty() )){
            gar.setTpRefExtGar("0");
        }
        else if("CGB".equalsIgnoreCase(item.getTpGar()) && (item.getTpRefExtGar() ==null || item.getTpRefExtGar().isEmpty() )){
            gar.setTpRefExtGar("2");
        }
        else if("AVA".equalsIgnoreCase(item.getTpGar()) && (item.getTpRefExtGar() ==null || item.getTpRefExtGar().isEmpty() )){
            gar.setTpRefExtGar("9");
        }
        else if( (item.getTpRefExtGar() ==null || item.getTpRefExtGar().isEmpty() )) {
            gar.setTpRefExtGar("1");
        }


        return gar;
    }
}
