package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor;


import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_arch_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_inter_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.ComInfNeg_arch;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.InfNeg_arch;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InfoNegaArchProcessor implements ItemProcessor<sss_cdr_inter_infoNegative, sss_cdr_arch_infoNegative> {
    @Override
    public @Nullable sss_cdr_arch_infoNegative process(sss_cdr_inter_infoNegative item) throws Exception {
        sss_cdr_arch_infoNegative arc=buildArchiv(item);
        return arc;
    }

    private sss_cdr_arch_infoNegative buildArchiv(sss_cdr_inter_infoNegative inter){

        sss_cdr_arch_infoNegative arch = sss_cdr_arch_infoNegative.builder()
                .id(inter.getId())
                .id_lot(inter.getId_lot())
                .dateExtraction(inter.getDateExtraction())
                .idDest(inter.getIdDest())
                .entDeclar(inter.getEntDeclar())
                .entObserv(inter.getEntObserv())
                .build();



        if (inter.getComInfNegs() != null) {
            List<ComInfNeg_arch> infArchiv = inter.getComInfNegs().stream()
                    .map(inf -> {
                        ComInfNeg_arch a = ComInfNeg_arch.builder()
                                .dtRef(inf.getDtRef())
                                .build();
                        if(inf.getInfNegList()!= null){
                            List<InfNeg_arch> g=inf.getInfNegList().stream().map(r-> {InfNeg_arch e=InfNeg_arch.builder()
                                    .actionType(r.getActionType())
                                    .codClient(r.getCodClient())
                                    .dtObsInfNegInc(r.getDtObsInfNegInc())
                                    .dtSortie(r.getDtSortie())
                                    .montInc(r.getMontInc())
                                    .refInfoNeg(r.getRefInfoNeg())
                                    .statInfoNeg(r.getStatInfoNeg())
                                    .tpInfNegInc(r.getTpInfNegInc())
                                    .build();
                                e.setComInf(a);;  // ← Lier au parent comInf
                                return e;
                            }).collect(Collectors.toList());
                            a.setInfNegList(g);}

                        a.setInfoNeg(arch); // ← Lier au parent archiv
                        return a;
                    }).collect(Collectors.toList());
            arch.setComInfNegs(infArchiv);
        }
        return arch;
    }
}
