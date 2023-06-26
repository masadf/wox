package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.entity.option.OptionEntity;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "category", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "categoryid")
public class CategoryEntity extends AppEntity {
    @Column(name = "categoryid", insertable = false, updatable = false)
    private Long categoryId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
    })
    private LanguageEmb languageEmb;

    @Column(name = "parentid")
    private Long parentId;

    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CategoryEntity> childs;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OptionEntity> options;
    @Transient
    public ObjectTypeEnum getObjectTypeEnum() {
        return ObjectTypeEnum.CATEGORY;
    }
}
