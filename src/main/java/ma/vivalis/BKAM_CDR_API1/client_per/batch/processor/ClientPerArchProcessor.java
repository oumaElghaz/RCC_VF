package ma.vivalis.BKAM_CDR_API1.client_per.batch.processor;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_arch_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ClientPerArchProcessor implements ItemProcessor<sss_cdr_inter_client_per, sss_cdr_arch_client_per> {
    @Override
    public @Nullable sss_cdr_arch_client_per process(sss_cdr_inter_client_per item) throws Exception {
        sss_cdr_arch_client_per archiv=buildArchFromIntermediaire(item);
        return archiv;
    }

    private sss_cdr_arch_client_per buildArchFromIntermediaire(sss_cdr_inter_client_per inter){
        sss_cdr_arch_client_per arch=sss_cdr_arch_client_per.builder()
                .codClient(inter.getCodClient())
                .id_lot(inter.getId_lot())
                .dateExtraction(inter.getDateExtraction())
                //.entObserv (inter.getEntObserv())
                //.entDeclar (inter.getEntDeclar())
                //.dtCreation (inter.getDtCreation())
                //.idDest(inter.getIdDest())
                .dtRef(inter.getDtRef())
                .actionType(inter.getActionType())
                .watchList (inter.getWatchList())
                .etatAvProcJud(inter.getEtatAvProcJud())
                .dtEtatAvProcJud(inter.getDtEtatAvProcJud())
                .revenu (inter.getRevenu())
                .dtRevenu(inter.getDtRevenu())
                .annExercCompt(inter.getAnnExercCompt())
                .capSocial(inter.getCapSocial())
                .capPropres(inter.getCapPropres())
                .actImmobilises(inter.getActImmobilises())
                .totBilan(inter.getTotBilan())
                .chiffreAffaire(inter.getChiffreAffaire())
                .dtAffairesAnExp(inter.getDtAffairesAnExp())
                .detteBancLMT(inter.getDetteBancLMT())
                .detteBancCT (inter.getDetteBancCT())
                .passifCirculant(inter.getPassifCirculant())
                .dettesFourn (inter.getDettesFourn())
                .compteCourAssoc(inter.getCompteCourAssoc())
                .tresoreriePassif(inter.getTresoreriePassif())
                .actifCirculant(inter.getActifCirculant())
                .créancesClients(inter.getCréancesClients())
                .tresorerieActif(inter.getTresorerieActif())
                .caisse (inter.getCaisse())
                .achatsRevendus(inter.getAchatsRevendus())
                .achatsConsom(inter.getAchatsConsom())
                .chargesExternes(inter.getChargesExternes())
                .chargesInterets(inter.getChargesInterets())
                .resultatNet(inter.getResultatNet())
                .tpResultat(inter.getTpResultat())
                .PDCont (inter.getPDCont())
                .dtEvalRisques(inter.getDtEvalRisques())
                .modIRBCont(inter.getModIRBCont())
                .coteCli (inter.getCoteCli())
                .dateCoteCli(inter.getDateCoteCli())
                .modCoteCli (inter.getModCoteCli())
                .notAgence (inter.getNotAgence())
                .NomAgence (inter.getNomAgence())
                .dtnotAgc(inter.getDtnotAgc())

                .build();

        return arch;
    }
}
