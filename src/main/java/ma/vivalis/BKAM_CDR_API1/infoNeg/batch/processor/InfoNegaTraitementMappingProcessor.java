package ma.vivalis.BKAM_CDR_API1.infoNeg.batch.processor;

import generated.ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.common.MappingLoader;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.sss_cdr_inter_infoNegative;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_ComInfNeg;
import ma.vivalis.BKAM_CDR_API1.infoNeg.model.util.sss_cdr_InfNeg;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class InfoNegaTraitementMappingProcessor implements ItemProcessor<sss_cdr_inter_infoNegative, List<ComInfNeg>> {

    private static final Logger log = LoggerFactory.getLogger(InfoNegaTraitementMappingProcessor.class);
    private final MappingLoader mappingLoader;

    public InfoNegaTraitementMappingProcessor(MappingLoader mappingLoader) {
        this.mappingLoader = mappingLoader;
    }


    @Override
    public @Nullable List<ComInfNeg> process(sss_cdr_inter_infoNegative item) throws Exception {
        List<ComInfNeg> liste=new ArrayList<>();
        sss_cdr_infoNegative j=mappingTraitement(item);

        for (sss_cdr_ComInfNeg r: j.getComInfNegs()){
            ComInfNeg comInfNeg =mapInfoNegaToXML(r);
            liste.add(comInfNeg);

        }

        return liste;
    }


    private sss_cdr_infoNegative mappingTraitement(sss_cdr_inter_infoNegative inter){

        sss_cdr_infoNegative f=sss_cdr_infoNegative.builder()
                .id(inter.getId())
                .id_lot(inter.getId_lot())
                .dateExtraction(inter.getDateExtraction())
                //.idDest(inter.getIdDest())
                //.entDeclar(inter.getEntDeclar())
                //.entObserv(inter.getEntObserv())


                .build();


        if (inter.getComInfNegs() != null) {
            List<sss_cdr_ComInfNeg> infFinal = inter.getComInfNegs().stream()
                    .map(inf -> {
                        sss_cdr_ComInfNeg a = sss_cdr_ComInfNeg.builder()
                                .dtRef(inf.getDtRef())
                                .build();
                        if(inf.getInfNegList()!= null){
                            List<sss_cdr_InfNeg> g=inf.getInfNegList().stream().map(r-> {sss_cdr_InfNeg e=sss_cdr_InfNeg.builder()
                                    .actionType(r.getActionType())
                                    .codClient(r.getCodClient())
                                    .dtObsInfNegInc(r.getDtObsInfNegInc())
                                    .dtSortie(r.getDtSortie())
                                    .montInc(r.getMontInc())
                                    .refInfoNeg(r.getRefInfoNeg())
                                    .statInfoNeg(r.getStatInfoNeg())
                                    .tpInfNegInc(mappingLoader.map("T_INI", r.getTpInfNegInc()))
                                    .build();
                                e.setComInf(a);;  // ← Lier au parent comInf
                                return e;
                            }).collect(Collectors.toList());
                            a.setInfNegList(g);}

                        a.setInfoNeg(f); // ← Lier au parent archiv
                        return a;
                    }).collect(Collectors.toList());
            f.setComInfNegs(infFinal);
        }
        return f;
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
