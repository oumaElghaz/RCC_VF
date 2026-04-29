package ma.vivalis.BKAM_CDR_API1.API.service;

import ma.vivalis.BKAM_CDR_API1.API.model.MyRequestBody;
import ma.vivalis.BKAM_CDR_API1.API.model.MyResponseBody;

import ma.vivalis.BKAM_CDR_API1.common.FileNameService;
import ma.vivalis.BKAM_CDR_API1.common.repository.lotSequence.LotSequenceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class ApiService {
    @Autowired
    private WebClient webClient;
    private final LotSequenceRepository lotSequenceService;
    private static final Logger log = LoggerFactory.getLogger(ApiService.class);


    public ApiService(LotSequenceRepository lotSequenceService) {
        this.lotSequenceService = lotSequenceService;

    }

    public MyResponseBody sendDataToApi(MyRequestBody body) {
        String url = "https://cdr-int-extranet.bankalmaghrib.ma/ma.cdr.extranet.api/Report/EnvoiDeclarationRCC";
        Integer lot_id=lotSequenceService.findMaxVal();
        log.info(" lot_id : {}",lot_id);
        //LocalDateTime dateDeclaration=sss_cdr_inter_client_stat_Repository.findDateExtractionByMaxId_lot();
        String dateDec = FileNameService.getFormattedDate();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(dateDec, inputFormatter);
        String dateDeclaration = dateTime.format(outputFormatter);
        log.info(" dateDeclaration : {}",dateDeclaration);
        return webClient.post()
                .uri(url)
                .header("serviceBAM", "RCC")
                .header("id_Lot", String.valueOf(lot_id))
                .header("declarant", "415")
                .header("remettant", "415")
                .header("recepteur", "001")
                .header("dateDeclaration", dateDeclaration)
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
