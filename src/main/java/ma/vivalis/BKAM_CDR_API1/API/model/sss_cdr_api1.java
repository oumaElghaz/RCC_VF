package ma.vivalis.BKAM_CDR_API1.API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "sss_cdr_api1")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class sss_cdr_api1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int id_Lot;
    private String codMsg;
    private String msg;
    private LocalDateTime dateExtraction;



}
