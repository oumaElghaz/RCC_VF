package ma.vivalis.BKAM_CDR_API1.services;

import generated.*;
import jakarta.transaction.Transactional;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_act;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_benef;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.entities.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_Adresse;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_DonneesIntPM;
import ma.vivalis.BKAM_CDR_API1.entities.util.sss_cdr_DonneesIntPP;
import ma.vivalis.BKAM_CDR_API1.generationXML.XmlGenerator;
import ma.vivalis.BKAM_CDR_API1.mapping.client_stat_mapp;
import ma.vivalis.BKAM_CDR_API1.repositories.sss_cdr_inter_client_stat_Repository;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class sss_cdr_client_stat_service_impl {
    private final sss_cdr_inter_client_stat_service_impl sss_cdr_inter_client_stat_service_impl;
    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    private final client_stat_mapp client_stat_mapp;


    public sss_cdr_client_stat_service_impl(sss_cdr_inter_client_stat_service_impl sssCdrInterClientStatServiceImpl, sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository, client_stat_mapp clientStatMapp) {
        sss_cdr_inter_client_stat_service_impl = sssCdrInterClientStatServiceImpl;
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
        client_stat_mapp = clientStatMapp;
    }


    public List<sss_cdr_client_stat> fct_get_sss_cdr_inter_client_stat(){
        sss_cdr_inter_client_stat_service_impl.sss_cdr_inter_client_stat_create();
        List<sss_cdr_inter_client_stat> clientStats_inter=sss_cdr_inter_client_stat_Repository.findAll();
        List<sss_cdr_client_stat> sss_cdr_client_stats=new ArrayList<>();
        for (sss_cdr_inter_client_stat cl : clientStats_inter) {
            sss_cdr_client_stat client_stat=  client_stat_mapp.fct_mapp_donnees_client(cl);
            sss_cdr_client_stats.add(client_stat);

        }
        return sss_cdr_client_stats;

    }

    public RCC mapToXml(List<sss_cdr_client_stat> sss_cdr_client_stats) throws DatatypeConfigurationException {
        RCC rcc = new RCC();
        ControlType ctr = new ControlType();
        ContentType contenu = new ContentType();
        ComEnt comEnt = new ComEnt();


        for (sss_cdr_client_stat sss_cdr_client_stat:sss_cdr_client_stats){

            for (sss_cdr_DonneesIntPP donneesIntPP : sss_cdr_client_stat.getDonneesInts_pp()) {
                //ComEnt comEnt = new ComEnt();
                ComEnt.DonneesEnt d_ent = new ComEnt.DonneesEnt();
                ComEnt.DonneesEnt.Address adr = new ComEnt.DonneesEnt.Address();
                ComEnt.DonneesEnt.LstActionnariat t = new ComEnt.DonneesEnt.LstActionnariat();
                ComEnt.DonneesEnt.LstActionnariat.Actionnariat b = new ComEnt.DonneesEnt.LstActionnariat.Actionnariat();
                ComEnt.DonneesEnt.LstBenEffect f = new ComEnt.DonneesEnt.LstBenEffect();
                ComEnt.DonneesEnt.LstBenEffect.BenEffect n = new ComEnt.DonneesEnt.LstBenEffect.BenEffect();

                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(sss_cdr_client_stat.getDtRefEnt());

                XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

                d_ent.setDtRefEnt(xmlCal);
                d_ent.setActionType(sss_cdr_client_stat.getActionType().toString());
                d_ent.setCodClient(sss_cdr_client_stat.getCodClient());
                d_ent.setNatClient(sss_cdr_client_stat.getNatClient());
                d_ent.setEntLieeEtab(sss_cdr_client_stat.getEntLieeEtab());
                d_ent.setCodAgEcon(sss_cdr_client_stat.getCodAgEcon());


                d_ent.setTpIdPrincipal(donneesIntPP.getTpIdPrincipal());
                d_ent.setIdPrincipal(donneesIntPP.getIdPrincipal());
                d_ent.setPrenom(donneesIntPP.getPrenom());
                d_ent.setNomFamille(donneesIntPP.getNomFamille());
                d_ent.setPaysDelivrance(donneesIntPP.getPaysDelivrance());

                GregorianCalendar calDtDelivrance = new GregorianCalendar();
                calDtDelivrance.setTime(donneesIntPP.getDtDelivrance());

                XMLGregorianCalendar xmlCalDtDelivrance = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtDelivrance);

                d_ent.setDtDelivrance(xmlCalDtDelivrance);

                GregorianCalendar calDtExpiration = new GregorianCalendar();
                calDtExpiration.setTime(donneesIntPP.getDtExpiration());

                XMLGregorianCalendar xmlCalDtExpiration = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtExpiration);
                d_ent.setDtExpiration(xmlCalDtExpiration);
                d_ent.setTypePPPro(donneesIntPP.getTypePPPro());
                d_ent.setRNAE(donneesIntPP.getRNAE());
                GregorianCalendar calDtNaissance = new GregorianCalendar();
                calDtNaissance.setTime(donneesIntPP.getDtNaissance());

                XMLGregorianCalendar xmlCalDtNaissance = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtNaissance);
                d_ent.setDtNaissance(xmlCalDtNaissance);
                d_ent.setCodLocalNaissance(donneesIntPP.getCodLocalNaissance());
                d_ent.setSexe(donneesIntPP.getSexe());
                d_ent.setNationalite(donneesIntPP.getNationalite());
                d_ent.setSitFamille(donneesIntPP.getSitFamille());
                d_ent.setCodCatProf(donneesIntPP.getCodCatProf());
                d_ent.setMenage(BigInteger.valueOf(donneesIntPP.getMenage()));
                d_ent.setQualAcadem(donneesIntPP.getQualAcadem());
                d_ent.setCatClient(donneesIntPP.getCatClient());


                d_ent.setRaisonSocial("");
                d_ent.setSigle("");
                d_ent.setFormJur("");
                d_ent.setCodTrib("");
                d_ent.setRegCommerce("");
                d_ent.setICE("");
                d_ent.setIdFiscal("");
                d_ent.setNumTaxeProf("");
                d_ent.setIdSpecifique("");
                d_ent.setCodLEI("");
                d_ent.setCodActPrinc("");
                d_ent.setCodActSec("");
                d_ent.setTailleEntrep("");
                d_ent.setGenre("");
                d_ent.setDtCreation(null);
                d_ent.setNatMod("");
                d_ent.setDtMod(null);
                d_ent.setFlagSuc(null);
                d_ent.setTpIdPrincSiege("");
                d_ent.setIdPrincSiege("");
                d_ent.setRaisonSocSiege("");
                d_ent.setGroupAppart("");

                for (sss_cdr_Adresse a : sss_cdr_client_stat.getAdresses()) {
                    //adresse
                    adr.setAdresse(a.getAdresse());
                    adr.setCodPostal(a.getCodPostal());
                    adr.setCodLocal(a.getCodLocal());
                    adr.setCodPays(a.getCodPays());
                    adr.setNumTeleph(a.getNumTeleph());
                    d_ent.getAddress().add(adr);
                }

                for (sss_cdr_client_act act : sss_cdr_client_stat.getActionnariats()) {
                    b.setNatActionnaire(act.getNatActionnaire());
                    b.setFormJurAct(act.getFormJurAct());
                    b.setCodTribunAct(act.getCodTribunAct());
                    b.setRegCommerAct(act.getRegCommerAct());
                    b.setICEAct(act.getICEAct());
                    b.setLEIAct(act.getLEIAct());
                    b.setPayResAct(act.getPayResAct());
                    b.setNomRaisonSocAct(act.getNomRaisonSocAct());
                    b.setQtpartCapSocAct(BigDecimal.valueOf(act.getQtpartCapSocAct()));
                    t.getActionnariat().add(b);
                    d_ent.getLstActionnariat().add(t);
                }
                for (sss_cdr_client_benef bnf : sss_cdr_client_stat.getBenEffects()) {
                    n.setTypIdBenEffect(bnf.getTypIdBenEffect());
                    n.setIdBenEffect(bnf.getIdBenEffect());
                    n.setNomBenEffect(bnf.getNomBenEffect());
                    n.setPreBenEffect(bnf.getPreBenEffect());
                    n.setNatBenEffect(bnf.getNatBenEffect());
                    f.getBenEffect().add(n);
                    d_ent.getLstBenEffect().add(f);

                }
                comEnt.getDonneesEnt().add(d_ent);
            }

            for (sss_cdr_DonneesIntPM donneesIntPM : sss_cdr_client_stat.getDonneesInts_pm()) {

                ComEnt.DonneesEnt d_ent = new ComEnt.DonneesEnt();
                ComEnt.DonneesEnt.Address adr = new ComEnt.DonneesEnt.Address();
                ComEnt.DonneesEnt.LstActionnariat t = new ComEnt.DonneesEnt.LstActionnariat();
                ComEnt.DonneesEnt.LstActionnariat.Actionnariat b = new ComEnt.DonneesEnt.LstActionnariat.Actionnariat();
                ComEnt.DonneesEnt.LstBenEffect f = new ComEnt.DonneesEnt.LstBenEffect();
                ComEnt.DonneesEnt.LstBenEffect.BenEffect n = new ComEnt.DonneesEnt.LstBenEffect.BenEffect();
                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(sss_cdr_client_stat.getDtRefEnt());

                XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

                d_ent.setDtRefEnt(xmlCal);
                d_ent.setActionType(sss_cdr_client_stat.getActionType().toString());
                d_ent.setCodClient(sss_cdr_client_stat.getCodClient());
                d_ent.setNatClient(sss_cdr_client_stat.getNatClient());
                d_ent.setEntLieeEtab(sss_cdr_client_stat.getEntLieeEtab());
                d_ent.setCodAgEcon(sss_cdr_client_stat.getCodAgEcon());


                d_ent.setTpIdPrincipal("");
                d_ent.setIdPrincipal("");
                d_ent.setPrenom("");
                d_ent.setNomFamille("");
                d_ent.setPaysDelivrance("");
                d_ent.setDtDelivrance(null);
                d_ent.setDtExpiration(null);
                d_ent.setTypePPPro("");
                d_ent.setRNAE("");
                d_ent.setDtNaissance(null);
                d_ent.setCodLocalNaissance("");
                d_ent.setSexe("");
                d_ent.setNationalite("");
                d_ent.setSitFamille("");
                d_ent.setCodCatProf("");
                d_ent.setMenage(null);
                d_ent.setQualAcadem("");
                d_ent.setCatClient("");


                d_ent.setRaisonSocial(donneesIntPM.getRaisonSocial());
                d_ent.setSigle(donneesIntPM.getSigle());
                d_ent.setFormJur(donneesIntPM.getFormJur());
                d_ent.setCodTrib(donneesIntPM.getCodTrib());
                d_ent.setRegCommerce(donneesIntPM.getRegCommerce());
                d_ent.setICE(donneesIntPM.getICE());
                d_ent.setIdFiscal(donneesIntPM.getIdFiscal());
                d_ent.setNumTaxeProf(donneesIntPM.getNumTaxeProf());
                d_ent.setIdSpecifique(donneesIntPM.getIdSpecifique());
                d_ent.setCodLEI(donneesIntPM.getCodLEI());
                d_ent.setCodActPrinc(donneesIntPM.getCodActPrinc());
                d_ent.setCodActSec(donneesIntPM.getCodActSec());
                d_ent.setTailleEntrep(donneesIntPM.getTailleEntrep());
                d_ent.setGenre(donneesIntPM.getGenre());
                GregorianCalendar calDtCreation = new GregorianCalendar();
                calDtCreation.setTime(donneesIntPM.getDtCreation());

                XMLGregorianCalendar xmlCalDtCreation = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtCreation);
                d_ent.setDtCreation(xmlCalDtCreation);
                d_ent.setNatMod(donneesIntPM.getNatMod());
                GregorianCalendar calDtMod = new GregorianCalendar();
                calDtMod.setTime(donneesIntPM.getDtMod());

                XMLGregorianCalendar xmlCalDtMod = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtMod);
                d_ent.setDtMod(xmlCalDtMod);
                d_ent.setFlagSuc(donneesIntPM.getFlagSuc());
                d_ent.setTpIdPrincSiege(donneesIntPM.getTpIdPrincSiege());
                d_ent.setIdPrincSiege(donneesIntPM.getIdPrincSiege());
                d_ent.setRaisonSocSiege(donneesIntPM.getRaisonSocSiege());
                d_ent.setGroupAppart(donneesIntPM.getGroupAppart());

                for (sss_cdr_Adresse a : sss_cdr_client_stat.getAdresses()) {
                    //adresse
                    adr.setAdresse(a.getAdresse());
                    adr.setCodPostal(a.getCodPostal());
                    adr.setCodLocal(a.getCodLocal());
                    adr.setCodPays(a.getCodPays());
                    adr.setNumTeleph(a.getNumTeleph());
                    d_ent.getAddress().add(adr);
                }

                for (sss_cdr_client_act act : sss_cdr_client_stat.getActionnariats()) {
                    b.setNatActionnaire(act.getNatActionnaire());
                    b.setFormJurAct(act.getFormJurAct());
                    b.setCodTribunAct(act.getCodTribunAct());
                    b.setRegCommerAct(act.getRegCommerAct());
                    b.setICEAct(act.getICEAct());
                    b.setLEIAct(act.getLEIAct());
                    b.setPayResAct(act.getPayResAct());
                    b.setNomRaisonSocAct(act.getNomRaisonSocAct());
                    b.setQtpartCapSocAct(BigDecimal.valueOf(act.getQtpartCapSocAct()));
                    t.getActionnariat().add(b);
                    d_ent.getLstActionnariat().add(t);
                }
                for (sss_cdr_client_benef bnf : sss_cdr_client_stat.getBenEffects()) {
                    n.setTypIdBenEffect(bnf.getTypIdBenEffect());
                    n.setIdBenEffect(bnf.getIdBenEffect());
                    n.setNomBenEffect(bnf.getNomBenEffect());
                    n.setPreBenEffect(bnf.getPreBenEffect());
                    n.setNatBenEffect(bnf.getNatBenEffect());
                    f.getBenEffect().add(n);
                    d_ent.getLstBenEffect().add(f);

                }
                comEnt.getDonneesEnt().add(d_ent);

            }


        }







        sss_cdr_client_stat h=sss_cdr_client_stats.get(0);
        //control
        ctr.setEntObserv(h.getEntObserv());
        ctr.setEntDeclar(h.getEntDeclar());
        //ctr.setIdDest(h.getId_lot());

        GregorianCalendar calDtCre = new GregorianCalendar();
        calDtCre.setTime(h.getDateExtraction());
        XMLGregorianCalendar xmlCalDtCre = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtCre);
        ctr.setDtCreation(xmlCalDtCre);

        //contenu
        contenu.setComEnt(comEnt);

        //rcc
        rcc.setControle(ctr);
        rcc.setContenu(contenu);
        rcc.setVersion("1.0");



        return rcc;


    }

    public void generer_xml_clientStat() throws DatatypeConfigurationException {
        List<sss_cdr_client_stat> sss_cdr_client_stats=fct_get_sss_cdr_inter_client_stat();
        RCC rcc=mapToXml(sss_cdr_client_stats);
        XmlGenerator.generateXml(rcc, "C:/Users/PC/Documents/vivalis/BKAM_CDR/BKAM_CDR_API1/tmp/rcc_clients_stat.xml");


    }



}
