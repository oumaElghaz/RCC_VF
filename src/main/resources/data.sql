--pays
INSERT INTO  sss_cdr_mapping  ( dmo ,  dou ,  id ,  att1 ,  att2 ,  att3 ,  codCibl ,  codSrc ,  ctab ,  descEvo ,  dom ,  libelle ,  uti )
VALUES ('2026-02-11 09:53:41.000000', '2026-02-18 09:53:41.000000', '1', 'Afghanistan', 'Afghanistan', 'Afghanistan', 'AFG', 'AF', 'PAYS', 'AFGHANISTAN', 'T_TER', 'Afghanistan', 'GGGG');
--code tribunal
INSERT INTO  sss_cdr_mapping  ( dmo ,  dou ,  id ,  att1 ,  att2 ,  att3 ,  codCibl ,  codSrc ,  ctab ,  descEvo ,  dom ,  libelle ,  uti )
VALUES ('2026-02-18 10:06:24.000000', '2026-02-03 10:06:24.000000', '2', 'NBBH', 'BVGGH', 'GFFG', '01', '1', 'CDTR', 'AGADIR', 'T_TRB', 'AGADIR', 'GGD');
--forme juridique
INSERT INTO  sss_cdr_mapping  ( dmo ,  dou ,  id ,  att1 ,  att2 ,  att3 ,  codCibl ,  codSrc ,  ctab ,  descEvo ,  dom ,  libelle ,  uti )
VALUES ('2026-02-18 10:06:24.000000', '2026-02-03 10:06:24.000000', '5', '', '', '', '90', 'A', 'FJR', '', '', '', 'GGD');
--code activite
INSERT INTO  sss_cdr_mapping  ( dmo ,  dou ,  id ,  att1 ,  att2 ,  att3 ,  codCibl ,  codSrc ,  ctab ,  descEvo ,  dom ,  libelle ,  uti )
VALUES ('2026-02-18 10:06:24.000000', '2026-02-03 10:06:24.000000', '3', '', '', '', '900', '100', 'SACT', '', '', '', 'GGD');
--nationalite
INSERT INTO  sss_cdr_mapping  ( dmo ,  dou ,  id ,  att1 ,  att2 ,  att3 ,  codCibl ,  codSrc ,  ctab ,  descEvo ,  dom ,  libelle ,  uti )
VALUES ('2026-02-18 10:06:24.000000', '2026-02-03 10:06:24.000000', '4', '', '', '', 'AFG', 'AFG', 'NATI', '', '', '', 'GGD');
--natURE CLIENT
INSERT INTO  sss_cdr_mapping  ( dmo ,  dou ,  id ,  att1 ,  att2 ,  att3 ,  codCibl ,  codSrc ,  ctab ,  descEvo ,  dom ,  libelle ,  uti )
VALUES ('2026-02-18 10:06:24.000000', '2026-02-03 10:06:24.000000', '6', '', '', '', '1', 'P', 'CDTYPT', '', '', '', 'GGD');

--client snapshot
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_STAT (altCodClient, entLieeEtab, dateDeclaration, dtRefent, codAgEcon, codClient, entDeclar, entObserv, id_client, natClient, actionType)
VALUES ('1', '000000', '2026-02-18 10:06:24.000000', '2026-02-18 10:06:24.000000', '100', '01212', 'bdhhdnhd', 'nbdbdbdbdb', '01212', 'P', 'ED');

--adresse

INSERT INTO  adresse  ( id ,  adresse ,  codLocal ,  codPays ,  codPostal ,  id_client ,  numTeleph )
VALUES ('1', 'hhgghggghgh', 'hhghgghghgh', 'AF', '545', '01212', '555415');
--donneees pm
INSERT INTO  donneesintpm  ( flagSuc ,  dtCreation ,  dtMod ,  id ,  codActPrinc ,  codActSec ,  codTrib ,  codlei ,  formJur ,  genre ,  groupAppart ,  ice ,  id_client ,  idFiscal ,  idPrincSiege ,  idSpecifique ,  natMod ,  numTaxeProf ,  raisonSocSiege ,  raisonSocial ,  regCommerce ,  sigle ,  tailleEntrep ,  tpIdPrincSiege )
VALUES (true, '2026-02-18 09:58:07.000000', '2026-02-18 09:58:07.000000', '1', '100', '100', '1', 'GG', 'A', 'VCFCGFG', 'FCGFGFG', 'VFVGFG', '01212', 'GFFGFG', 'VFVGFG', 'FGFGFG', 'VVVF', 'VVVGJHHJ', 'FGFGFGD', 'HGGVGH', 'BBVHJ', 'HJHHJ', '554', 'NGHFGCF');
--actionnaire
INSERT INTO  sss_cdr_snapshot_client_act  ( qtpartCapSocAct ,  id ,  codTribunAct ,  formJurAct ,  iceact ,  id_client ,  idPrincAct ,  idSpecifiqueAct ,  leiact ,  natActionnaire ,  nomRaisonSocAct ,  payResAct ,  regCommerAct ,  tpIdPrincAct )
VALUES ('11', '1', '1', 'A', 'sss', '01212', '96655', 'ddff', 'ffff', 'P', 'eee', 'AF', 'DDDD', 'FFFFH');
--beneficiaire
INSERT INTO SSS_CDR_SNAPSHOT_CLIENT_BENEF (ID, IDBENEFFECT, ID_CLIENT, NATBENEFFECT, NOMBENEFFECT, PREBENEFFECT, TYPIDBENEFFECT)
VALUES ('1', '15655', '01212', 'P', 'hjdshjdshj', 'hgszgs', 'hjjhdshj');