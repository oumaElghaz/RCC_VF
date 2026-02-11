package ma.vivalis.BKAM_CDR_API1.repositories.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComparaisonImpl implements Comparaison{
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List findModifiedAndNewIdsClients() {
        String sql = """
                SELECT id_client
                FROM (
                    -- Snapshot Client Stat
                    SELECT snap.id_client
                    FROM sss_cdr_snapshot_client_stat snap
                    LEFT JOIN sss_cdr_snapshot_arch_client_stat arch ON snap.id_client = arch.id_client
                    WHERE
                        IFNULL(snap.dateDeclaration, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(arch.dateExtraction, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(snap.entObserv, 'X') <> IFNULL(arch.entObserv, 'X')
                        OR IFNULL(snap.entDeclar, 'X') <> IFNULL(arch.entDeclar, 'X')
                        OR IFNULL(snap.dtRefEnt, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(arch.dtRefEnt, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(snap.actionType, 'X') <> IFNULL(arch.actionType, 'X')
                        OR IFNULL(snap.codClient, 'X') <> IFNULL(arch.codClient, 'X')
                        OR IFNULL(snap.altCodClient, 'X') <> IFNULL(arch.altCodClient, 'X')
                        OR IFNULL(snap.natClient, 'X') <> IFNULL(arch.natClient, 'X')
                        OR IFNULL(snap.entLieeEtab, 'X') <> IFNULL(arch.entLieeEtab, 'X')
                        OR IFNULL(snap.codAgEcon, 'X') <> IFNULL(arch.codAgEcon, 'X')
                        OR IFNULL(snap.flag_envoi, 'X') <> IFNULL(arch.flag_envoi, 'X')
                
                    UNION
                
                    -- Adresse
                    SELECT adr.id_client
                    FROM Adresse adr
                    LEFT JOIN Adresse_Arch adr_arc ON adr.id_client = adr_arc.id_client
                    WHERE
                        IFNULL(adr.adresse, 'X') <> IFNULL(adr_arc.adresse, 'X')
                        OR IFNULL(adr.codPostal, 'X') <> IFNULL(adr_arc.codPostal, 'X')
                        OR IFNULL(adr.codLocal, 'X') <> IFNULL(adr_arc.codLocal, 'X')
                        OR IFNULL(adr.codPays, 'X') <> IFNULL(adr_arc.codPays, 'X')
                        OR IFNULL(adr.numTeleph, 'X') <> IFNULL(adr_arc.numTeleph, 'X')
                
                    UNION
                
                    -- Donnees Int PP
                    SELECT d_pp.id_client
                    FROM donneesintpp d_pp
                    LEFT JOIN donneesintpp_Arch d_pp_arc ON d_pp.id_client = d_pp_arc.id_client
                    WHERE
                        IFNULL(d_pp.idPrincipal, 'X') <> IFNULL(d_pp_arc.idPrincipal, 'X')
                        OR IFNULL(d_pp.tpIdPrincipal, 'X') <> IFNULL(d_pp_arc.tpIdPrincipal, 'X')
                        OR IFNULL(d_pp.prenom, 'X') <> IFNULL(d_pp_arc.prenom, 'X')
                        OR IFNULL(d_pp.nomFamille, 'X') <> IFNULL(d_pp_arc.nomFamille, 'X')
                        OR IFNULL(d_pp.paysDelivrance, 'X') <> IFNULL(d_pp_arc.paysDelivrance, 'X')
                        OR IFNULL(d_pp.dtDelivrance, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(d_pp_arc.dtDelivrance, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(d_pp.dtExpiration, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(d_pp_arc.dtExpiration, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(d_pp.TypePPPro, 'X') <> IFNULL(d_pp_arc.TypePPPro, 'X')
                        OR IFNULL(d_pp.RNAE, 'X') <> IFNULL(d_pp_arc.RNAE, 'X')
                        OR IFNULL(d_pp.dtNaissance, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(d_pp_arc.dtNaissance, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(d_pp.codLocalNaissance, 'X') <> IFNULL(d_pp_arc.codLocalNaissance, 'X')
                        OR IFNULL(d_pp.sexe, 'X') <> IFNULL(d_pp_arc.sexe, 'X')
                        OR IFNULL(d_pp.nationalite, 'X') <> IFNULL(d_pp_arc.nationalite, 'X')
                        OR IFNULL(d_pp.sitFamille, 'X') <> IFNULL(d_pp_arc.sitFamille, 'X')
                        OR IFNULL(d_pp.codCatProf, 'X') <> IFNULL(d_pp_arc.codCatProf, 'X')
                        OR IFNULL(d_pp.menage, 'X') <> IFNULL(d_pp_arc.menage, 'X')
                        OR IFNULL(d_pp.qualAcadem, 'X') <> IFNULL(d_pp_arc.qualAcadem, 'X')
                        OR IFNULL(d_pp.catClient, 'X') <> IFNULL(d_pp_arc.catClient, 'X')
                
                    UNION
                
                    -- Donnees Int PM
                    SELECT d_pm.id_client
                    FROM DonneesIntPM d_pm
                    LEFT JOIN DonneesIntPM_Arch d_pm_arc ON d_pm.id_client = d_pm_arc.id_client
                    WHERE
                        IFNULL(d_pm.raisonSocial, 'X') <> IFNULL(d_pm_arc.raisonSocial, 'X')
                        OR IFNULL(d_pm.sigle, 'X') <> IFNULL(d_pm_arc.sigle, 'X')
                        OR IFNULL(d_pm.formJur, 'X') <> IFNULL(d_pm_arc.formJur, 'X')
                        OR IFNULL(d_pm.codTrib, 'X') <> IFNULL(d_pm_arc.codTrib, 'X')
                        OR IFNULL(d_pm.regCommerce, 'X') <> IFNULL(d_pm_arc.regCommerce, 'X')
                        OR IFNULL(d_pm.ICE, 'X') <> IFNULL(d_pm_arc.ICE, 'X')
                        OR IFNULL(d_pm.idFiscal, 'X') <> IFNULL(d_pm_arc.idFiscal, 'X')
                        OR IFNULL(d_pm.numTaxeProf, 'X') <> IFNULL(d_pm_arc.numTaxeProf, 'X')
                        OR IFNULL(d_pm.idSpecifique, 'X') <> IFNULL(d_pm_arc.idSpecifique, 'X')
                        OR IFNULL(d_pm.codLEI, 'X') <> IFNULL(d_pm_arc.codLEI, 'X')
                        OR IFNULL(d_pm.codActPrinc, 'X') <> IFNULL(d_pm_arc.codActPrinc, 'X')
                        OR IFNULL(d_pm.codActSec, 'X') <> IFNULL(d_pm_arc.codActSec, 'X')
                        OR IFNULL(d_pm.tailleEntrep, 'X') <> IFNULL(d_pm_arc.tailleEntrep, 'X')
                        OR IFNULL(d_pm.genre, 'X') <> IFNULL(d_pm_arc.genre, 'X')
                        OR IFNULL(d_pm.dtCreation, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(d_pm_arc.dtCreation, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(d_pm.natMod, 'X') <> IFNULL(d_pm_arc.natMod, 'X')
                        OR IFNULL(d_pm.dtMod, STR_TO_DATE('01/01/1900','%d/%m/%Y')) <> IFNULL(d_pm_arc.dtMod, STR_TO_DATE('01/01/1900','%d/%m/%Y'))
                        OR IFNULL(d_pm.flagSuc, 'X') <> IFNULL(d_pm_arc.flagSuc, 'X')
                        OR IFNULL(d_pm.tpIdPrincSiege, 'X') <> IFNULL(d_pm_arc.tpIdPrincSiege, 'X')
                        OR IFNULL(d_pm.tpIdPrincSiege, 'X') <> IFNULL(d_pm_arc.tpIdPrincSiege, 'X')
                        OR IFNULL(d_pm.idPrincSiege, 'X') <> IFNULL(d_pm_arc.idPrincSiege, 'X')
                        OR IFNULL(d_pm.raisonSocSiege, 'X') <> IFNULL(d_pm_arc.raisonSocSiege, 'X')
                        OR IFNULL(d_pm.groupAppart, 'X') <> IFNULL(d_pm_arc.groupAppart, 'X')
                
                    UNION
                
                    -- Snapshot Client Act
                    SELECT act.id_client
                    FROM sss_cdr_snapshot_client_act act
                    LEFT JOIN sss_cdr_snapshot_client_act_Arch act_arc ON act.id = act_arc.id
                    WHERE
                        IFNULL(act.natActionnaire, 'X') <> IFNULL(act_arc.natActionnaire, 'X')
                        OR IFNULL(act.formJurAct, 'X') <> IFNULL(act_arc.formJurAct, 'X')
                        OR IFNULL(act.tpIdPrincAct, 'X') <> IFNULL(act_arc.tpidprincAct, 'X')
                        OR IFNULL(act.idPrincAct, 'X') <> IFNULL(act_arc.idPrincAct, 'X')
                        OR IFNULL(act.codTribunAct, 'X') <> IFNULL(act_arc.codTribunAct, 'X')
                        OR IFNULL(act.regCommerAct, 'X') <> IFNULL(act_arc.regCommerAct, 'X')
                        OR IFNULL(act.idSpecifiqueAct, 'X') <> IFNULL(act_arc.idSpecifiqueAct, 'X')
                        OR IFNULL(act.ICEAct, 'X') <> IFNULL(act_arc.ICEAct, 'X')
                        OR IFNULL(act.LEIAct, 'X') <> IFNULL(act_arc.LEIAct, 'X')
                        OR IFNULL(act.payResAct, 'X') <> IFNULL(act_arc.payResAct, 'X')
                        OR IFNULL(act.nomRaisonSocAct, 'X') <> IFNULL(act_arc.nomRaisonSocAct, 'X')
                        OR IFNULL(act.qtpartCapSocAct, 0) <> IFNULL(act_arc.qtpartCapSocAct, 0)
                
                    UNION
                
                    -- Snapshot Client Benef
                    SELECT bnf.id_client
                    FROM sss_cdr_snapshot_client_benef bnf
                    LEFT JOIN sss_cdr_snapshot_client_benef_Arch bnf_arc ON bnf.id = bnf_arc.id
                    WHERE
                        IFNULL(bnf.typIdBenEffect, 'X') <> IFNULL(bnf_arc.typIdBenEffect, 'X')
                        OR IFNULL(bnf.idBenEffect, 'X') <> IFNULL(bnf_arc.idBenEffect, 'X')
                        OR IFNULL(bnf.nomBenEffect, 'X') <> IFNULL(bnf_arc.nomBenEffect, 'X')
                        OR IFNULL(bnf.preBenEffect, 'X') <> IFNULL(bnf_arc.preBenEffect, 'X')
                        OR IFNULL(bnf.natBenEffect, 'X') <> IFNULL(bnf_arc.natBenEffect, 'X')
                ) AS t;
                """;

        return entityManager.createNativeQuery(sql).getResultList();
    }
}

