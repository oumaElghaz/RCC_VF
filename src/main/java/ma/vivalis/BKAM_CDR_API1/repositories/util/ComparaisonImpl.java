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
                SELECT id_client FROM (
            SELECT id_client,dateDeclaration,entObserv,entDeclar,dtRefEnt,actionType,codClient,altCodClient,natClient,entLieeEtab,codAgEcon
            FROM sss_cdr_snapshot_client_stat
            MINUS
            SELECT id_client,dateExtraction,entObserv,entDeclar,dtRefEnt,actionType,codClient,altCodClient,natClient,entLieeEtab,codAgEcon
            FROM sss_cdr_snapshot_arch_client_stat
        )
        UNION
        SELECT DISTINCT id_client FROM (
            SELECT adresse, codPostal, codLocal, codPays, numTeleph, id_client
            FROM Adresse
            MINUS
            SELECT adresse, codPostal, codLocal, codPays, numTeleph, id_client
            FROM Adresse_Arch
        )
        UNION
        SELECT DISTINCT id_client FROM (
            SELECT idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance, dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe, nationalite, sitFamille, codCatProf, menage,qualAcadem, catClient, id_client
            FROM DonneesIntPP
            MINUS
            SELECT idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance, dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe, nationalite, sitFamille, codCatProf, menage,qualAcadem, catClient, id_client
            FROM DonneesIntPP_Arch
        )
        UNION
        SELECT DISTINCT id_client FROM (
            SELECT raisonSocial, sigle, formJur,codTrib, regCommerce, ICE, idFiscal, numTaxeProf, idSpecifique, codLEI, codActPrinc, codActSec, tailleEntrep, genre, dtCreation,natMod,dtMod,flagSuc,tpIdPrincSiege,idPrincSiege, raisonSocSiege,groupAppart,id_client
            FROM DonneesIntPM
            MINUS
            SELECT raisonSocial, sigle, formJur,codTrib, regCommerce, ICE, idFiscal, numTaxeProf, idSpecifique, codLEI, codActPrinc, codActSec, tailleEntrep, genre, dtCreation,natMod,dtMod,flagSuc,tpIdPrincSiege,idPrincSiege, raisonSocSiege,groupAppart,id_client
            FROM DonneesIntPM_Arch
        )
        UNION
        SELECT DISTINCT id_client FROM (
            SELECT id, natActionnaire,formJurAct, tpIdPrincAct, idPrincAct,codTribunAct,regCommerAct,idSpecifiqueAct,ICEAct, LEIAct, payResAct, nomRaisonSocAct, qtpartCapSocAct,id_client
            FROM sss_cdr_snapshot_client_act
            MINUS
            SELECT id, natActionnaire,formJurAct, tpIdPrincAct, idPrincAct,codTribunAct,regCommerAct,idSpecifiqueAct,ICEAct, LEIAct, payResAct, nomRaisonSocAct, qtpartCapSocAct,id_client
            FROM sss_cdr_snapshot_client_act_Arch
        )
        UNION
        SELECT DISTINCT id_client FROM (
            SELECT id, typIdBenEffect, idBenEffect, nomBenEffect, preBenEffect, natBenEffect,id_client
            FROM sss_cdr_snapshot_client_benef
            MINUS
            SELECT id, typIdBenEffect, idBenEffect, nomBenEffect, preBenEffect, natBenEffect,id_client
            FROM sss_cdr_snapshot_client_benef_Arch
        )""";

        return entityManager.createNativeQuery(sql).getResultList();
    }
}

