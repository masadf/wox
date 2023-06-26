package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contactcompany", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "contactcompanyid")
public class ContactCompanyEntity extends AppEntity {

    @Column(name = "contactcompanyid", insertable = false, updatable = false)
    private Long contactCompanyId;
    @Column(name = "numberphone")
    private String numberPhone;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "addressru")),
            @AttributeOverride(name = "enValue", column = @Column(name = "addressen")),
            @AttributeOverride(name = "roValue", column = @Column(name = "addressro"))
    })
    private LanguageEmb addr;
//
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "timeworkoneru")),
            @AttributeOverride(name = "enValue", column = @Column(name = "timeworkoneen")),
            @AttributeOverride(name = "roValue", column = @Column(name = "timeworkonero"))
    })
    private LanguageEmb timeWorkOne;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "timeworktworu")),
            @AttributeOverride(name = "enValue", column = @Column(name = "timeworktwoen")),
            @AttributeOverride(name = "roValue", column = @Column(name = "timeworktworo"))
    })
    private LanguageEmb timeWorkTwo;
}
