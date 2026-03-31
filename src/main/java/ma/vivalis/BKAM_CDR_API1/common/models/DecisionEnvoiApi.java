package ma.vivalis.BKAM_CDR_API1.common.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DecisionEnvoiApi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DecisionEnvoiApi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Boolean decision;
}
