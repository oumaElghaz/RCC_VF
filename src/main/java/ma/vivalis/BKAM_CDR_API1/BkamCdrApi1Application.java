package ma.vivalis.BKAM_CDR_API1;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BkamCdrApi1Application //implements CommandLineRunner
{

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(BkamCdrApi1Application.class, args)));
    }

}