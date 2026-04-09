package ma.vivalis.BKAM_CDR_API1.contrat_per.batch.processor;

import generated.ComConPer;
import ma.vivalis.BKAM_CDR_API1.contrat_per.model.sss_cdr_contrat_per;
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
public class ContratPerJaxbProcessor implements ItemProcessor<sss_cdr_contrat_per, ComConPer> {
    @Override
    public @Nullable ComConPer process(sss_cdr_contrat_per item) throws Exception {
        ComConPer com=mapContratToXmlElement(item);
        return com;
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
