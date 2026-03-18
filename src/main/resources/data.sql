INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-03-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-06-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227001', '415', '415', 'CF227001', '1', 'EI', '11', '101', '21');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('11', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '21', '900', '', '83', '', '26', 'M',
'', '000000000000012', '1', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '1', '', '', '', 'CF227001',
'BB00001', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('1', 'T000001', 'CF227001', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('101', '1', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-06-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-12-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE1', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-08-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-02-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227002', '415', '415', 'CF227002', '1', 'EI', '12', '102', '22');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('12', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '22', '900', '', '83', '', '26', 'M',
'', '000000000000012', '2', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '2', '', '', '', 'CF227002',
'BB00002', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('2', 'T000002', 'CF227002', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('102', '2', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-08-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-04-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE2', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-06-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-03-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227003', '415', '415', 'CF227003', '1', 'EI', '13', '103', '23');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('13', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '23', '900', '', '83', '', '26', 'M',
'', '000000000000012', '3', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '3', '', '', '', 'CF227003',
'BB00003', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('3', 'T000003', 'CF227003', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('103', '3', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-03-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-08-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE3', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-08-03 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-02-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227004', '415', '415', 'CF227004', '1', 'EI', '14', '104', '24');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('14', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '24', '900', '', '83', '', '26', 'M',
'', '000000000000012', '4', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '4', '', '', '', 'CF227004',
'BB00004', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('4', 'T000004', 'CF227004', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('104', '4', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-02-23 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-11-02 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE4', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-06-07 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-03-12 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227005', '415', '415', 'CF227005', '1', 'EI', '15', '105', '25');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('15', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '25', '900', '', '83', '', '26', 'M',
'', '000000000000012', '5', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '5', '', '', '', 'CF227005',
'BB00005', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('5', 'T000005', 'CF227005', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('105', '5', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-12-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-02-12 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE5', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-09-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-08-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227006', '415', '415', 'CF227006', '1', 'EI', '16', '106', '26');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('16', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '26', '900', '', '83', '', '26', 'M',
'', '000000000000012', '6', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '6', '', '', '', 'CF227006',
'BB00006', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('6', 'T000006', 'CF227006', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('106', '6', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-04-11 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-03-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE6', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-09-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-10-16 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227007', '415', '415', 'CF227007', '1', 'EI', '17', '107', '27');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('17', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '27', '900', '', '83', '', '26', 'M',
'', '000000000000012', '7', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '7', '', '', '', 'CF227007',
'BB00007', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('7', 'T000007', 'CF227007', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('107', '7', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-09-11 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-10-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE7', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-12-29 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-10-02 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227008', '415', '415', 'CF227008', '1', 'EI', '18', '108', '28');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('18', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '28', '900', '', '83', '', '26', 'M',
'', '000000000000012', '8', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '8', '', '', '', 'CF227008',
'BB00008', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('8', 'T000008', 'CF227008', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('108', '8', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-05-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-05-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE8', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-08-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-05-01 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227009', '415', '415', 'CF227009', '1', 'EI', '19', '109', '29');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('19', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '29', '900', '', '83', '', '26', 'M',
'', '000000000000012', '9', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '9', '', '', '', 'CF227009',
'BB00009', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('9', 'T000009', 'CF227009', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('109', '9', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-06-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-09-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE9', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-05-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-05-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227010', '415', '415', 'CF227010', '1', 'EI', '20', '110', '30');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('20', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '30', '900', '', '83', '', '26', 'M',
'', '000000000000012', '10', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '10', '', '', '', 'CF227010',
'BB00010', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('10', 'T000010', 'CF227010', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('110', '10', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-10-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-04-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE10', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-10-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-10-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227011', '415', '415', 'CF227011', '1', 'EI', '21', '111', '31');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('21', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '31', '900', '', '83', '', '26', 'M',
'', '000000000000012', '11', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '11', '', '', '', 'CF227011',
'BB00011', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('11', 'T000011', 'CF227011', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('111', '11', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-11-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-03-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE11', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-03-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-03-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227012', '415', '415', 'CF227012', '1', 'EI', '22', '112', '32');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('22', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '32', '900', '', '83', '', '26', 'M',
'', '000000000000012', '12', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '12', '', '', '', 'CF227012',
'BB00012', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('12', 'T000012', 'CF227012', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('112', '12', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-01-05 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-01-15 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE12', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-05-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-03-15 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227013', '415', '415', 'CF227013', '1', 'EI', '23', '113', '33');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('23', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '33', '900', '', '83', '', '26', 'M',
'', '000000000000012', '13', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '13', '', '', '', 'CF227013',
'BB00013', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('13', 'T000013', 'CF227013', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('113', '13', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-07-31 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-11-10 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE13', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-02-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-08-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227014', '415', '415', 'CF227014', '1', 'EI', '24', '114', '34');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('24', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '34', '900', '', '83', '', '26', 'M',
'', '000000000000012', '14', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '14', '', '', '', 'CF227014',
'BB00014', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('14', 'T000014', 'CF227014', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('114', '14', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-01-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-06-07 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE14', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-03-31 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-04-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227015', '415', '415', 'CF227015', '1', 'EI', '25', '115', '35');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('25', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '35', '900', '', '83', '', '26', 'M',
'', '000000000000012', '15', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '15', '', '', '', 'CF227015',
'BB00015', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('15', 'T000015', 'CF227015', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('115', '15', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-06-11 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-06-06 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE15', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-10-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-11-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227016', '415', '415', 'CF227016', '1', 'EI', '26', '116', '36');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('26', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '36', '900', '', '83', '', '26', 'M',
'', '000000000000012', '16', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '16', '', '', '', 'CF227016',
'BB00016', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('16', 'T000016', 'CF227016', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('116', '16', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-05-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-11-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE16', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-05-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-08-11 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227017', '415', '415', 'CF227017', '1', 'EI', '27', '117', '37');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('27', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '37', '900', '', '83', '', '26', 'M',
'', '000000000000012', '17', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '17', '', '', '', 'CF227017',
'BB00017', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('17', 'T000017', 'CF227017', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('117', '17', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-05-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE17', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-04-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-12-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227018', '415', '415', 'CF227018', '1', 'EI', '28', '118', '38');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('28', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '38', '900', '', '83', '', '26', 'M',
'', '000000000000012', '18', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '18', '', '', '', 'CF227018',
'BB00018', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('18', 'T000018', 'CF227018', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('118', '18', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-12-02 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-08-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE18', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-06-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-04-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227019', '415', '415', 'CF227019', '1', 'EI', '29', '119', '39');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('29', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '39', '900', '', '83', '', '26', 'M',
'', '000000000000012', '19', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '19', '', '', '', 'CF227019',
'BB00019', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('19', 'T000019', 'CF227019', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('119', '19', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-01-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-05-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE19', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-02-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-07-29 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227020', '415', '415', 'CF227020', '1', 'EI', '30', '120', '40');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('30', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '40', '900', '', '83', '', '26', 'M',
'', '000000000000012', '20', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '20', '', '', '', 'CF227020',
'BB00020', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('20', 'T000020', 'CF227020', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('120', '20', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-12-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-07-03 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE20', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-08-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-01-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227021', '415', '415', 'CF227021', '1', 'EI', '31', '121', '41');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('31', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '41', '900', '', '83', '', '26', 'M',
'', '000000000000012', '21', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '21', '', '', '', 'CF227021',
'BB00021', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('21', 'T000021', 'CF227021', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('121', '21', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-03-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-06-06 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE21', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-10-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-09-07 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227022', '415', '415', 'CF227022', '1', 'EI', '32', '122', '42');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('32', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '42', '900', '', '83', '', '26', 'M',
'', '000000000000012', '22', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '22', '', '', '', 'CF227022',
'BB00022', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('22', 'T000022', 'CF227022', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('122', '22', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-07-29 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-10-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE22', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-01-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-02-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227023', '415', '415', 'CF227023', '1', 'EI', '33', '123', '43');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('33', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '43', '900', '', '83', '', '26', 'M',
'', '000000000000012', '23', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '23', '', '', '', 'CF227023',
'BB00023', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('23', 'T000023', 'CF227023', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('123', '23', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-06-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-06-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE23', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-05-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-03-31 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227024', '415', '415', 'CF227024', '1', 'EI', '34', '124', '44');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('34', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '44', '900', '', '83', '', '26', 'M',
'', '000000000000012', '24', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '24', '', '', '', 'CF227024',
'BB00024', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('24', 'T000024', 'CF227024', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('124', '24', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-05-03 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-02-05 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE24', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-01-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-06-06 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227025', '415', '415', 'CF227025', '1', 'EI', '35', '125', '45');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('35', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '45', '900', '', '83', '', '26', 'M',
'', '000000000000012', '25', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '25', '', '', '', 'CF227025',
'BB00025', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('25', 'T000025', 'CF227025', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('125', '25', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-08-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-11-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE25', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-05-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-12-31 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227026', '415', '415', 'CF227026', '1', 'EI', '36', '126', '46');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('36', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '46', '900', '', '83', '', '26', 'M',
'', '000000000000012', '26', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '26', '', '', '', 'CF227026',
'BB00026', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('26', 'T000026', 'CF227026', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('126', '26', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-10-29 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-11-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE26', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-07-05 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-08-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227027', '415', '415', 'CF227027', '1', 'EI', '37', '127', '47');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('37', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '47', '900', '', '83', '', '26', 'M',
'', '000000000000012', '27', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '27', '', '', '', 'CF227027',
'BB00027', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('27', 'T000027', 'CF227027', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('127', '27', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-03-19 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-03-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE27', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-09-23 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-11-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227028', '415', '415', 'CF227028', '1', 'EI', '38', '128', '48');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('38', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '48', '900', '', '83', '', '26', 'M',
'', '000000000000012', '28', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '28', '', '', '', 'CF227028',
'BB00028', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('28', 'T000028', 'CF227028', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('128', '28', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-04-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-04-11 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE28', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-01-16 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-10-01 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227029', '415', '415', 'CF227029', '1', 'EI', '39', '129', '49');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('39', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '49', '900', '', '83', '', '26', 'M',
'', '000000000000012', '29', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '29', '', '', '', 'CF227029',
'BB00029', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('29', 'T000029', 'CF227029', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('129', '29', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-07-02 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-06-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE29', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-10-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-07-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227030', '415', '415', 'CF227030', '1', 'EI', '40', '130', '50');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('40', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '50', '900', '', '83', '', '26', 'M',
'', '000000000000012', '30', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '30', '', '', '', 'CF227030',
'BB00030', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('30', 'T000030', 'CF227030', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('130', '30', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-08-11 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-06-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE30', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-11-05 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-07-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227031', '415', '415', 'CF227031', '1', 'EI', '41', '131', '51');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('41', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '51', '900', '', '83', '', '26', 'M',
'', '000000000000012', '31', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '31', '', '', '', 'CF227031',
'BB00031', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('31', 'T000031', 'CF227031', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('131', '31', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-06-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-03-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE31', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-08-19 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-01-23 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227032', '415', '415', 'CF227032', '1', 'EI', '42', '132', '52');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('42', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '52', '900', '', '83', '', '26', 'M',
'', '000000000000012', '32', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '32', '', '', '', 'CF227032',
'BB00032', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('32', 'T000032', 'CF227032', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('132', '32', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-02-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-12-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE32', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-01-29 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-01-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227033', '415', '415', 'CF227033', '1', 'EI', '43', '133', '53');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('43', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '53', '900', '', '83', '', '26', 'M',
'', '000000000000012', '33', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '33', '', '', '', 'CF227033',
'BB00033', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('33', 'T000033', 'CF227033', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('133', '33', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-08-06 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-05-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE33', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-08-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-10-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227034', '415', '415', 'CF227034', '1', 'EI', '44', '134', '54');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('44', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '54', '900', '', '83', '', '26', 'M',
'', '000000000000012', '34', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '34', '', '', '', 'CF227034',
'BB00034', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('34', 'T000034', 'CF227034', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('134', '34', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-09-28 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-01-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE34', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-05-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-11-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227035', '415', '415', 'CF227035', '1', 'EI', '45', '135', '55');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('45', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '55', '900', '', '83', '', '26', 'M',
'', '000000000000012', '35', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '35', '', '', '', 'CF227035',
'BB00035', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('35', 'T000035', 'CF227035', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('135', '35', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-07-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-05-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE35', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-01-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-02-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227036', '415', '415', 'CF227036', '1', 'EI', '46', '136', '56');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('46', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '56', '900', '', '83', '', '26', 'M',
'', '000000000000012', '36', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '36', '', '', '', 'CF227036',
'BB00036', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('36', 'T000036', 'CF227036', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('136', '36', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-04-12 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-09-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE36', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-03-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227037', '415', '415', 'CF227037', '1', 'EI', '47', '137', '57');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('47', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '57', '900', '', '83', '', '26', 'M',
'', '000000000000012', '37', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '37', '', '', '', 'CF227037',
'BB00037', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('37', 'T000037', 'CF227037', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('137', '37', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-07-16 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-01-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE37', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-03-19 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-10-19 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227038', '415', '415', 'CF227038', '1', 'EI', '48', '138', '58');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('48', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '58', '900', '', '83', '', '26', 'M',
'', '000000000000012', '38', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '38', '', '', '', 'CF227038',
'BB00038', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('38', 'T000038', 'CF227038', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('138', '38', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-07-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-09-15 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE38', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-04-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-10-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227039', '415', '415', 'CF227039', '1', 'EI', '49', '139', '59');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('49', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '59', '900', '', '83', '', '26', 'M',
'', '000000000000012', '39', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '39', '', '', '', 'CF227039',
'BB00039', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('39', 'T000039', 'CF227039', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('139', '39', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-12-19 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-03-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE39', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-11-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-03-06 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227040', '415', '415', 'CF227040', '1', 'EI', '50', '140', '60');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('50', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '60', '900', '', '83', '', '26', 'M',
'', '000000000000012', '40', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '40', '', '', '', 'CF227040',
'BB00040', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('40', 'T000040', 'CF227040', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('140', '40', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-09-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-06-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE40', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-06-01 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-11-15 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227041', '415', '415', 'CF227041', '1', 'EI', '51', '141', '61');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('51', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '61', '900', '', '83', '', '26', 'M',
'', '000000000000012', '41', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '41', '', '', '', 'CF227041',
'BB00041', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('41', 'T000041', 'CF227041', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('141', '41', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-05-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-03-18 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE41', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-02-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-11-22 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227042', '415', '415', 'CF227042', '1', 'EI', '52', '142', '62');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('52', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '62', '900', '', '83', '', '26', 'M',
'', '000000000000012', '42', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '42', '', '', '', 'CF227042',
'BB00042', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('42', 'T000042', 'CF227042', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('142', '42', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-11-24 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-05-31 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE42', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-05-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-09-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227043', '415', '415', 'CF227043', '1', 'EI', '53', '143', '63');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('53', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '63', '900', '', '83', '', '26', 'M',
'', '000000000000012', '43', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '43', '', '', '', 'CF227043',
'BB00043', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('43', 'T000043', 'CF227043', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('143', '43', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-09-13 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-07-07 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE43', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-07-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-11-03 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227044', '415', '415', 'CF227044', '1', 'EI', '54', '144', '64');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('54', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '64', '900', '', '83', '', '26', 'M',
'', '000000000000012', '44', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '44', '', '', '', 'CF227044',
'BB00044', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('44', 'T000044', 'CF227044', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('144', '44', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-06-05 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-06-14 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE44', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-11-03 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-10-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227045', '415', '415', 'CF227045', '1', 'EI', '55', '145', '65');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('55', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '65', '900', '', '83', '', '26', 'M',
'', '000000000000012', '45', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '45', '', '', '', 'CF227045',
'BB00045', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('45', 'T000045', 'CF227045', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('145', '45', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-12-08 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-10-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE45', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-05-25 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-05-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227046', '415', '415', 'CF227046', '1', 'EI', '56', '146', '66');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('56', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '66', '900', '', '83', '', '26', 'M',
'', '000000000000012', '46', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '46', '', '', '', 'CF227046',
'BB00046', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('46', 'T000046', 'CF227046', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('146', '46', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-11-17 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-06-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE46', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-11-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-10-21 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227047', '415', '415', 'CF227047', '1', 'EI', '57', '147', '67');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('57', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '67', '900', '', '83', '', '26', 'M',
'', '000000000000012', '47', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '47', '', '', '', 'CF227047',
'BB00047', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('47', 'T000047', 'CF227047', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('147', '47', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-10-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-08-20 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE47', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2025-03-04 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2024-08-15 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227048', '415', '415', 'CF227048', '1', 'EI', '58', '148', '68');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('58', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '68', '900', '', '83', '', '26', 'M',
'', '000000000000012', '48', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '48', '', '', '', 'CF227048',
'BB00048', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('48', 'T000048', 'CF227048', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('148', '48', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2025-04-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-01-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE48', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-01-05 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-05-31 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227049', '415', '415', 'CF227049', '1', 'EI', '59', '149', '69');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('59', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '69', '900', '', '83', '', '26', 'M',
'', '000000000000012', '49', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '49', '', '', '', 'CF227049',
'BB00049', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('49', 'T000049', 'CF227049', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('149', '49', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-09-09 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2024-03-12 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE49', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT
(altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType,adresse_id,
  donneesInt_pp_id, donneesInt_pm_id)
VALUES ('', '0', STR_TO_DATE('2024-06-30 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), STR_TO_DATE('2025-07-26 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '112', 'CF227050', '415', '415', 'CF227050', '1', 'EI', '60', '150', '70');
INSERT INTO adresse (id, adresse, codLocal, codPays, codPostal,  numTeleph)
VALUES ('60', 'BD SEBTA HAY MERIEM N36', '', 'MAR', '28630', '');
INSERT INTO donneesintpm
(flagSuc, dtCreation, dtMod, id, codActPrinc, codActSec, codTrib, codlei, formJur, genre,
groupAppart, ice,  idFiscal, idPrincSiege, idSpecifique, natMod, numTaxeProf,
raisonSocSiege, raisonSocial, regCommerce, sigle, tailleEntrep, tpIdPrincSiege)
VALUES (1, STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), NULL, '70', '900', '', '83', '', '26', 'M',
'', '000000000000012', '50', '', '', '0', '1',
'', 'FOR EDDGAS', '35351', '', 'TPE', '');
INSERT INTO sss_cdr_snapshot_client_act
(qtpartCapSocAct, id, codTribunAct, formJurAct, iceact, id_client,
idPrincAct, idSpecifiqueAct, leiact, natActionnaire,
nomRaisonSocAct, payResAct, regCommerAct, tpIdPrincAct)
VALUES ('0', '50', '', '', '', 'CF227050',
'BB00050', '', '', '1',
'EDDABZI', 'MAR', '', 'I');
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF
(ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('50', 'T000050', 'CF227050', 'P', 'EL GUASMI', 'YOUNESS', '1');
INSERT INTO donneesintpp
(id, idPrincipal, tpIdPrincipal, prenom, nomFamille, paysDelivrance, dtDelivrance,
dtExpiration, TypePPPro, RNAE, dtNaissance, codLocalNaissance, sexe,
nationalite, sitFamille, codCatProf, menage, qualAcadem, catClient)
VALUES ('150', '50', 'CIN', 'John', 'Doe', 'AF', STR_TO_DATE('2024-03-10 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'),
STR_TO_DATE('2025-09-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), '100', 'RNAE50', STR_TO_DATE('2024-05-27 00:00:00.000000', '%Y-%m-%d %H:%i:%s.%f'), 'AZ', '1',
'AFG', 'C', '0', 5, 'N1', 'MAR');

