package ma.vivalis.BKAM_CDR_API1.client_per.batch.processor;

import generated.ComEntPer;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class ClientPerJaxbProcessor implements ItemProcessor<sss_cdr_client_per, ComEntPer> {
    @Override
    public @Nullable ComEntPer process(sss_cdr_client_per item) throws Exception {
        ComEntPer com=mapClientToXmlElement(item);
        return com;
    }

    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }
    private ComEntPer mapClientToXmlElement(sss_cdr_client_per client) throws DatatypeConfigurationException {
        ComEntPer comEntPer = new ComEntPer();
        comEntPer.setDtRef(convertDateToXml(client.getDtRef()));


        ComEntPer.EntPer t = new ComEntPer.EntPer();
        t.setCodClient(client.getCodClient());
        t.setActionType(String.valueOf(client.getActionType()));
        t.setWatchList(Boolean.valueOf(client.getWatchList()));
        t.setEtatAvProcJud(client.getEtatAvProcJud());
        t.setDtEtatAvProcJud(convertDateToXml(client.getDtEtatAvProcJud()));
        t.setRevenu(BigDecimal.valueOf(client.getRevenu()));
        t.setDtRevenu(convertDateToXml(client.getDtRevenu()));

        ComEntPer.EntPer.DataCompta f=new ComEntPer.EntPer.DataCompta();
        f.setAnnExercCompt (BigInteger.valueOf(client.getAnnExercCompt()));
        f.setCapSocial (BigDecimal.valueOf(client.getCapSocial()));
        f.setCapPropres (BigDecimal.valueOf(client.getCapPropres()));
        f.setActImmobilises (BigDecimal.valueOf(client.getActImmobilises()));
        f.setTotBilan (BigDecimal.valueOf(client.getTotBilan()));
        f.setChiffreAffaire (BigDecimal.valueOf(client.getChiffreAffaire()));
        f.setDtAffairesAnExp (BigDecimal.valueOf(client.getDtAffairesAnExp()));
        f.setDetteBancLMT (BigDecimal.valueOf(client.getDetteBancLMT()));
        f.setDetteBancCT (BigDecimal.valueOf(client.getDetteBancCT()));
        f.setPassifCirculant (BigDecimal.valueOf(client.getPassifCirculant()));
        f.setDettesFourn (BigDecimal.valueOf(client.getDettesFourn()));
        f.setTresoreriePassif (BigDecimal.valueOf(client.getTresoreriePassif()));
        f.setActifCirculant (BigDecimal.valueOf(client.getActifCirculant()));
        f.setCréancesClients (BigDecimal.valueOf(client.getCréancesClients()));
        f.setTresorerieActif (BigDecimal.valueOf(client.getTresorerieActif()));
        f.setCaisse (BigDecimal.valueOf(client.getCaisse()));
        f.setAchatsRevendus (BigDecimal.valueOf(client.getAchatsRevendus()));
        f.setAchatsConsom (BigDecimal.valueOf(client.getAchatsConsom()));
        f.setChargesExternes(BigDecimal.valueOf(client.getChargesExternes()));
        f.setChargesInterets (BigDecimal.valueOf(client.getChargesInterets()));
        f.setResultatNet(BigDecimal.valueOf(client.getResultatNet()));
        f.setTpResultat(client.getTpResultat());


        ComEntPer.EntPer.DataRiskCli o = new ComEntPer.EntPer.DataRiskCli();
        ComEntPer.EntPer.DataRiskCli.RiskCli n=new ComEntPer.EntPer.DataRiskCli.RiskCli();

        n.setPDCont (BigDecimal.valueOf(client.getPDCont()));
        n.setDtEvalRisques (convertDateToXml(client.getDtEvalRisques()));
        n.setModIRBCont(client.getModIRBCont());
        n.setCoteCli (client.getCoteCli());
        n.setDateCoteCli(convertDateToXml(client.getDateCoteCli()));
        n.setModCoteCli (client.getModCoteCli());
        n.setNotAgence (client.getNotAgence());
        n.setNomAgence (client.getNomAgence());
        n.setDtnotAgc(convertDateToXml(client.getDtnotAgc()));
        o.getRiskCli().add(n);
        t.getDataRiskCli().add(o);
        t.getDataCompta().add(f);

        comEntPer.getEntPer().add(t);


        return comEntPer;


    }
}
