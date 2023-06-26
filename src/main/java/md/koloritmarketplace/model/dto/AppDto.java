package md.koloritmarketplace.model.dto;

import lombok.Data;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.model.entity.ImageEntity;
import md.koloritmarketplace.model.enums.ObjectStatus;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppDto {
    private Long id;
    private ObjectStatus status;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Integer isAll;
    private ObjectTypeEnum type;
    private AccountDto accountEntity;
    private List<ImageDto> images;
//    private List<ImageEntity> videos;
}
