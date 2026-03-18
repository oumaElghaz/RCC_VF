package ma.vivalis.BKAM_CDR_API1.client.batch.reader;

import ma.vivalis.BKAM_CDR_API1.client.mapper.ClientChangeDTORowMapper;
import ma.vivalis.BKAM_CDR_API1.client.model.dto.ClientChangeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ClientCompareNativeReader {
    private static final Logger log = LoggerFactory.getLogger(ClientCompareNativeReader.class);

    @Bean
    @StepScope
    public JdbcCursorItemReader<ClientChangeDTO> clientCompareReader(
            DataSource dataSource) {

        String sql = """
            -- NOUVEAUX (EI) : dans snapshot mais pas dans le dernier client_stat
                SELECT s.id_client, s.dateDeclaration, s.entObserv, s.entDeclar,
                                  s.dtRefEnt, s.codClient, s.altCodClient, s.natClient,
                                  s.entLieeEtab, s.codAgEcon,
                                  a.adresse, a.codPostal, a.codLocal, a.codPays, a.numTeleph,
                                  pp.idPrincipal, pp.tpIdPrincipal, pp.prenom, pp.nomFamille,
                                  pp.nationalite, pp.sexe, pp.dtNaissance, pp.codLocalNaissance,
                                  pp.dtDelivrance, pp.dtExpiration, pp.paysDelivrance,
                                  pp.catClient, pp.codCatProf, pp.sitFamille, pp.qualAcadem,
                                  pp.RNAE, pp.menage, pp.TypePPPro,
                                  pm.raisonSocial, pm.formJur, pm.ICE, pm.codLEI,
                                  pm.regCommerce, pm.codTrib, pm.idFiscal, pm.numTaxeProf,
                                  pm.codActPrinc, pm.codActSec, pm.tailleEntrep, pm.sigle,
                                  pm.groupAppart, pm.genre, pm.flagSuc, pm.dtCreation AS pmDtCreation,
                                  pm.dtMod, pm.natMod, pm.idPrincSiege, pm.tpIdPrincSiege,
                                  pm.raisonSocSiege, pm.idSpecifique,
                                  'EI' AS actionType
                           FROM sss_cdr_snapshot_client_stat s
                           LEFT JOIN adresse a ON a.id = s.adresse_id
                           LEFT JOIN donneesintpp pp ON pp.id = s.donneesInt_pp_id
                           LEFT JOIN donneesintpm pm ON pm.id = s.donneesInt_pm_id
                           WHERE s.id_client NOT IN (
                               SELECT c.id_client FROM sss_cdr_arch_client_stat c
                           )
                
                           UNION ALL
                
                           -- MODIFIÉS (EU) : dans les deux mais avec des données différentes
                           SELECT s.id_client, s.dateDeclaration, s.entObserv, s.entDeclar,
                                  s.dtRefEnt, s.codClient, s.altCodClient, s.natClient,
                                  s.entLieeEtab, s.codAgEcon,
                                  a.adresse, a.codPostal, a.codLocal, a.codPays, a.numTeleph,
                                  pp.idPrincipal, pp.tpIdPrincipal, pp.prenom, pp.nomFamille,
                                  pp.nationalite, pp.sexe, pp.dtNaissance, pp.codLocalNaissance,
                                  pp.dtDelivrance, pp.dtExpiration, pp.paysDelivrance,
                                  pp.catClient, pp.codCatProf, pp.sitFamille, pp.qualAcadem,
                                  pp.RNAE, pp.menage, pp.TypePPPro,
                                  pm.raisonSocial, pm.formJur, pm.ICE, pm.codLEI,
                                  pm.regCommerce, pm.codTrib, pm.idFiscal, pm.numTaxeProf,
                                  pm.codActPrinc, pm.codActSec, pm.tailleEntrep, pm.sigle,
                                  pm.groupAppart, pm.genre, pm.flagSuc, pm.dtCreation AS pmDtCreation,
                                  pm.dtMod, pm.natMod, pm.idPrincSiege, pm.tpIdPrincSiege,
                                  pm.raisonSocSiege, pm.idSpecifique,
                                  'EU' AS actionType
                           FROM sss_cdr_snapshot_client_stat s
                           LEFT JOIN adresse a ON a.id = s.adresse_id
                           LEFT JOIN donneesintpp pp ON pp.id = s.donneesInt_pp_id
                           LEFT JOIN donneesintpm pm ON pm.id = s.donneesInt_pm_id
                           INNER JOIN sss_cdr_arch_client_stat c ON c.id_client = s.id_client
                           WHERE (
                               COALESCE(s.codClient,'') <> COALESCE(c.codClient,'')
                               OR COALESCE(s.natClient,'') <> COALESCE(c.natClient,'')
                               OR COALESCE(s.codAgEcon,'') <> COALESCE(c.codAgEcon,'')
                               OR COALESCE(s.altCodClient,'') <> COALESCE(c.altCodClient,'')
                               OR COALESCE(s.entLieeEtab,'') <> COALESCE(c.entLieeEtab,'')
                           )
            """;

        return new JdbcCursorItemReaderBuilder<ClientChangeDTO>()
                .name("clientCompareNativeReader")
                .dataSource(dataSource)
                .sql(sql)
                .rowMapper(new ClientChangeDTORowMapper())
                .build();
    }
}
