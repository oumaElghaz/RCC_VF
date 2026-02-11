package ma.vivalis.BKAM_CDR_API1.services;

import generated.*;
import jakarta.transaction.Transactional;
import ma.vivalis.BKAM_CDR_API1.entities.Enums.Flag_envoi;
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
import java.util.Objects;

@Service
@Transactional
public class sss_cdr_client_stat_service_impl {
    private final sss_cdr_inter_client_stat_service_impl sss_cdr_inter_client_stat_service_impl;
    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    private final client_stat_mapp client_stat_mapp;
    private final LotSequenceService_impl lotSequenceService_impl;


    public sss_cdr_client_stat_service_impl(sss_cdr_inter_client_stat_service_impl sssCdrInterClientStatServiceImpl, sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository, client_stat_mapp clientStatMapp, LotSequenceService_impl lotSequenceServiceImpl) {
        sss_cdr_inter_client_stat_service_impl = sssCdrInterClientStatServiceImpl;
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
        client_stat_mapp = clientStatMapp;
        lotSequenceService_impl = lotSequenceServiceImpl;
    }


    public List<sss_cdr_client_stat> fct_get_sss_cdr_inter_client_stat(){
        sss_cdr_inter_client_stat_service_impl.sss_cdr_inter_client_stat_create();
        List<sss_cdr_inter_client_stat> clientStats_inter=sss_cdr_inter_client_stat_Repository.findAll();
        List<sss_cdr_client_stat> sss_cdr_client_stats=new ArrayList<>();
        for (sss_cdr_inter_client_stat cl : clientStats_inter) {
            sss_cdr_client_stat client_stat=  client_stat_mapp.fct_mapp_donnees_client(cl);
            if(client_stat.getFlag_envoi() == Flag_envoi.AUTOMATIQUE){
            sss_cdr_client_stats.add(client_stat);
            }

        }
        return sss_cdr_client_stats;

    }

