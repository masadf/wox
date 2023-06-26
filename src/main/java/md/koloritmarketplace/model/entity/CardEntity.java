package md.koloritmarketplace.model.entity;

import lombok.Data;
import md.koloritmarketplace.model.entity.account.AccountEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "card", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "cardid")
public class CardEntity extends AppEntity {

    @Column(name = "cardid", insertable = false, updatable = false)
    private Long cardId;
    @Column(name = "numbercard")
    private String numberCard;
    @Column(name = "percentofcard")
    private Long percent;

    @ManyToOne
    @JoinColumn(name = "accountid", referencedColumnName = "accountid")
    private AccountEntity owner;
}
