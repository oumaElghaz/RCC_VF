INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType)
VALUES ('', '0', NULL, '2024-02-12 00:00:00.000000', '112', 'CF273895', '415', '415', 'CF273895', '1', 'EI');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal, id_client, numTeleph)
VALUES ('46895', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', 'CF273895', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice, id_client, idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, '2024-05-27 00:00:00.000000', NULL, '46895', '900', '', '83', '', '26', 'M',
'', '000000000000012', 'CF273895', '46895', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '46895', '', '', '', 'CF273895',
'BB46895', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('46895', 'T046895', 'CF273895', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient, id_client)
VALUES ('46895', '46895', 'CIN', 'John', 'Doe', 'AF', '2024-10-13 00:00:00.000000',
'2024-06-19 00:00:00.000000', '100', 'RNAE46895', '1995-12-31 23:59:59.000000', 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR', 'CF273895');