package ma.vivalis.BKAM_CDR_API1.common.models.lotSequence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lot_sequence")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LotSequence {
    @Id
    private Integer id = 1;

    @Column(nullable = false)
    private Integer val = 0;
}