    public RCC mapToXml(List<sss_cdr_client_stat> sss_cdr_client_stats) throws DatatypeConfigurationException {
        RCC rcc = new RCC();
        ControlType ctr = new ControlType();
        ContentType contenu = new ContentType();
        ComEnt comEnt = new ComEnt();


        for (sss_cdr_client_stat sss_cdr_client_stat:sss_cdr_client_stats){




                ComEnt.DonneesEnt d_ent = new ComEnt.DonneesEnt();
                ComEnt.DonneesEnt.Address adr = new ComEnt.DonneesEnt.Address();
                ComEnt.DonneesEnt.LstActionnariat t = new ComEnt.DonneesEnt.LstActionnariat();
                ComEnt.DonneesEnt.LstBenEffect f = new ComEnt.DonneesEnt.LstBenEffect();


                GregorianCalendar cal = new GregorianCalendar();
                cal.setTime(sss_cdr_client_stat.getDtRefEnt());

                XMLGregorianCalendar xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

                d_ent.setDtRefEnt(xmlCal);
                d_ent.setActionType(sss_cdr_client_stat.getActionType().toString());
                d_ent.setCodClient(sss_cdr_client_stat.getCodClient());
                d_ent.setNatClient(sss_cdr_client_stat.getNatClient());
                d_ent.setEntLieeEtab(sss_cdr_client_stat.getEntLieeEtab());
                d_ent.setCodAgEcon(sss_cdr_client_stat.getCodAgEcon());


                if(sss_cdr_client_stat.getDonneesInts_pp() != null && !sss_cdr_client_stat.getDonneesInts_pp().isEmpty()){
                    sss_cdr_DonneesIntPP donneesIntPP1=sss_cdr_client_stat.getDonneesInts_pp().get(0);
                    d_ent.setTpIdPrincipal(donneesIntPP1.getTpIdPrincipal());
                    d_ent.setIdPrincipal(donneesIntPP1.getIdPrincipal());
                    d_ent.setPrenom(donneesIntPP1.getPrenom());
                    d_ent.setNomFamille(donneesIntPP1.getNomFamille());
                    d_ent.setPaysDelivrance(donneesIntPP1.getPaysDelivrance());

                    GregorianCalendar calDtDelivrance = new GregorianCalendar();
                    calDtDelivrance.setTime(donneesIntPP1.getDtDelivrance());

                    XMLGregorianCalendar xmlCalDtDelivrance = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtDelivrance);

                    d_ent.setDtDelivrance(xmlCalDtDelivrance);

                    GregorianCalendar calDtExpiration = new GregorianCalendar();
                    calDtExpiration.setTime(donneesIntPP1.getDtExpiration());

                    XMLGregorianCalendar xmlCalDtExpiration = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtExpiration);
                    d_ent.setDtExpiration(xmlCalDtExpiration);
                    d_ent.setTypePPPro(donneesIntPP1.getTypePPPro());
                    d_ent.setRNAE(donneesIntPP1.getRNAE());
                    GregorianCalendar calDtNaissance = new GregorianCalendar();
                    calDtNaissance.setTime(donneesIntPP1.getDtNaissance());

                    XMLGregorianCalendar xmlCalDtNaissance = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtNaissance);
                    d_ent.setDtNaissance(xmlCalDtNaissance);
                    d_ent.setCodLocalNaissance(donneesIntPP1.getCodLocalNaissance());
                    d_ent.setSexe(donneesIntPP1.getSexe());
                    d_ent.setNationalite(donneesIntPP1.getNationalite());
                    d_ent.setSitFamille(donneesIntPP1.getSitFamille());
                    d_ent.setCodCatProf(donneesIntPP1.getCodCatProf());
                    d_ent.setMenage(BigInteger.valueOf(donneesIntPP1.getMenage()));
                    d_ent.setQualAcadem(donneesIntPP1.getQualAcadem());
                    d_ent.setCatClient(donneesIntPP1.getCatClient());

                }



            //if 2
            if (sss_cdr_client_stat.getDonneesInts_pm() != null && !sss_cdr_client_stat.getDonneesInts_pm().isEmpty()){
                sss_cdr_DonneesIntPM donneesIntPM1 = sss_cdr_client_stat.getDonneesInts_pm().get(0);
                d_ent.setRaisonSocial(donneesIntPM1.getRaisonSocial());
                d_ent.setSigle(donneesIntPM1.getSigle());
                d_ent.setFormJur(donneesIntPM1.getFormJur());
                d_ent.setCodTrib(donneesIntPM1.getCodTrib());
                d_ent.setRegCommerce(donneesIntPM1.getRegCommerce());
                d_ent.setICE(donneesIntPM1.getICE());
                d_ent.setIdFiscal(donneesIntPM1.getIdFiscal());
                d_ent.setNumTaxeProf(donneesIntPM1.getNumTaxeProf());
                d_ent.setIdSpecifique(donneesIntPM1.getIdSpecifique());
                d_ent.setCodLEI(donneesIntPM1.getCodLEI());
                d_ent.setCodActPrinc(donneesIntPM1.getCodActPrinc());
                d_ent.setCodActSec(donneesIntPM1.getCodActSec());
                d_ent.setTailleEntrep(donneesIntPM1.getTailleEntrep());
                d_ent.setGenre(donneesIntPM1.getGenre());
                GregorianCalendar calDtCreation = new GregorianCalendar();
                calDtCreation.setTime(donneesIntPM1.getDtCreation());

                XMLGregorianCalendar xmlCalDtCreation = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtCreation);
                d_ent.setDtCreation(xmlCalDtCreation);
                d_ent.setNatMod(donneesIntPM1.getNatMod());
                GregorianCalendar calDtMod = new GregorianCalendar();
                calDtMod.setTime(donneesIntPM1.getDtMod());

                XMLGregorianCalendar xmlCalDtMod = DatatypeFactory.newInstance().newXMLGregorianCalendar(calDtMod);
                d_ent.setDtMod(xmlCalDtMod);
                d_ent.setFlagSuc(donneesIntPM1.getFlagSuc());
                d_ent.setTpIdPrincSiege(donneesIntPM1.getTpIdPrincSiege());
                d_ent.setIdPrincSiege(donneesIntPM1.getIdPrincSiege());
                d_ent.setRaisonSocSiege(donneesIntPM1.getRaisonSocSiege());
                d_ent.setGroupAppart(donneesIntPM1.getGroupAppart());

            }


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

                    ComEnt.DonneesEnt.LstActionnariat.Actionnariat b = new ComEnt.DonneesEnt.LstActionnariat.Actionnariat();
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

                }
                d_ent.getLstActionnariat().add(t);
                for (sss_cdr_client_benef bnf : sss_cdr_client_stat.getBenEffects()) {
                    ComEnt.DonneesEnt.LstBenEffect.BenEffect n = new ComEnt.DonneesEnt.LstBenEffect.BenEffect();
                    n.setTypIdBenEffect(bnf.getTypIdBenEffect());
                    n.setIdBenEffect(bnf.getIdBenEffect());
                    n.setNomBenEffect(bnf.getNomBenEffect());
                    n.setPreBenEffect(bnf.getPreBenEffect());
                    n.setNatBenEffect(bnf.getNatBenEffect());
                    f.getBenEffect().add(n);


                }
                d_ent.getLstBenEffect().add(f);
                comEnt.getDonneesEnt().add(d_ent);





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
        List<sss_cdr_client_stat> sss_cdr_client_stats_dernierLot=new ArrayList<>();
        //recuperer le max des lots pour generation xml
        Integer maxLot=lotSequenceService_impl.getMax();
        for (sss_cdr_client_stat clt: sss_cdr_client_stats){

            if(Objects.equals(clt.getId_lot(), maxLot)){
                sss_cdr_client_stats_dernierLot.add(clt);
            }
        }
        //envoyer que les cliens avec max lot
        RCC rcc=mapToXml(sss_cdr_client_stats_dernierLot);
        XmlGenerator.generateXml(rcc, "C:/Users/PC/Documents/vivalis/BKAM_CDR/BKAM_CDR_API1/tmp/rcc_clients_stat.xml");


    }



}
