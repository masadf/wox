package md.koloritmarketplace.model.entity;

import lombok.Data;
import lombok.ToString;
import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "actions", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "actionsid")
public class ActionEntity  extends AppEntity{
    @Column(name = "actionsid", insertable = false, updatable = false)
    private Long actionsId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "rutitlevalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "entitlevalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rotitlevalue"))
    })
    private LanguageEmb languageTitleEmb;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "rucontentvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "encontentvalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rocontentvalue"))
    })
    private LanguageEmb languageContentEmb;

    @Column( name = "enddate")
    private LocalDate endDate;

    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = { CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "linkitemaction", schema = "marketplace",
            joinColumns = {
                    @JoinColumn(name = "actionsid")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "itemid")
            }
    )
    private Set<ItemEntity> items = new HashSet<>();

    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.ACTION;
    }


}
