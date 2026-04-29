package ma.vivalis.BKAM_CDR_API1.contrat.batch.processor;

import generated.ComCon;
import ma.vivalis.BKAM_CDR_API1.common.CleanDate;
import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.sss_cdr_inter_contrat_stat;
import ma.vivalis.BKAM_CDR_API1.contrat.model.util.*;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class ContratTraitementMappingProcessor  implements ItemProcessor<sss_cdr_inter_contrat_stat, ComCon.Con> {
    private static final Logger log = LoggerFactory.getLogger(ContratTraitementMappingProcessor.class);
    private final MappingLoader mappingLoader;
    private final CleanDate cleanDate;

    public ContratTraitementMappingProcessor(MappingLoader mappingLoader, CleanDate cleanDate) {
        this.mappingLoader = mappingLoader;
        this.cleanDate = cleanDate;
    }

    @Override
    public ComCon.@Nullable Con process(sss_cdr_inter_contrat_stat item) throws Exception {
        sss_cdr_contrat_stat contrat=mappingTraitement(item);
        ComCon.Con con = mapContratToXmlElement(contrat);
        return con;
    }
    private BigDecimal toBigDecimal(Number value) {
        return value != null ? BigDecimal.valueOf(value.doubleValue()) : null;
    }

    private Boolean toBoolean(String value) {
        return value != null ? Boolean.valueOf(value) : null;
    }

    private XMLGregorianCalendar toXmlDate(Date date) throws DatatypeConfigurationException {
        return date != null ? cleanDate.convertDateToXml(date) : null;
    }

    private ComCon.Con mapContratToXmlElement(sss_cdr_contrat_stat item) throws DatatypeConfigurationException {
        ComCon.Con con= new ComCon.Con();
        con.setIdCont(item.getIdCont());
        con.setActionType(item.getActionType() != null ? String.valueOf(item.getActionType()) : null);
        con.setDtRefCont(toXmlDate(item.getDtRefCont()));
        con.setGuichetAgence(item.getGuichetAgence());
        con.setCodLocAgence(item.getCodLocAgence());
        con.setTpCont(item.getTpCont());
        con.setTpCred(item.getTpCred());
        con.setDtlTpCred(item.getDtlTpCred() != null ? String.valueOf(item.getDtlTpCred()) : null);
        con.setCreCoFin(toBoolean(item.getCreCoFin()));
        con.setCreConsor(toBoolean(item.getCreConsor()));
        con.setObjCred(item.getObjCred());
        con.setObjCredDetail(item.getObjCredDetail());
        con.setMonnaie(item.getMonnaie());
        con.setMontIniAccord(toBigDecimal(item.getMontIniAccord()));
        con.setMontCreCoFin(toBigDecimal(item.getMontCreCoFin()));
        con.setTxChange(toBigDecimal(item.getTxChange()));
        con.setDtContCredt(toXmlDate(item.getDtContCredt()));
        con.setDtDebloCred(toXmlDate(item.getDtDebloCred()));
        con.setDtClotIni(toXmlDate(item.getDtClotIni()));
        con.setDtClotCred(toXmlDate(item.getDtClotCred()));
        con.setMotClotCont(item.getMotClotCont());
        con.setFlagDiff(toBoolean(item.getFlagDiff()));
        con.setDtModCondCred(toXmlDate(item.getDtModCondCred()));
        con.setMotModCondCred(item.getMotModCondCred());
        con.setDtDebPerGraCap(toXmlDate(item.getDtDebPerGraCap()));
        con.setDtFinPerGraCap(toXmlDate(item.getDtFinPerGraCap()));
        con.setModPaiement(item.getModPaiement());
        con.setTpEche(item.getTpEche());
        con.setFxEche(toBoolean(item.getFxEche()));
        con.setNombreTotEche(toBigDecimal(item.getNombreTotEche()));
        con.setPeriodEche(item.getPeriodEche());
        con.setMtEche(toBigDecimal(item.getMtEche()));
        con.setDt1Eche(toXmlDate(item.getDt1Eche()));
        con.setMont1Eche(toBigDecimal(item.getMont1Eche()));
        con.setMont1EcheDiv(toBigDecimal(item.getMont1EcheDiv()));
        con.setFlagTxInt(item.getFlagTxInt());
        con.setTxRef(item.getTxRef());
        con.setTxAnnuelPourc(toBigDecimal(item.getTxAnnuelPourc()));
        con.setTxTAEG(toBigDecimal(item.getTxTAEG()));
        con.setHmRibh(toBigDecimal(item.getHmRibh()));
        con.setCmFxWkl(toBigDecimal(item.getCmFxWkl()));
        con.setFreqMiseJourTxInt(item.getFreqMiseJourTxInt());
        con.setLTVIni(toBigDecimal(item.getLTVIni()));
        con.setTpSecuritization(toBoolean(item.getTpSecuritization()));
        con.setExisGarant(toBoolean(item.getExisGarant()));
        con.setMntGarant(toBigDecimal(item.getMntGarant()));
        //ListCliContrat
        if (item.getListCliContrat() != null && !item.getListCliContrat().isEmpty()) {
            ComCon.Con.ListCliContrat f = new ComCon.Con.ListCliContrat();
            for (sss_cdr_ListCliContrat bnf : item.getListCliContrat()) {
                if (bnf != null) {
                    ComCon.Con.ListCliContrat.CliContrat n = new ComCon.Con.ListCliContrat.CliContrat();
                    n.setCapAutoriseEnt(toBigDecimal(bnf.getCapAutoriseEnt()));
                    n.setCodClient(bnf.getCodClient());
                    n.setValProcVersEnt(toBigDecimal(bnf.getValProcVersEnt()));

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






    private sss_cdr_contrat_stat mappingTraitement(sss_cdr_inter_contrat_stat item){

        sss_cdr_contrat_stat inter =sss_cdr_contrat_stat.builder()
                .idCont(item.getIdCont())
                .id_lot(item.getId_lot())
                .dateExtraction(item.getDateExtraction())
                //.entObserv(item.getEntObserv())
                //.entDeclar(item.getEntDeclar())
                //.idDest(item.getIdDest())

                . actionType(item.getActionType())
                .dtRefCont(item.getDtRefCont())
                .guichetAgence(item.getGuichetAgence())
                .codLocAgence(mappingLoader.map("T_CNC",item.getCodLocAgence()))
                .tpCont("1")
                .tpCred(item.getTpCred())
                .dtlTpCred(item.getDtlTpCred())
                .creCoFin("N")
                .creConsor("N")
                .objCred(item.getObjCred())
                .objCredDetail(item.getObjCredDetail())
                .monnaie("MAD")
                .montIniAccord(item.getMontIniAccord())
                .montCreCoFin(item.getMontIniAccord())
                .txChange(item.getTxChange())
                .dtContCredt(item.getDtContCredt())
                .dtDebloCred(item.getDtDebloCred())
                .dtClotIni(item.getDtClotIni())
                .dtClotCred(item.getDtClotCred())
                .motClotCont(mappingLoader.map("T_MCC",item.getMotClotCont()))
                //.flagDiff(item.getFlagDiff())
                .dtModCondCred(item.getDtModCondCred())
                .motModCondCred(item.getMotModCondCred())
                .dtDebPerGraCap(item.getDtDebPerGraCap())
                .dtFinPerGraCap(item.getDtFinPerGraCap())
                .modPaiement(mappingLoader.map("T_MPA",item.getModPaiement()))
                .tpEche(item.getTpEche())
                .fxEche(item.getFxEche())
                .nombreTotEche(item.getNombreTotEche())
                .periodEche(item.getPeriodEche())
                .mtEche(item.getMtEche())
                .dt1Eche(item.getDt1Eche())
                .mont1Eche(item.getMont1Eche())
                .mont1EcheDiv(item.getMont1EcheDiv())
                .flagTxInt(item.getFlagTxInt())
                .txRef(item.getTxRef())
                .txAnnuelPourc(item.getTxAnnuelPourc())
                .txTAEG(item.getTxTAEG())
                .hmRibh(item.getHmRibh())
                .cmFxWkl(item.getCmFxWkl())
                .freqMiseJourTxInt(item.getFreqMiseJourTxInt())
                .LTVIni(item.getLTVIni())
                .tpSecuritization(item.getTpSecuritization())
                .exisGarant(item.getExisGarant())
                .mntGarant(item.getMntGarant())
                .build();
        if("0".equalsIgnoreCase(item.getFlagDiff())){
            inter.setFlagDiff("N");
        }
        else  {
            inter.setFlagDiff("O");
        }



        // ── ListCliContrat ──
        if (item.getListCliContrat() != null && !item.getListCliContrat().isEmpty()) {
            inter.setListCliContrat(
                    item.getListCliContrat().stream().map(cliCon -> {
                        sss_cdr_ListCliContrat a = sss_cdr_ListCliContrat.builder()
                                .codClient(cliCon.getCodClient())
                                .capAutoriseEnt(cliCon.getCapAutoriseEnt())
                                .valProcVersEnt(cliCon.getValProcVersEnt())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  // ✅ toSet() au lieu de toList()
        }


        // ── ListLinkContrat ──
        if (item.getListLinkContrat() != null && !item.getListLinkContrat().isEmpty()) {
            inter.setListLinkContrat(
                    item.getListLinkContrat().stream().map(linkCon -> {
                        sss_cdr_ListLinkContrat a = sss_cdr_ListLinkContrat.builder()
                                .idContAss(linkCon.getIdContAss())
                                .tpConnex(linkCon.getTpConnex())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  // ✅ toSet() au lieu de toList()
        }


        // ── ListConsort ──
        if (item.getListConsort() != null && !item.getListConsort().isEmpty()) {
            inter.setListConsort(
                    item.getListConsort().stream().map(listConsrt -> {
                        sss_cdr_ListConsort a = sss_cdr_ListConsort.builder()
                                .idEnt(listConsrt.getIdEnt())
                                .relEntPart(listConsrt.getRelEntPart())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet()));  // ✅ toSet() au lieu de toList()
        }


        //ListGarant
        if (item.getListGarant() != null && !item.getListGarant().isEmpty()) {
            inter.setListGarant(
                    item.getListGarant().stream().map(listGar -> {
                        sss_cdr_ListGarant a = sss_cdr_ListGarant.builder()
                                .idGar(listGar.getIdGar())
                                .build();
                        a.setContrat(inter);  // ✅ Lier au parent
                        return a;
                    }).collect(Collectors.toSet())); // ✅ toSet() au lieu de toList()
        }
return inter;

    }
}
