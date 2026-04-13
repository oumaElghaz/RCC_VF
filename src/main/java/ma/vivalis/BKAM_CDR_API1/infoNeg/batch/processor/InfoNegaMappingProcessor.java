package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor;

import ma.vivalis.BKAM_CDR_API1.client.batch.processor.ClientMappingProcessor;

import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_inter_infoNegative;

import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_InfNeg;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InfoNegaMappingProcessor implements ItemProcessor<sss_cdr_inter_infoNegative, sss_cdr_infoNegative> {

    private static final Logger log = LoggerFactory.getLogger(ClientMappingProcessor.class);
    private final MappingLoader mappingLoader;

    public InfoNegaMappingProcessor(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }

    @Override
    public @Nullable sss_cdr_infoNegative process(sss_cdr_inter_infoNegative inter) throws Exception {
        sss_cdr_infoNegative f=sss_cdr_infoNegative.builder()
                .id(inter.getId())
                .id_lot(inter.getId_lot())
                .dateExtraction(inter.getDateExtraction())
                //.idDest(inter.getIdDest())
                //.entDeclar(inter.getEntDeclar())
                //.entObserv(inter.getEntObserv())


                .build();


        if (inter.getComInfNegs() != null) {
            List<sss_cdr_ComInfNeg> infFinal = inter.getComInfNegs().stream()
                    .map(inf -> {
                        sss_cdr_ComInfNeg a = sss_cdr_ComInfNeg.builder()
                                .dtRef(inf.getDtRef())
                                .build();
                        if(inf.getInfNegList()!= null){
                            List<sss_cdr_InfNeg> g=inf.getInfNegList().stream().map(r-> {sss_cdr_InfNeg e=sss_cdr_InfNeg.builder()
                                    .actionType(r.getActionType())
                                    .codClient(r.getCodClient())
                                    .dtObsInfNegInc(r.getDtObsInfNegInc())
                                    .dtSortie(r.getDtSortie())
                                    .montInc(r.getMontInc())
                                    .refInfoNeg(r.getRefInfoNeg())
                                    .statInfoNeg(r.getStatInfoNeg())
                                    .tpInfNegInc(mappingLoader.map("T_INI", r.getTpInfNegInc()))
                                    .build();
                                e.setComInf(a);;  // ← Lier au parent comInf
                                return e;
                            }).collect(Collectors.toList());
                            a.setInfNegList(g);}

                        a.setInfoNeg(f); // ← Lier au parent archiv
                        return a;
                    }).collect(Collectors.toList());
            f.setComInfNegs(infFinal);
        }
        return f;
    }
}
