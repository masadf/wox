package md.koloritmarketplace.model.entity.embeded;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class LanguageEmb implements Serializable {
    @Column(name = "envalue")
    private String enValue;
    @Column(name = "ruvalue")
    private String ruValue;
    @Column(name = "rovalue")
    private String roValue;
}
