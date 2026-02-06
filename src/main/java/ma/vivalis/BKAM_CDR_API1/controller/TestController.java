package ma.vivalis.BKAM_CDR_API1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/")
    public String home() {
        return "Spring MVC fonctionne";
    }
}
