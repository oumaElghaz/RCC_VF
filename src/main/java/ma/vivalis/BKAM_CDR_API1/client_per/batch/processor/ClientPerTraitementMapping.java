package ma.vivalis.BKAM_CDR_API1.client_per.batch.processor;

import generated.ComEntPer;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ClientPerTraitementMapping implements ItemProcessor<sss_cdr_inter_client_per, ComEntPer> {
    private static final Logger log = LoggerFactory.getLogger(ClientPerTraitementMapping.class);
    private final MappingLoader mappingLoader;

    public ClientPerTraitementMapping(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }

    @Override
    public @Nullable ComEntPer process(sss_cdr_inter_client_per item) throws Exception {
        sss_cdr_client_per client=mappingTraitement(item);
        ComEntPer comEntPer = mapClientToXmlElement(client);
        return comEntPer;
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

    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    private sss_cdr_client_per mappingTraitement(sss_cdr_inter_client_per item) {
        sss_cdr_client_per cli_final=sss_cdr_client_per.builder()
                .codClient(item.getCodClient())
                .id_lot(item.getId_lot())
                .dateExtraction(item.getDateExtraction())
                //.entObserv (item.getEntObserv())
                //.entDeclar (item.getEntDeclar())
                //.dtCreation (item.getDtCreation())
                //.idDest(item.getIdDest())
                .dtRef(item.getDtRef())
                .actionType(item.getActionType())
                .watchList (item.getWatchList())
                //.etatAvProcJud(mappingLoader.map("EtatJud", item.getEtatAvProcJud()))
                .etatAvProcJud("0")
                .dtEtatAvProcJud(item.getDtEtatAvProcJud())
                .revenu (item.getRevenu())
                .dtRevenu(item.getDtRevenu())
                .annExercCompt(item.getAnnExercCompt())
                .capSocial(item.getCapSocial())
                .capPropres(item.getCapPropres())
                .actImmobilises(item.getActImmobilises())
                .totBilan(item.getTotBilan())
                .chiffreAffaire(item.getChiffreAffaire())
                .dtAffairesAnExp(item.getDtAffairesAnExp())
                .detteBancLMT(item.getDetteBancLMT())
                .detteBancCT (item.getDetteBancCT())
                .passifCirculant(item.getPassifCirculant())
                .dettesFourn (item.getDettesFourn())
                .compteCourAssoc(item.getCompteCourAssoc())
                .tresoreriePassif(item.getTresoreriePassif())
                .actifCirculant(item.getActifCirculant())
                .créancesClients(item.getCréancesClients())
                .tresorerieActif(item.getTresorerieActif())
                .caisse (item.getCaisse())
                .achatsRevendus(item.getAchatsRevendus())
                .achatsConsom(item.getAchatsConsom())
                .chargesExternes(item.getChargesExternes())
                .chargesInterets(item.getChargesInterets())
                .resultatNet(item.getResultatNet())
                .tpResultat(item.getTpResultat())
                .PDCont (item.getPDCont())
                .dtEvalRisques(item.getDtEvalRisques())
                .modIRBCont(item.getModIRBCont())
                .coteCli (item.getCoteCli())
                .dateCoteCli(item.getDateCoteCli())
                .modCoteCli (item.getModCoteCli())
                .notAgence (item.getNotAgence())
                .NomAgence (item.getNomAgence())
                .dtnotAgc(item.getDtnotAgc())

                .build();

        return cli_final;
    }



}
