//package md.koloritmarketplace.model.entity.option;
//
//import lombok.Data;
//import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table(name = "optionscontent", schema = "marketplace")
//public class OptionContentEntity {
//    @Id
//    @SequenceGenerator(name = "IdGenerator", sequenceName = "marketplace.itemdopinfo_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IdGenerator")
//    @Column(name = "optionscontentid")
//    private Long optionsContentId;
//
//    @Column(name = "optionid")
//    private Long optionId;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "ruValue", column = @Column(name = "ruvalue")),
//            @AttributeOverride(name = "enValue", column = @Column(name = "envalue")),
//            @AttributeOverride(name = "roValue", column = @Column(name = "rovalue"))
//    })
//    private LanguageEmb languageEmb;
//
//}
