package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor;

import generated.ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_InfNeg;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Component
public class InfoNegaJaxbProcessor implements ItemProcessor<sss_cdr_infoNegative, List<ComInfNeg>> {

    @Override
    public @Nullable List<ComInfNeg> process(sss_cdr_infoNegative item) throws Exception {
        List<ComInfNeg> listeComInfNeg = new ArrayList<>();
        for (sss_cdr_ComInfNeg r: item.getComInfNegs()){
        ComInfNeg comInfNeg =mapInfoNegaToXML(r);
            listeComInfNeg.add(comInfNeg);

        }


        return listeComInfNeg;
    }


    private ComInfNeg mapInfoNegaToXML(sss_cdr_ComInfNeg infNega) throws DatatypeConfigurationException {

        if (infNega == null) {
            throw new IllegalArgumentException("Information Negative null !");
        }

        ComInfNeg comInfNeg =new ComInfNeg();
        List<ComInfNeg.InfNeg> infosList= new ArrayList<>();

        comInfNeg.setDtRef(String.valueOf(infNega.getDtRef()));

        if (infNega.getInfNegList() != null && !infNega.getInfNegList().isEmpty()){
        for(sss_cdr_InfNeg g :infNega.getInfNegList()){
            ComInfNeg.InfNeg element=mapInfonegaElemenetTOXML(g);
            infosList.add(element);


        }
        comInfNeg.getInfNeg().addAll(infosList);
        }

        return comInfNeg;
    }


    private ComInfNeg.InfNeg mapInfonegaElemenetTOXML(sss_cdr_InfNeg u) throws DatatypeConfigurationException {

        ComInfNeg.InfNeg h= new ComInfNeg.InfNeg();
        h.setActionType(String.valueOf(u.getActionType()));
        h.setCodClient(u.getCodClient());
        h.setRefInfoNeg(u.getRefInfoNeg());
        h.setTpInfNegInc(u.getTpInfNegInc());
        h.setDtObsInfNegInc(convertDateToXml(u.getDtObsInfNegInc()));
        h.setMontInc(BigDecimal.valueOf(u.getMontInc()));
        h.setStatInfoNeg(Boolean.valueOf(u.getStatInfoNeg()));
        h.setDtSortie(convertDateToXml(u.getDtSortie()));


        return h;


    }

    private XMLGregorianCalendar convertDateToXml(Date date) throws DatatypeConfigurationException {
        if (date == null) return null;
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    }


}
