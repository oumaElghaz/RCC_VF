package ma.vivalis.BKAM_CDR_API1.client.mapper;

import ma.vivalis.BKAM_CDR_API1.client.model.dto.ClientChangeDTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientChangeDTORowMapper implements RowMapper<ClientChangeDTO> {
    @Override
    public ClientChangeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ClientChangeDTO.builder()
                // Client
                .idClient(rs.getString("id_client"))
                .dateExtraction(rs.getDate("dateDeclaration"))
                .entObserv(rs.getString("entObserv"))
                .entDeclar(rs.getString("entDeclar"))
                .dtRefEnt(rs.getDate("dtRefEnt"))
                .codClient(rs.getString("codClient"))
                .altCodClient(rs.getString("altCodClient"))
                .natClient(rs.getString("natClient"))
                .entLieeEtab(rs.getString("entLieeEtab"))
                .codAgEcon(rs.getString("codAgEcon"))
                .actionType(rs.getString("actionType"))
                // Adresse
                .adresse(rs.getString("adresse"))
                .codPostal(rs.getString("codPostal"))
                .codLocal(rs.getString("codLocal"))
                .codPays(rs.getString("codPays"))
                .numTeleph(rs.getString("numTeleph"))
                // PP
                .idPrincipal(rs.getString("idPrincipal"))
                .tpIdPrincipal(rs.getString("tpIdPrincipal"))
                .prenom(rs.getString("prenom"))
                .nomFamille(rs.getString("nomFamille"))
                .nationalite(rs.getString("nationalite"))
                .sexe(rs.getString("sexe"))
                .dtNaissance(rs.getDate("dtNaissance"))
                .codLocalNaissance(rs.getString("codLocalNaissance"))
                .dtDelivrance(rs.getDate("dtDelivrance"))
                .dtExpiration(rs.getDate("dtExpiration"))
                .paysDelivrance(rs.getString("paysDelivrance"))
                .catClient(rs.getString("catClient"))
                .codCatProf(rs.getString("codCatProf"))
                .sitFamille(rs.getString("sitFamille"))
                .qualAcadem(rs.getString("qualAcadem"))
                .RNAE(rs.getString("RNAE"))
                .menage(rs.getInt("menage"))
                .typePPPro(rs.getString("TypePPPro"))
                // PM
                .raisonSocial(rs.getString("raisonSocial"))
                .formJur(rs.getString("formJur"))
                .ICE(rs.getString("ICE"))
                .codLEI(rs.getString("codLEI"))
                .regCommerce(rs.getString("regCommerce"))
                .codTrib(rs.getString("codTrib"))
                .idFiscal(rs.getString("idFiscal"))
                .numTaxeProf(rs.getString("numTaxeProf"))
                .codActPrinc(rs.getString("codActPrinc"))
                .codActSec(rs.getString("codActSec"))
                .tailleEntrep(rs.getString("tailleEntrep"))
                .sigle(rs.getString("sigle"))
                .groupAppart(rs.getString("groupAppart"))
                .genre(rs.getString("genre"))
                .flagSuc(rs.getBoolean("flagSuc"))
                .pmDtCreation(rs.getDate("pmDtCreation"))
                .dtMod(rs.getDate("dtMod"))
                .natMod(rs.getString("natMod"))
                .idPrincSiege(rs.getString("idPrincSiege"))
                .tpIdPrincSiege(rs.getString("tpIdPrincSiege"))
                .raisonSocSiege(rs.getString("raisonSocSiege"))
                .idSpecifique(rs.getString("idSpecifique"))
                .build();
    }
}
