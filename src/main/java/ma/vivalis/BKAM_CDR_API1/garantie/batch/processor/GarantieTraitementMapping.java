package ma.vivalis.BKAM_CDR_API1.garantie.batch.processor;

import generated.ComGar;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_garantie;
import ma.vivalis.BKAM_CDR_API1.garantie.model.sss_cdr_inter_garantie;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class GarantieTraitementMapping implements ItemProcessor<sss_cdr_inter_garantie, ComGar> {
    @Override
    public @Nullable ComGar process(sss_cdr_inter_garantie item) throws Exception {
        sss_cdr_garantie gar=mappingTraitement(item);
        ComGar comGar = mapGarantieToXmlElement(gar);
        return comGar;
    }

    private ComGar mapGarantieToXmlElement(sss_cdr_garantie item) throws DatatypeConfigurationException {
        ComGar comGar= new ComGar();
        ComGar.Garantie gar=new ComGar.Garantie();

        gar.setActionType(String.valueOf(item.getActionType()));
        gar.setDtRefGar(convertDateToXml(item.getDtRefGar()));
        gar.setDtCreatGar(convertDateToXml(item.getDtCreatGar()));
        gar.setDtFinGar(convertDateToXml(item.getDtFinGar()));
        gar.setRenGar(item.getRenGar());
        gar.setDtRenGar(convertDateToXml(item.getDtRenGar()));
        gar.setDtFinRenGar(convertDateToXml(item.getDtFinRenGar()));
        gar.setTpGar(item.getTpGar());
        gar.setCodClient(item.getCodClient());
        gar.setCodGarExt(item.getCodGarExt());
        gar.setTpRefExtGar(item.getTpRefExtGar());
        gar.setRefExtGar(item.getRefExtGar());
        gar.setPrixAcqProp(BigDecimal.valueOf(item.getPrixAcqProp()));
        gar.setCodLocalGar(item.getCodLocalGar());
        gar.setMontGar(BigDecimal.valueOf(item.getMontGar()));
        gar.setValOriGar(BigDecimal.valueOf(item.getValOriGar()));
        gar.setTpValInGar(item.getTpValInGar());
        gar.setValActGar(BigDecimal.valueOf(item.getValActGar()));
        gar.setDtEvalGar(convertDateToXml(item.getDtEvalGar()));
        gar.setTpValActGar(item.getTpValActGar());
        gar.setGarEtat(item.getGarEtat());
        gar.setNvGarAdossCred(item.getNvGarAdossCred());
        gar.setEtatExecGar(item.getEtatExecGar());
        gar.setDtExecGar(convertDateToXml(item.getDtExecGar()));
        comGar.getGarantie().add(gar);
        return comGar;
    }
    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }


    private sss_cdr_garantie mappingTraitement(sss_cdr_inter_garantie item) {

        sss_cdr_garantie gar = sss_cdr_garantie.builder()

                .idGar(item.getIdGar())
                .id_lot(item.getId_lot())
                .dateExtraction(item.getDateExtraction())
                .actionType(item.getActionType())
                .dtRefGar(item.getDtRefGar())
                .dtCreatGar(item.getDtCreatGar())
                .dtFinGar(item.getDtFinGar())
                .renGar(item.getRenGar())
                .dtRenGar(item.getDtRenGar())
                .dtFinRenGar(item.getDtFinRenGar())
                .tpGar(item.getTpGar())
                .codClient(item.getCodClient())
                .codGarExt(item.getCodGarExt())
                .tpRefExtGar(item.getTpRefExtGar())
                .refExtGar(item.getRefExtGar())
                .prixAcqProp(item.getPrixAcqProp())
                .codLocalGar (item.getCodLocalGar())
                .montGar(item.getMontGar())
                .valOriGar(item.getValOriGar())
                .tpValInGar(item.getTpValInGar())
                .valActGar(item.getValActGar())
                .dtEvalGar(item.getDtEvalGar())
                .tpValActGar(item.getTpValActGar())
                .garEtat(item.getGarEtat())
                .nvGarAdossCred(item.getNvGarAdossCred())
                .etatExecGar(item.getEtatExecGar())
                .dtExecGar(item.getDtExecGar())
                .build();

        return gar;
    }
}
