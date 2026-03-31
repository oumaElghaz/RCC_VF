package ma.vivalis.BKAM_CDR_API1.API.service;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.MyResponseBody;
import ma.vivalis.BKAM_CDR_API1.client.repository.sss_cdr_inter_client_stat_Repository;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class ApiService {
    @Autowired
    private WebClient webClient;
    private final LotSequenceRepository lotSequenceService;
    private final sss_cdr_inter_client_stat_Repository sss_cdr_inter_client_stat_Repository;

    public ApiService(LotSequenceRepository lotSequenceService, sss_cdr_inter_client_stat_Repository sssCdrInterClientStatRepository) {
        this.lotSequenceService = lotSequenceService;
        sss_cdr_inter_client_stat_Repository = sssCdrInterClientStatRepository;
    }

    public MyResponseBody sendDataToApi(MyRequestBody body) {
        String url = "https://cdr-int-extranet.bankalmaghrib.ma/ma.cdr.extranet.api/Report/EnvoiDeclarationRCC";
        int lot_id=lotSequenceService.findMaxVal();
        Date dateDeclaration=sss_cdr_inter_client_stat_Repository.findDateExtractionByMaxId_lot();
        return webClient.post()
                .uri(url)
                .header("serviceBAM", "RCC")
                .header("id_Lot", String.valueOf(lot_id))
                .header("declarant", "415")
                .header("remettant", "415")
                .header("recepteur", "001")
                .header("dateDeclaration", String.valueOf(dateDeclaration))
                .header("content-type", "application/json")
                .header("login", "APIVIV01")
                .header("Password_hash", "2ee3175ff464b2dbc86898640c60bbf47df2a6b09e96f68a3a079304a014f87f")
                .header("nonce", "0001")
                .body(Mono.just(body), MyRequestBody.class)
                .retrieve()
                .bodyToMono(MyResponseBody.class)
                .block(); // Attention : block() rend synchrone, sinon retourne Mono<String>
    }
}
