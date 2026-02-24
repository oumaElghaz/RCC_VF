package ma.vivalis.BKAM_CDR_API1.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.vivalis.BKAM_CDR_API1.repositories.util.Comparaison;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComparaisonImpl implements Comparaison {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findModifiedAndNewIdsClients() {
        String sql = """
                SELECT DISTINCT id_client
                                   FROM (
                                       -- Snapshot Client Stat
                                       SELECT snap.id_client
                                       FROM sss_cdr_snapshot_client_stat snap
                                       LEFT JOIN sss_cdr_arch_client_stat arch
                                           ON snap.id_client = arch.id_client
                                       LEFT JOIN Adresse adr
                                           ON snap.adresse_id = adr.id
                                       LEFT JOIN Adresse_Arch adr_arc
                                           ON arch.adresse_archiv_id = adr_arc.id
                                       LEFT JOIN donneesintpp d_pp ON snap.donneesInt_pp_id = d_pp.id
                                       LEFT JOIN donneesintpp_Arch d_pp_arc
                                           ON arch.donneesInts_pp_id = d_pp_arc.id
                                       LEFT JOIN DonneesIntPM d_pm ON snap.donneesInt_pm_id = d_pm.id
                                       LEFT JOIN donneesintpm_Arch d_pm_arc
                                           ON arch.donneesInts_pm_id = d_pm_arc.id
                                       WHERE arch.id_client IS NULL -- Nouveau client
                                           --OR
                                           --NVL(snap.dateDeclaration, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(arch.dateExtraction, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(snap.entObserv, 'X') <> NVL(arch.entObserv, 'X')
                                           OR NVL(snap.entDeclar, 'X') <> NVL(arch.entDeclar, 'X')
                                           OR NVL(snap.dtRefEnt, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(arch.dtRefEnt, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(snap.actionType, 'X') <> NVL(arch.actionType, 'X')
                                           OR NVL(snap.codClient, 'X') <> NVL(arch.codClient, 'X')
                                           OR NVL(snap.altCodClient, 'X') <> NVL(arch.altCodClient, 'X')
                                           OR NVL(snap.natClient, 'X') <> NVL(arch.natClient, 'X')
                                           OR NVL(snap.entLieeEtab, 'X') <> NVL(arch.entLieeEtab, 'X')
                                           OR NVL(snap.codAgEcon, 'X') <> NVL(arch.codAgEcon, 'X')
                                           OR NVL(adr.adresse, 'X') <> NVL(adr_arc.adresse, 'X')
                                           OR NVL(adr.codPostal, 'X') <> NVL(adr_arc.codPostal, 'X')
                                           OR NVL(adr.codLocal, 'X') <> NVL(adr_arc.codLocal, 'X')
                                           OR NVL(adr.codPays, 'X') <> NVL(adr_arc.codPays, 'X')
                                           OR NVL(adr.numTeleph, 'X') <> NVL(adr_arc.numTeleph, 'X')
                                           OR NVL(d_pp.idPrincipal, 'X') <> NVL(d_pp_arc.idPrincipal, 'X')
                                           OR NVL(d_pp.tpIdPrincipal, 'X') <> NVL(d_pp_arc.tpIdPrincipal, 'X')
                                           OR NVL(d_pp.prenom, 'X') <> NVL(d_pp_arc.prenom, 'X')
                                           OR NVL(d_pp.nomFamille, 'X') <> NVL(d_pp_arc.nomFamille, 'X')
                                           OR NVL(d_pp.paysDelivrance, 'X') <> NVL(d_pp_arc.paysDelivrance, 'X')
                                           OR NVL(d_pp.dtDelivrance, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(d_pp_arc.dtDelivrance, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(d_pp.dtExpiration, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(d_pp_arc.dtExpiration, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(d_pp.TypePPPro, 'X') <> NVL(d_pp_arc.TypePPPro, 'X')
                                           OR NVL(d_pp.RNAE, 'X') <> NVL(d_pp_arc.RNAE, 'X')
                                           OR NVL(d_pp.dtNaissance, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(d_pp_arc.dtNaissance, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(d_pp.codLocalNaissance, 'X') <> NVL(d_pp_arc.codLocalNaissance, 'X')
                                           OR NVL(d_pp.sexe, 'X') <> NVL(d_pp_arc.sexe, 'X')
                                           OR NVL(d_pp.nationalite, 'X') <> NVL(d_pp_arc.nationalite, 'X')
                                           OR NVL(d_pp.sitFamille, 'X') <> NVL(d_pp_arc.sitFamille, 'X')
                                           OR NVL(d_pp.codCatProf, 'X') <> NVL(d_pp_arc.codCatProf, 'X')
                                           --OR NVL(d_pp.menage, 'X') <> NVL(d_pp_arc.menage, 'X')
                                           OR NVL(d_pp.qualAcadem, 'X') <> NVL(d_pp_arc.qualAcadem, 'X')
                                           OR NVL(d_pp.catClient, 'X') <> NVL(d_pp_arc.catClient, 'X')
                                           OR NVL(d_pm.raisonSocial, 'X') <> NVL(d_pm_arc.raisonSocial, 'X')
                                           OR NVL(d_pm.sigle, 'X') <> NVL(d_pm_arc.sigle, 'X')
                                           OR NVL(d_pm.formJur, 'X') <> NVL(d_pm_arc.formJur, 'X')
                                           OR NVL(d_pm.codTrib, 'X') <> NVL(d_pm_arc.codTrib, 'X')
                                           OR NVL(d_pm.regCommerce, 'X') <> NVL(d_pm_arc.regCommerce, 'X')
                                           OR NVL(d_pm.ICE, 'X') <> NVL(d_pm_arc.ICE, 'X')
                                           OR NVL(d_pm.idFiscal, 'X') <> NVL(d_pm_arc.idFiscal, 'X')
                                           OR NVL(d_pm.numTaxeProf, 'X') <> NVL(d_pm_arc.numTaxeProf, 'X')
                                           OR NVL(d_pm.idSpecifique, 'X') <> NVL(d_pm_arc.idSpecifique, 'X')
                                           OR NVL(d_pm.codLEI, 'X') <> NVL(d_pm_arc.codLEI, 'X')
                                           OR NVL(d_pm.codActPrinc, 'X') <> NVL(d_pm_arc.codActPrinc, 'X')
                                           OR NVL(d_pm.codActSec, 'X') <> NVL(d_pm_arc.codActSec, 'X')
                                           OR NVL(d_pm.tailleEntrep, 'X') <> NVL(d_pm_arc.tailleEntrep, 'X')
                                           OR NVL(d_pm.genre, 'X') <> NVL(d_pm_arc.genre, 'X')
                                           OR NVL(d_pm.dtCreation, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(d_pm_arc.dtCreation, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(d_pm.natMod, 'X') <> NVL(d_pm_arc.natMod, 'X')
                                           OR NVL(d_pm.dtMod, TO_DATE('01/01/1900','DD/MM/YYYY')) <> NVL(d_pm_arc.dtMod, TO_DATE('01/01/1900','DD/MM/YYYY'))
                                           OR NVL(d_pm.flagSuc, 'X') <> NVL(d_pm_arc.flagSuc, 'X')
                                           OR NVL(d_pm.tpIdPrincSiege, 'X') <> NVL(d_pm_arc.tpIdPrincSiege, 'X')
                                           OR NVL(d_pm.idPrincSiege, 'X') <> NVL(d_pm_arc.idPrincSiege, 'X')
                                           OR NVL(d_pm.raisonSocSiege, 'X') <> NVL(d_pm_arc.raisonSocSiege, 'X')
                                           OR NVL(d_pm.groupAppart, 'X') <> NVL(d_pm_arc.groupAppart, 'X')
                
                
                
                
                                       UNION ALL
                
                
                
                
                                       -- Snapshot Client Act
                                       SELECT act.id_client
                                       FROM sss_cdr_snapshot_client_act act
                                       LEFT JOIN sss_cdr_arch_client_act act_arc
                                           ON act.id = act_arc.id
                                       WHERE act_arc.id_client IS NULL -- Nouveau client
                                           OR
                                           NVL(act.natActionnaire, 'X') <> NVL(act_arc.natActionnaire, 'X')
                                           OR NVL(act.formJurAct, 'X') <> NVL(act_arc.formJurAct, 'X')
                                           OR NVL(act.tpIdPrincAct, 'X') <> NVL(act_arc.tpIdPrincAct, 'X')
                                           OR NVL(act.idPrincAct, 'X') <> NVL(act_arc.idPrincAct, 'X')
                                           OR NVL(act.codTribunAct, 'X') <> NVL(act_arc.codTribunAct, 'X')
                                           OR NVL(act.regCommerAct, 'X') <> NVL(act_arc.regCommerAct, 'X')
                                           OR NVL(act.idSpecifiqueAct, 'X') <> NVL(act_arc.idSpecifiqueAct, 'X')
                                           OR NVL(act.ICEAct, 'X') <> NVL(act_arc.ICEAct, 'X')
                                           OR NVL(act.LEIAct, 'X') <> NVL(act_arc.LEIAct, 'X')
                                           OR NVL(act.payResAct, 'X') <> NVL(act_arc.payResAct, 'X')
                                           OR NVL(act.nomRaisonSocAct, 'X') <> NVL(act_arc.nomRaisonSocAct, 'X')
                                           OR NVL(act.qtpartCapSocAct, 0) <> NVL(act_arc.qtpartCapSocAct, 0)
                
                                       UNION ALL
                
                                       -- Snapshot Client Benef
                                       SELECT bnf.id_client
                                       FROM sss_cdr_snapshot_client_benef bnf
                                       LEFT JOIN sss_cdr_arch_client_benef bnf_arc
                                           ON bnf.id = bnf_arc.id
                                       WHERE bnf_arc.id_client IS NULL -- Nouveau client
                                           OR
                                           NVL(bnf.typIdBenEffect, 'X') <> NVL(bnf_arc.typIdBenEffect, 'X')
                                           OR NVL(bnf.idBenEffect, 'X') <> NVL(bnf_arc.idBenEffect, 'X')
                                           OR NVL(bnf.nomBenEffect, 'X') <> NVL(bnf_arc.nomBenEffect, 'X')
                                           OR NVL(bnf.preBenEffect, 'X') <> NVL(bnf_arc.preBenEffect, 'X')
                                           OR NVL(bnf.natBenEffect, 'X') <> NVL(bnf_arc.natBenEffect, 'X')
                                   ) t
                """;

        // Solution 1 : Retour directement en tant que String (RECOMMANDÉ)
        @SuppressWarnings("unchecked")
        List<String> results = entityManager.createNativeQuery(sql, String.class).getResultList();
        return results;
    }
}