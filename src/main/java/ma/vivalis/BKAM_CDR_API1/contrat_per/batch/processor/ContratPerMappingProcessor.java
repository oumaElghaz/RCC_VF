package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor;

import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ContratPerMappingProcessor implements ItemProcessor<sss_cdr_inter_contrat_per, sss_cdr_contrat_per> {
    private static final Logger log = LoggerFactory.getLogger(ContratPerMappingProcessor.class);
    private final MappingLoader mappingLoader;

    public ContratPerMappingProcessor(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }

    @Override
    public @Nullable sss_cdr_contrat_per process(sss_cdr_inter_contrat_per item) throws Exception {
        sss_cdr_contrat_per ctr_final=sss_cdr_contrat_per.builder()
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
                .colRefin(Boolean.valueOf("N"))
                .nbEcheRest(item.getNbEcheRest())
                .dtProcEche(item.getDtProcEche())
                .dtDernEchePay(item.getDtDernEchePay())
                .nbEcheImp(item.getNbEcheImp())
                .montEcheImp(item.getMontEcheImp())
                //.stPaiement(item.getStPaiement())
                .dtStPaiement(item.getDateRef())
                .classCreanceSouff(mappingLoader.map("T_DST",item.getClassCreanceSouff()))
                .dtClassCreanceSouff(item.getDtClassCreanceSouff())
                .contentieux(item.getContentieux())
                //.creanceProv(item.getCreanceProv())
                .montProv(item.getMontProv())
                //.txProvCont(item.getTxProvCont())
                .codClient(item.getCodClient())
                .montEncCli(item.getMontEncCli())
                .LGDCont(item.getLGDCont())
                .dtLGD(item.getDtLGD())
                .EADCont(item.getEADCont())
                .dtEAD(item.getDtEAD())
                .ECLCont(item.getECLCont())
                .dtECL(item.getDtECL())
                .build();

     switch (item.getStPaiement()){
         case "0":
             ctr_final.setStPaiement("1");
             break;

         case "1":
             ctr_final.setStPaiement("2");
             break;

         case "2":
             ctr_final.setStPaiement("3");
             break;

         case "3":
             ctr_final.setStPaiement("4");
             break;

         case "4":
             ctr_final.setStPaiement("5");
             break;

         case "5":
             ctr_final.setStPaiement("6");
             break;

         default:
             ctr_final.setStPaiement("7");


     }

     if(!"0".equalsIgnoreCase(String.valueOf(item.getMontProv())) || !(item.getMontProv() ==null)){
         ctr_final.setCreanceProv(Boolean.valueOf("O"));
     }else {
         ctr_final.setCreanceProv(Boolean.valueOf("N"));
     }

        switch (item.getClassCreanceSouff()){
            case "NOR":
                ctr_final.setTxProvCont((double) 0);
                break;

            case "IMP":
                ctr_final.setTxProvCont((double) 0);
                break;

            case "DOU":
                ctr_final.setTxProvCont((double) 20);
                break;

            case "CXP":
                ctr_final.setTxProvCont((double) 50);
                break;

            case "COM":
                ctr_final.setTxProvCont((double) 100);
                break;

            case "CTX":
                ctr_final.setTxProvCont((double) 100);
                break;


        }

        return ctr_final;
    }
}
