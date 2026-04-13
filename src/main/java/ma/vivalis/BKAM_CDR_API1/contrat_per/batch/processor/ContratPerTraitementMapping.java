package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor;

import generated.ComConPer;
import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_contrat_per;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_inter_contrat_per;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class ContratPerTraitementMapping implements ItemProcessor<sss_cdr_inter_contrat_per, ComConPer> {

    private final MappingLoader mappingLoader;

    public ContratPerTraitementMapping(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }


    @Override
    public @Nullable ComConPer process(sss_cdr_inter_contrat_per item) throws Exception {
        sss_cdr_contrat_per contrat=mappingTraitement(item);
        ComConPer comConPer = mapContratToXmlElement(contrat);
        return comConPer;
    }

    private sss_cdr_contrat_per mappingTraitement(sss_cdr_inter_contrat_per item) {
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

    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    private ComConPer mapContratToXmlElement(sss_cdr_contrat_per item) throws DatatypeConfigurationException {
        ComConPer com=new ComConPer();
        ComConPer.ConPer conPer=new ComConPer.ConPer();
        ComConPer.ConPer.ListCliCredit listCliCredit=new ComConPer.ConPer.ListCliCredit();
        ComConPer.ConPer.ListCliCredit.CliCredit cliCredit=new ComConPer.ConPer.ListCliCredit.CliCredit();
        ComConPer.ConPer.DataRiskContrat dataRiskContrat=new ComConPer.ConPer.DataRiskContrat();
        ComConPer.ConPer.DataRiskContrat.RiskContrat riskContrat=new ComConPer.ConPer.DataRiskContrat.RiskContrat();
        //cliCredit
        cliCredit.setCodClient(item.getCodClient());
        cliCredit.setMontEncCli(BigDecimal.valueOf(item.getMontEncCli()));
        listCliCredit.getCliCredit().add(cliCredit);
        //riskContrat
        riskContrat.setLGDCont(BigDecimal.valueOf(item.getLGDCont()));
        riskContrat.setDtLGD(convertDateToXml(item.getDtLGD()));
        riskContrat.setEADCont(BigDecimal.valueOf(item.getEADCont()));
        riskContrat.setDtEAD(convertDateToXml(item.getDtEAD()));
        riskContrat.setECLCont(BigDecimal.valueOf(item.getECLCont()));
        riskContrat.setDtECL(convertDateToXml(item.getDtECL()));
        dataRiskContrat.getRiskContrat().add(riskContrat);
        //conPer
        conPer.setActionType(String.valueOf(item.getActionType()));
        conPer.setIdCont(item.getIdCont());
        conPer.setMontDu(BigDecimal.valueOf(item.getMontDu()));
        conPer.setMontUtilCred(BigDecimal.valueOf(item.getMontUtilCred()));
        conPer.setMontUtilCredDev(BigDecimal.valueOf(item.getMontUtilCredDev()));
        conPer.setMontRest(BigDecimal.valueOf(item.getMontRest()));
        conPer.setMontRestDev(BigDecimal.valueOf(item.getMontRestDev()));
        conPer.setMontComAg(BigDecimal.valueOf(item.getMontComAg()));
        conPer.setCommSpecif(BigDecimal.valueOf(item.getCommSpecif()));
        conPer.setInterCourMrg(BigDecimal.valueOf(item.getInterCourMrg()));
        conPer.setCaFactor(BigDecimal.valueOf(item.getCaFactor()));
        conPer.setTpReembAntc(item.getTpReembAntc());
        conPer.setMontReembAntc(BigDecimal.valueOf(item.getMontReembAntc()));
        conPer.setDtProcRevTxInt(convertDateToXml(item.getDtProcRevTxInt()));
        conPer.setColRefin(item.getColRefin());
        conPer.setNbEcheRest(BigDecimal.valueOf(item.getNbEcheRest()));
        conPer.setDtProcEche(convertDateToXml(item.getDtProcEche()));
        conPer.setDtDernEchePay(convertDateToXml(item.getDtDernEchePay()));
        conPer.setNbEcheImp(BigDecimal.valueOf(item.getNbEcheImp()));
        conPer.setMontEcheImp(BigDecimal.valueOf(item.getMontEcheImp()));
        conPer.setStPaiement(item.getStPaiement());
        conPer.setDtStPaiement(convertDateToXml(item.getDtStPaiement()));
        conPer.setClassCreanceSouff(item.getClassCreanceSouff());
        conPer.setDtClassCreanceSouff(convertDateToXml(item.getDtClassCreanceSouff()));
        conPer.setContentieux(item.getContentieux());
        conPer.setCreanceProv(item.getCreanceProv());
        conPer.setMontProv(BigDecimal.valueOf(item.getMontProv()));
        conPer.setTxProvCont(BigDecimal.valueOf(item.getTxProvCont()));


        conPer.getListCliCredit().add(listCliCredit);
        conPer.getDataRiskContrat().add(dataRiskContrat);

        //com
        com.setDtRef(convertDateToXml(item.getDateRef()));
        com.getConPer().add(conPer);
        return com;

    }
}
