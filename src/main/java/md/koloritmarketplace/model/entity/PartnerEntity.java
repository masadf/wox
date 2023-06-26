package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

import javax.persistence.*;

@Data
@Entity
@Table(name = "partner", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "partnerid")
public class PartnerEntity extends AppEntity {
    @Column(name = "partnerid", insertable = false, updatable = false)
    private Long partnerId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;
}
