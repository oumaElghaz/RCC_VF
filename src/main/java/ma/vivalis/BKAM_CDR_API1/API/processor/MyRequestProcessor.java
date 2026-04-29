package ma.vivalis.BKAM_CDR_API1.API.processor;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.MyResponseBody;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import ma.vivalis.BKAM_CDR_API1.API.service.ApiService;
import ma.vivalis.BKAM_CDR_API1.client.repository.sss_cdr_inter_client_stat_Repository;
import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class MyRequestProcessor implements ItemProcessor<MyRequestBody, sss_cdr_api1> {
    private final ApiService apiService;
    private static final Logger log = LoggerFactory.getLogger(MyRequestProcessor.class);
    //private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    public MyRequestProcessor(ApiService apiService//, sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository
                               ) {
        this.apiService = apiService;
        //sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
    }

    @Override
    public @Nullable sss_cdr_api1 process(MyRequestBody item) throws Exception {
        String dateDec = FileNameService.getFormattedDate();

        // ✅ Pattern correct pour "20260428.194607"
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(dateDec, inputFormatter);
        String dateDeclaration = dateTime.format(outputFormatter);

        MyResponseBody resultatApi = apiService.sendDataToApi(item);
        sss_cdr_api1 api1 = sss_cdr_api1.builder()
                .id_Lot(resultatApi.getId_Lot())
                .dateExtraction(LocalDateTime.parse(dateDeclaration, outputFormatter)) // ✅ parser avec outputFormatter
                .msg(resultatApi.getMsg())
                .codMsg(resultatApi.getCodMsg())
                .build();

        log.info("Résultat de l'API : id_Lot={}, msg={}, codMsg={}, dateExtraction={}",
                api1.getId_Lot(), api1.getMsg(), api1.getCodMsg(), api1.getDateExtraction());
        return api1;
    }
}
