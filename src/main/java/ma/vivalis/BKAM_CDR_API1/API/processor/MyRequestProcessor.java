package ma.vivalis.BKAM_CDR_API1.API.processor;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.MyResponseBody;
import ma.vivalis.BKAM_CDR_API1.API.model.sss_cdr_api1;
import ma.vivalis.BKAM_CDR_API1.API.service.ApiService;
import ma.vivalis.BKAM_CDR_API1.client.repository.sss_cdr_inter_client_stat_Repository;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyRequestProcessor implements ItemProcessor<MyRequestBody, sss_cdr_api1> {
    private final ApiService apiService;
    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;
    public MyRequestProcessor(ApiService apiService, sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository) {
        this.apiService = apiService;
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
    }

    @Override
    public @Nullable sss_cdr_api1 process(MyRequestBody item) throws Exception {
        Date dateDeclaration=sss_cdr_inter_client_stat_Repository.findDateExtractionByMaxId_lot();
        MyResponseBody resultatApi = apiService.sendDataToApi(item);
        sss_cdr_api1 api1=sss_cdr_api1.builder()
                .id_Lot(resultatApi.getId_Lot())
                .dateExtraction(dateDeclaration)
                .msg(resultatApi.getMsg())
                .codMsg(resultatApi.getCodMsg())

                .build();
        return api1;
    }
}
