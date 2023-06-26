package md.koloritmarketplace.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "contacts", schema = "marketplace")
@PrimaryKeyJoinColumn(name = "contactid")
public class ContactEntity extends AppEntity{
    @Column(name = "contactid", insertable = false, updatable = false)
    private Long contactId;
    @Column(name = "value")
    private String value;
    @Column(name = "contacttype")
    private String contactType;
}
