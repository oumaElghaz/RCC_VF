package ma.vivalis.BKAM_CDR_API1.client.batch.processor;

import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_arch_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.sss_cdr_inter_client_stat;
import ma.vivalis.BKAM_CDR_API1.client.model.util.*;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientArchProcessor implements ItemProcessor<sss_cdr_inter_client_stat,sss_cdr_arch_client_stat> {

    @Override
    public @Nullable sss_cdr_arch_client_stat process(sss_cdr_inter_client_stat item) throws Exception {
        sss_cdr_arch_client_stat archiv = buildArchivFromIntermediaire(item);
        return archiv;
    }
    private sss_cdr_arch_client_stat buildArchivFromIntermediaire(sss_cdr_inter_client_stat inter) {

        sss_cdr_arch_client_stat archiv = sss_cdr_arch_client_stat.builder()
                .id_client(inter.getId_client())
                .id_lot(inter.getId_lot())
                .dateExtraction(inter.getDateExtraction())
                //.entObserv(inter.getEntObserv())
                //.entDeclar(inter.getEntDeclar())
                .dtRefEnt(inter.getDtRefEnt())
                //.codClient(inter.getCodClient())
                .altCodClient(inter.getAltCodClient())
                .natClient(inter.getNatClient())
                .entLieeEtab(inter.getEntLieeEtab())
                .codAgEcon(inter.getCodAgEcon())
                .build();

        // ── Adresse ──
        if (inter.getAdresse() != null) {
            archiv.setAdresse(Adresse_Arch.builder()
                    .adresse(inter.getAdresse().getAdresse())
                    .codPostal(inter.getAdresse().getCodPostal())
                    .codLocal(inter.getAdresse().getCodLocal())
                    .codPays(inter.getAdresse().getCodPays())
                    .numTeleph(inter.getAdresse().getNumTeleph())
                    .build());
        }

        // ── Données PP ──
        if (inter.getDonneesInt_pp() != null) {
            archiv.setDonneesInts_pp(DonneesIntPP_Arch.builder()
                    .idPrincipal(inter.getDonneesInt_pp().getIdPrincipal())
                    .tpIdPrincipal(inter.getDonneesInt_pp().getTpIdPrincipal())
                    .prenom(inter.getDonneesInt_pp().getPrenom())
                    .nomFamille(inter.getDonneesInt_pp().getNomFamille())
                    .paysDelivrance(inter.getDonneesInt_pp().getPaysDelivrance())
                    .dtDelivrance(inter.getDonneesInt_pp().getDtDelivrance())
                    .dtExpiration(inter.getDonneesInt_pp().getDtExpiration())
                    .TypePPPro(inter.getDonneesInt_pp().getTypePPPro())
                    .RNAE(inter.getDonneesInt_pp().getRNAE())
                    .dtNaissance(inter.getDonneesInt_pp().getDtNaissance())
                    .codLocalNaissance(inter.getDonneesInt_pp().getCodLocalNaissance())
                    .sexe(inter.getDonneesInt_pp().getSexe())
                    .nationalite(inter.getDonneesInt_pp().getNationalite())
                    .sitFamille(inter.getDonneesInt_pp().getSitFamille())
                    .codCatProf(inter.getDonneesInt_pp().getCodCatProf())
                    .menage(inter.getDonneesInt_pp().getMenage())
                    .qualAcadem(inter.getDonneesInt_pp().getQualAcadem())
                    .catClient(inter.getDonneesInt_pp().getCatClient())
                    .build());
        }

        // ── Données PM ──
        if (inter.getDonneesInt_pm() != null) {
            archiv.setDonneesInts_pm(DonneesIntPM_Arch.builder()
                    .raisonSocial(inter.getDonneesInt_pm().getRaisonSocial())
                    .sigle(inter.getDonneesInt_pm().getSigle())
                    .formJur(inter.getDonneesInt_pm().getFormJur())
                    .codTrib(inter.getDonneesInt_pm().getCodTrib())
                    .regCommerce(inter.getDonneesInt_pm().getRegCommerce())
                    .ICE(inter.getDonneesInt_pm().getICE())
                    .idFiscal(inter.getDonneesInt_pm().getIdFiscal())
                    .numTaxeProf(inter.getDonneesInt_pm().getNumTaxeProf())
                    .idSpecifique(inter.getDonneesInt_pm().getIdSpecifique())
                    .codLEI(inter.getDonneesInt_pm().getCodLEI())
                    .codActPrinc(inter.getDonneesInt_pm().getCodActPrinc())
                    .codActSec(inter.getDonneesInt_pm().getCodActSec())
                    .tailleEntrep(inter.getDonneesInt_pm().getTailleEntrep())
                    .genre(inter.getDonneesInt_pm().getGenre())
                    .dtCreation(inter.getDonneesInt_pm().getDtCreation())
                    .natMod(inter.getDonneesInt_pm().getNatMod())
                    .dtMod(inter.getDonneesInt_pm().getDtMod())
                    .flagSuc(inter.getDonneesInt_pm().getFlagSuc())
                    .tpIdPrincSiege(inter.getDonneesInt_pm().getTpIdPrincSiege())
                    .idPrincSiege(inter.getDonneesInt_pm().getIdPrincSiege())
                    .raisonSocSiege(inter.getDonneesInt_pm().getRaisonSocSiege())
                    .groupAppart(inter.getDonneesInt_pm().getGroupAppart())
                    .build());
        }

        // ── Actionnariats ──
        if (inter.getActionnariats() != null) {
            Set<sss_cdr_arch_client_act> actsArchiv = inter.getActionnariats().stream()
                    .map(act -> {
                        sss_cdr_arch_client_act a = sss_cdr_arch_client_act.builder()
                                .natActionnaire(act.getNatActionnaire())
                                .formJurAct(act.getFormJurAct())
                                .tpIdPrincAct(act.getTpIdPrincAct())
                                .idPrincAct(act.getIdPrincAct())
                                .codTribunAct(act.getCodTribunAct())
                                .regCommerAct(act.getRegCommerAct())
                                .idSpecifiqueAct(act.getIdSpecifiqueAct())
                                .ICEAct(act.getICEAct())
                                .LEIAct(act.getLEIAct())
                                .payResAct(act.getPayResAct())
                                .nomRaisonSocAct(act.getNomRaisonSocAct())
                                .qtpartCapSocAct(act.getQtpartCapSocAct())
                                .build();
                        a.setClient(archiv);  // ← Lier au parent archiv
                        return a;
                    }).collect(Collectors.toSet());
            archiv.setActionnariats(actsArchiv);
        }

        // ── Bénéficiaires effectifs ──
        if (inter.getBenEffects() != null) {
            Set<sss_cdr_arch_client_benef> bensArchiv = inter.getBenEffects().stream()
                    .map(ben -> {
                        sss_cdr_arch_client_benef b = sss_cdr_arch_client_benef.builder()
                                .typIdBenEffect(ben.getTypIdBenEffect())
                                .idBenEffect(ben.getIdBenEffect())
                                .nomBenEffect(ben.getNomBenEffect())
                                .preBenEffect(ben.getPreBenEffect())
                                .natBenEffect(ben.getNatBenEffect())
                                .build();
                        b.setClient(archiv);  // ← Lier au parent archiv
                        return b;
                    }).collect(Collectors.toSet());
            archiv.setBenEffects(bensArchiv);
        }

        return archiv;
    }


}
