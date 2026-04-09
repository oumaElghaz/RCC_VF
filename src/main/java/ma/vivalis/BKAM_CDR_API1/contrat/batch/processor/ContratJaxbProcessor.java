package ma.vivalis.BKAM_CDR_API1.contrat.batch.processor;

import generated.ComCon;
import ma.vivalis.BKAM_CDR_API1.common.CleanDate;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.sss_cdr_ListCliContrat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.sss_cdr_ListConsort;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.sss_cdr_ListGarant;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.sss_cdr_ListLinkContrat;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigDecimal;

@Component
public class ContratJaxbProcessor implements ItemProcessor<sss_cdr_contrat_stat, ComCon.Con> {
    private final CleanDate cleanDate;

    public ContratJaxbProcessor(CleanDate cleanDate) {
        this.cleanDate = cleanDate;
    }

    @Override
    public ComCon.@Nullable Con process(sss_cdr_contrat_stat item) throws Exception {
        ComCon.Con con = mapContratToXmlElement(item);
        return con;
    }

    private ComCon.Con mapContratToXmlElement(sss_cdr_contrat_stat item) throws DatatypeConfigurationException {
        ComCon.Con con= new ComCon.Con();
        con.setIdCont(item.getIdCont());
        con.setActionType(String.valueOf(item.getActionType()));
        con.setDtRefCont(cleanDate.convertDateToXml(item.getDtRefCont()));
        con.setGuichetAgence(item.getGuichetAgence());
        con.setCodLocAgence(item.getCodLocAgence());
        con.setTpCont(item.getTpCont());
        con.setTpCred(item.getTpCred());
        con.setDtlTpCred(String.valueOf(item.getDtlTpCred()));
        con.setCreCoFin(Boolean.valueOf(item.getCreCoFin()));
        con.setCreConsor(Boolean.valueOf(item.getCreConsor()));
        con.setObjCred(item.getObjCred());
        con.setObjCredDetail(item.getObjCredDetail());
        con.setMonnaie(item.getMonnaie());
        con.setMontIniAccord(BigDecimal.valueOf(item.getMontIniAccord()));
        con.setMontCreCoFin(BigDecimal.valueOf(item.getMontCreCoFin()));
        con.setTxChange(BigDecimal.valueOf(item.getTxChange()));
        con.setDtContCredt(cleanDate.convertDateToXml(item.getDtContCredt()));
        con.setDtDebloCred(cleanDate.convertDateToXml(item.getDtDebloCred()));
        con.setDtClotIni(cleanDate.convertDateToXml(item.getDtClotIni()));
        con.setDtClotCred(cleanDate.convertDateToXml(item.getDtClotCred()));
        con.setMotClotCont(item.getMotClotCont());
        con.setFlagDiff(Boolean.valueOf(item.getFlagDiff()));
        con.setDtModCondCred(cleanDate.convertDateToXml(item.getDtModCondCred()));
        con.setMotModCondCred(item.getMotModCondCred());
        con.setDtDebPerGraCap(cleanDate.convertDateToXml(item.getDtDebPerGraCap()));
        con.setDtFinPerGraCap(cleanDate.convertDateToXml(item.getDtFinPerGraCap()));
        con.setModPaiement(item.getModPaiement());
        con.setTpEche(item.getTpEche());
        con.setFxEche(Boolean.valueOf(item.getFxEche()));
        con.setNombreTotEche(BigDecimal.valueOf(item.getNombreTotEche()));
        con.setPeriodEche(item.getPeriodEche());
        con.setMtEche(BigDecimal.valueOf(item.getMtEche()));
        con.setDt1Eche(cleanDate.convertDateToXml(item.getDt1Eche()));
        con.setMont1Eche(BigDecimal.valueOf(item.getMont1Eche()));
        con.setMont1EcheDiv(BigDecimal.valueOf(item.getMont1EcheDiv()));
        con.setFlagTxInt(item.getFlagTxInt());
        con.setTxRef(item.getTxRef());
        con.setTxAnnuelPourc(BigDecimal.valueOf(item.getTxAnnuelPourc()));
        con.setTxTAEG(BigDecimal.valueOf(item.getTxTAEG()));
        con.setHmRibh(BigDecimal.valueOf(item.getHmRibh()));
        con.setCmFxWkl(BigDecimal.valueOf(item.getCmFxWkl()));
        con.setFreqMiseJourTxInt(item.getFreqMiseJourTxInt());
        con.setLTVIni(BigDecimal.valueOf(item.getLTVIni()));
        con.setTpSecuritization(Boolean.valueOf(item.getTpSecuritization()));
        con.setExisGarant(Boolean.valueOf(item.getExisGarant()));
        con.setMntGarant(BigDecimal.valueOf(item.getMntGarant()));

        //ListCliContrat
        if (item.getListCliContrat() != null && !item.getListCliContrat().isEmpty()) {
            ComCon.Con.ListCliContrat f = new ComCon.Con.ListCliContrat();
            for (sss_cdr_ListCliContrat bnf : item.getListCliContrat()) {
                if (bnf != null) {
                    ComCon.Con.ListCliContrat.CliContrat n = new ComCon.Con.ListCliContrat.CliContrat();
                    n.setCapAutoriseEnt(BigDecimal.valueOf(bnf.getCapAutoriseEnt()));
                    n.setCodClient(bnf.getCodClient());
                    n.setValProcVersEnt(BigDecimal.valueOf(bnf.getValProcVersEnt()));

                    f.getCliContrat().add(n);
                }
            }
            if (!f.getCliContrat().isEmpty()) {
                con.getListCliContrat().add(f);
            }
        }//ListCliContrat fin


        //ListLinkContrat
        if (item.getListLinkContrat() != null && !item.getListLinkContrat().isEmpty()) {
            ComCon.Con.ListLinkContrat f = new ComCon.Con.ListLinkContrat();
            for (sss_cdr_ListLinkContrat bnf : item.getListLinkContrat()) {
                if (bnf != null) {
                    ComCon.Con.ListLinkContrat.LinkContrat n = new ComCon.Con.ListLinkContrat.LinkContrat();
                    n.setIdContAss(bnf.getIdContAss());
                    n.setTpConnex(bnf.getTpConnex());


                    f.getLinkContrat().add(n);
                }
            }
            if (!f.getLinkContrat().isEmpty()) {
                con.getListLinkContrat().add(f);
            }
        }//ListLinkContrat fin



        //ListConsort
        if (item.getListConsort() != null && !item.getListConsort().isEmpty()) {
            ComCon.Con.ListConsort f = new ComCon.Con.ListConsort();
            for (sss_cdr_ListConsort bnf : item.getListConsort()) {
                if (bnf != null) {
                    ComCon.Con.ListConsort.Consort n = new ComCon.Con.ListConsort.Consort();
                    n.setIdEnt(bnf.getIdEnt());
                    n.setRelEntPart(BigDecimal.valueOf(bnf.getRelEntPart()));


                    f.getConsort().add(n);
                }
            }
            if (!f.getConsort().isEmpty()) {
                con.getListConsort().add(f);
            }
        }//ListConsort fin




        //ListGarant
        if (item.getListGarant() != null && !item.getListGarant().isEmpty()) {
            ComCon.Con.ListGarant f = new ComCon.Con.ListGarant();
            for (sss_cdr_ListGarant bnf : item.getListGarant()) {
                if (bnf != null) {
                    ComCon.Con.ListGarant.Garant n = new ComCon.Con.ListGarant.Garant();
                    n.setIdGar(bnf.getIdGar());


                    f.getGarant().add(n);
                }
            }
            if (!f.getGarant().isEmpty()) {
                con.getListGarant().add(f);
            }
        }//ListGarant fin








        return con;
    }
}
