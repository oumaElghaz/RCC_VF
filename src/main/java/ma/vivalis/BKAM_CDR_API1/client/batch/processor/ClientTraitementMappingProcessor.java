package ma.vivalis.BKAM_CDR_API1.client.batch.processor;

import generated.ComEnt;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
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
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ClientTraitementMappingProcessor implements ItemProcessor<sss_cdr_inter_client_stat, ComEnt.DonneesEnt> {

    private static final Logger log = LoggerFactory.getLogger(ClientMappingProcessor.class);
    private final MappingLoader mappingLoader;

   

    public ClientTraitementMappingProcessor(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
        
    }

  



    @Override
    public ComEnt.@Nullable DonneesEnt process(sss_cdr_inter_client_stat item) throws Exception {
        sss_cdr_client_stat client=mappingTraitement(item);
        ComEnt.DonneesEnt d_ent = mapClientToXmlElement(client);
        return d_ent;
    }

    private ComEnt.DonneesEnt mapClientToXmlElement(sss_cdr_client_stat client) throws DatatypeConfigurationException {
        if (client == null) {
            throw new IllegalArgumentException("Client null !");
        }

        ComEnt.DonneesEnt d_ent = new ComEnt.DonneesEnt();

        // Infos de base
        d_ent.setDtRefEnt(convertDateToXml(client.getDtRefEnt()));

        if (client.getActionType() != null) {
            d_ent.setActionType(client.getActionType().toString());
        } else {
            d_ent.setActionType("");
        }

        d_ent.setCodClient(client.getCodClient());
        d_ent.setNatClient(client.getNatClient());
        d_ent.setEntLieeEtab(client.getEntLieeEtab());
        d_ent.setCodAgEcon(client.getCodAgEcon());

        // Données Personne Physique
        if (client.getDonneesInt_pp() != null) {
            mapDonneesIntPP(d_ent, client.getDonneesInt_pp());
        }

        // Données Personne Morale
        if (client.getDonneesInt_pm() != null) {
            mapDonneesIntPM(d_ent, client.getDonneesInt_pm());
        }

        // Adresses
        if (client.getAdresse() != null) {
            sss_cdr_Adresse a = client.getAdresse();

            ComEnt.DonneesEnt.Address adr = new ComEnt.DonneesEnt.Address();
            adr.setAdresse(a.getAdresse());
            adr.setCodPostal(a.getCodPostal());
            adr.setCodLocal(a.getCodLocal());
            adr.setCodPays(a.getCodPays());
            adr.setNumTeleph(a.getNumTeleph());
            d_ent.getAddress().add(adr);


        }

        // Actionnariats
        if (client.getActionnariats() != null && !client.getActionnariats().isEmpty()) {
            ComEnt.DonneesEnt.LstActionnariat t = new ComEnt.DonneesEnt.LstActionnariat();
            for (sss_cdr_client_act act : client.getActionnariats()) {
                if (act != null) {
                    ComEnt.DonneesEnt.LstActionnariat.Actionnariat b = new ComEnt.DonneesEnt.LstActionnariat.Actionnariat();
                    b.setNatActionnaire(act.getNatActionnaire());
                    b.setFormJurAct(act.getFormJurAct());
                    b.setCodTribunAct(act.getCodTribunAct());
                    b.setRegCommerAct(act.getRegCommerAct());
                    b.setICEAct(act.getICEAct());
                    b.setLEIAct(act.getLEIAct());
                    b.setPayResAct(act.getPayResAct());
                    b.setNomRaisonSocAct(act.getNomRaisonSocAct());
                    if (act.getQtpartCapSocAct() != null) {
                        b.setQtpartCapSocAct(BigDecimal.valueOf(act.getQtpartCapSocAct()));
                    }
                    t.getActionnariat().add(b);
                }
            }
            if (!t.getActionnariat().isEmpty()) {
                d_ent.getLstActionnariat().add(t);
            }
        }

        // Bénéficiaires
        if (client.getBenEffects() != null && !client.getBenEffects().isEmpty()) {
            ComEnt.DonneesEnt.LstBenEffect f = new ComEnt.DonneesEnt.LstBenEffect();
            for (sss_cdr_client_benef bnf : client.getBenEffects()) {
                if (bnf != null) {
                    ComEnt.DonneesEnt.LstBenEffect.BenEffect n = new ComEnt.DonneesEnt.LstBenEffect.BenEffect();
                    n.setTypIdBenEffect(bnf.getTypIdBenEffect());
                    n.setIdBenEffect(bnf.getIdBenEffect());
                    n.setNomBenEffect(bnf.getNomBenEffect());
                    n.setPreBenEffect(bnf.getPreBenEffect());
                    n.setNatBenEffect(bnf.getNatBenEffect());
                    f.getBenEffect().add(n);
                }
            }
            if (!f.getBenEffect().isEmpty()) {
                d_ent.getLstBenEffect().add(f);
            }
        }

        return d_ent;
    }

    private void mapDonneesIntPP(ComEnt.DonneesEnt d_ent, sss_cdr_DonneesIntPP pp) throws DatatypeConfigurationException {
        if (pp == null) return;

        d_ent.setTpIdPrincipal(pp.getTpIdPrincipal());
        d_ent.setIdPrincipal(pp.getIdPrincipal());
        d_ent.setPrenom(pp.getPrenom());
        d_ent.setNomFamille(pp.getNomFamille());
        d_ent.setPaysDelivrance(pp.getPaysDelivrance());
        d_ent.setDtDelivrance(convertDateToXml(pp.getDtDelivrance()));
        d_ent.setDtExpiration(convertDateToXml(pp.getDtExpiration()));
        d_ent.setTypePPPro(pp.getTypePPPro());
        d_ent.setRNAE(pp.getRNAE());
        d_ent.setDtNaissance(convertDateToXml(pp.getDtNaissance()));
        d_ent.setCodLocalNaissance(pp.getCodLocalNaissance());
        d_ent.setSexe(pp.getSexe());
        d_ent.setNationalite(pp.getNationalite());
        d_ent.setSitFamille(pp.getSitFamille());
        d_ent.setCodCatProf(pp.getCodCatProf());
        if (pp.getMenage() != null) {
            d_ent.setMenage(BigInteger.valueOf(pp.getMenage()));
        }
        d_ent.setQualAcadem(pp.getQualAcadem());
        d_ent.setCatClient(pp.getCatClient());
    }

    private void mapDonneesIntPM(ComEnt.DonneesEnt d_ent, sss_cdr_DonneesIntPM pm) throws DatatypeConfigurationException {
        if (pm == null) return;

        d_ent.setRaisonSocial(pm.getRaisonSocial());
        d_ent.setSigle(pm.getSigle());
        d_ent.setFormJur(pm.getFormJur());
        d_ent.setCodTrib(pm.getCodTrib());
        d_ent.setRegCommerce(pm.getRegCommerce());
        d_ent.setICE(pm.getICE());
        d_ent.setIdFiscal(pm.getIdFiscal());
        d_ent.setNumTaxeProf(pm.getNumTaxeProf());
        d_ent.setIdSpecifique(pm.getIdSpecifique());
        d_ent.setCodLEI(pm.getCodLEI());
        d_ent.setCodActPrinc(pm.getCodActPrinc());
        d_ent.setCodActSec(pm.getCodActSec());
        d_ent.setTailleEntrep(pm.getTailleEntrep());
        d_ent.setGenre(pm.getGenre());
        d_ent.setDtCreation(convertDateToXml(pm.getDtCreation()));
        d_ent.setNatMod(pm.getNatMod());
        d_ent.setDtMod(convertDateToXml(pm.getDtMod()));
        d_ent.setFlagSuc(pm.getFlagSuc());
        d_ent.setTpIdPrincSiege(pm.getTpIdPrincSiege());
        d_ent.setIdPrincSiege(pm.getIdPrincSiege());
        d_ent.setRaisonSocSiege(pm.getRaisonSocSiege());
        d_ent.setGroupAppart(pm.getGroupAppart());
    }

    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }

    private sss_cdr_client_stat mappingTraitement(sss_cdr_inter_client_stat inter){
        // ── 1. Mapper les données principales du client ──
        sss_cdr_client_stat finalClient = sss_cdr_client_stat.builder()
                .id_client(inter.getId_client())
                .actionType(inter.getActionType())
                .dateExtraction(inter.getDateExtraction())
                .id_lot(inter.getId_lot())
                .entObserv(inter.getEntObserv())
                .entDeclar(inter.getEntDeclar())
                .dtRefEnt(inter.getDtRefEnt())
                .codClient(inter.getCodClient())
                .altCodClient(inter.getAltCodClient())
                .natClient(mappingLoader.map("CDTYPT", inter.getNatClient()))
                .entLieeEtab(mappingLoader.map("ENTLIEEETAB", inter.getEntLieeEtab()))
                .codAgEcon(mappingLoader.map("SACT", inter.getCodAgEcon()))
                .build();

        // ── 2. Mapper l'adresse ──
        if (inter.getAdresse() != null) {
            finalClient.setAdresse(sss_cdr_Adresse.builder()
                    .adresse(inter.getAdresse().getAdresse())
                    .codPostal(inter.getAdresse().getCodPostal())
                    .codLocal(mappingLoader.map("CodLocal", inter.getAdresse().getCodLocal()))
                    .codPays(mappingLoader.map("PAYS", inter.getAdresse().getCodPays()))
                    .numTeleph(inter.getAdresse().getNumTeleph())
                    .build());
        }

        // ── 3. Mapper les données PP ──
        if (inter.getDonneesInt_pp() != null) {
            DonneesIntPP_interm pp = inter.getDonneesInt_pp();
            finalClient.setDonneesInt_pp(sss_cdr_DonneesIntPP.builder()
                    .idPrincipal(pp.getIdPrincipal())
                    .tpIdPrincipal(mappingLoader.map("TYPDOC",pp.getTpIdPrincipal()))
                    .prenom(pp.getPrenom())
                    .nomFamille(pp.getNomFamille())
                    .paysDelivrance(mappingLoader.map("PAYS", pp.getPaysDelivrance()))
                    .dtDelivrance(pp.getDtDelivrance())
                    .dtExpiration(pp.getDtExpiration())
                    .TypePPPro(mappingLoader.map("SACT",pp.getTypePPPro()))
                    .RNAE(pp.getRNAE())
                    .dtNaissance(pp.getDtNaissance())
                    .codLocalNaissance(mappingLoader.map("CodLocal", pp.getCodLocalNaissance()))
                    .sexe(mappingLoader.map("SEXE", pp.getSexe()))
                    .nationalite(mappingLoader.map("NATI", pp.getNationalite()))
                    .sitFamille(mappingLoader.map("SIT_F", pp.getSitFamille()))
                    .codCatProf(mappingLoader.map("PROF", pp.getCodCatProf()))
                    .menage(pp.getMenage())
                    .qualAcadem(mappingLoader.map("QualAcadem",pp.getQualAcadem()))
                    .catClient(mappingLoader.map("RSDT",pp.getCatClient()))
                    .build());
        }

        // ── 4. Mapper les données PM ──
        if (inter.getDonneesInt_pm() != null) {
            DonneesIntPM_interm pm = inter.getDonneesInt_pm();
            finalClient.setDonneesInt_pm(sss_cdr_DonneesIntPM.builder()
                    .raisonSocial(pm.getRaisonSocial())
                    .sigle(pm.getSigle())
                    .formJur(mappingLoader.map("FJR", pm.getFormJur()))
                    .codTrib(mappingLoader.map("CDTR",pm.getCodTrib()))
                    .regCommerce(pm.getRegCommerce())
                    .ICE(pm.getICE())
                    .idFiscal(pm.getIdFiscal())
                    .numTaxeProf(pm.getNumTaxeProf())
                    .idSpecifique(pm.getIdSpecifique())
                    .codLEI(pm.getCodLEI())
                    .codActPrinc(mappingLoader.map("SACT",pm.getCodActPrinc()))
                    .codActSec(mappingLoader.map("SACT",pm.getCodActSec()))
                    .tailleEntrep(mappingLoader.map("TailleEntrep", pm.getTailleEntrep()))
                    .genre(pm.getGenre())
                    .dtCreation(pm.getDtCreation())
                    //.natMod(mappingLoader.map("NatMod",pm.getNatMod()))
                    .natMod(pm.getNatMod())
                    .dtMod(pm.getDtMod())
                    .flagSuc(pm.getFlagSuc())
                    .tpIdPrincSiege(mappingLoader.map("TYPDOC",pm.getTpIdPrincSiege()))
                    .idPrincSiege(pm.getIdPrincSiege())
                    .raisonSocSiege(pm.getRaisonSocSiege())
                    .groupAppart(mappingLoader.map("GroupAppart",pm.getGroupAppart()))
                    .build());
        }

        // ── 5. Mapper les actionnariats ──
        if (inter.getActionnariats() != null && !inter.getActionnariats().isEmpty()) {
            Set<sss_cdr_client_act> actsFinal = inter.getActionnariats().stream()
                    .map(act -> {
                        sss_cdr_client_act a = sss_cdr_client_act.builder()
                                .natActionnaire(mappingLoader.map("CDTYPT",act.getNatActionnaire()))
                                .formJurAct(mappingLoader.map("FJR", act.getFormJurAct()))
                                .tpIdPrincAct(mappingLoader.map("TYPDOC",act.getTpIdPrincAct()))
                                .idPrincAct(act.getIdPrincAct())
                                .codTribunAct(mappingLoader.map("CDTR",act.getCodTribunAct()))
                                .regCommerAct(act.getRegCommerAct())
                                .idSpecifiqueAct(act.getIdSpecifiqueAct())
                                .ICEAct(act.getICEAct())
                                .LEIAct(act.getLEIAct())
                                .payResAct(mappingLoader.map("PAYS", act.getPayResAct()))
                                .nomRaisonSocAct(act.getNomRaisonSocAct())
                                .qtpartCapSocAct(act.getQtpartCapSocAct())
                                .build();
                        a.setClient(finalClient);  // ← Lier au parent
                        return a;
                    }).collect(Collectors.toSet());
            finalClient.setActionnariats(actsFinal);
        }

        // ── 6. Mapper les bénéficiaires effectifs ──
        if (inter.getBenEffects() != null && !inter.getBenEffects().isEmpty()) {
            Set<sss_cdr_client_benef> bensFinal = inter.getBenEffects().stream()
                    .map(ben -> {
                        sss_cdr_client_benef b = sss_cdr_client_benef.builder()
                                .typIdBenEffect(mappingLoader.map("TYPDOC",ben.getTypIdBenEffect()))
                                .idBenEffect(ben.getIdBenEffect())
                                .nomBenEffect(ben.getNomBenEffect())
                                .preBenEffect(ben.getPreBenEffect())
                                .natBenEffect(mappingLoader.map("NATI", ben.getNatBenEffect()))
                                .build();
                        b.setClient(finalClient);  // ← Lier au parent
                        return b;
                    }).collect(Collectors.toSet());
            finalClient.setBenEffects(bensFinal);
        }

        return finalClient;
    }

}
