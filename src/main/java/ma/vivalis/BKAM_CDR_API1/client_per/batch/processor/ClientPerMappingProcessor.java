package ma.vivalis.BKAM_CDR_API1.client_per.batch.processor;

import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_client_per;
import ma.vivalis.BKAM_CDR_API1.client_per.model.sss_cdr_inter_client_per;
import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
    public class ClientPerMappingProcessor implements ItemProcessor<sss_cdr_inter_client_per, sss_cdr_client_per> {
    private static final Logger log = LoggerFactory.getLogger(ClientPerMappingProcessor.class);
    private final MappingLoader mappingLoader;

    public ClientPerMappingProcessor(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }

    @Override
    public @Nullable sss_cdr_client_per process(sss_cdr_inter_client_per item) throws Exception {
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
