package md.koloritmarketplace.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "promocode", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "promocodeid")
public class PromoCodeEntity extends AppEntity {

    @Column(name = "promocodeid", insertable = false, updatable = false)
    private Long promoCodeId;
    @Column(name = "promocode")
    private String promoCode;
    @Column(name = "percent")
    private Long percent;

    @Column(name = "startdate")
    private LocalDate startDate;

    @Column(name = "enddate")
    private LocalDate endDate;
}
