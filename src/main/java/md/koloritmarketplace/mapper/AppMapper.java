package md.koloritmarketplace.mapper;

import md.koloritmarketplace.model.dto.*;
import md.koloritmarketplace.model.dto.account.AccountAddressDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.model.dto.account.AccountRoleDto;
import md.koloritmarketplace.model.dto.brand.BrandDto;
import md.koloritmarketplace.model.dto.brand.BrandInputSaveDto;
import md.koloritmarketplace.model.dto.brand.BrandViewDto;
import md.koloritmarketplace.model.dto.bucket.BucketDto;
import md.koloritmarketplace.model.dto.input.CardInputDto;
import md.koloritmarketplace.model.dto.input.PromoCodeInput;
import md.koloritmarketplace.model.dto.item.ItemComplectationsDto;
import md.koloritmarketplace.model.dto.item.ItemDto;
import md.koloritmarketplace.model.dto.item.ItemShowListDto;
import md.koloritmarketplace.model.dto.option.OptionDto;
import md.koloritmarketplace.model.dto.option.OptionInputDto;
import md.koloritmarketplace.model.entity.*;
import md.koloritmarketplace.model.entity.account.AccountAddressEntity;
import md.koloritmarketplace.model.entity.account.AccountEntity;
import md.koloritmarketplace.model.entity.account.AccountRoleEntity;
import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import md.koloritmarketplace.model.entity.embeded.LanguageEmb;
import md.koloritmarketplace.model.entity.item.ItemComplectationsEntity;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import md.koloritmarketplace.model.entity.option.OptionEntity;
import md.koloritmarketplace.model.entity.view.BrandViewEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface AppMapper {
    ContactDto map(ContactEntity contactEntity);

    ContactEntity map(ContactDto contactEntity);


    CardEntity map(CardDto cardDto);

    @Mapping(target = "ownerId", source = "owner.accountId")
    @Mapping(target = "ownerFio", source = "owner.fio")
    CardDto map(CardEntity obj);

    @Mapping(target = "endDate", expression = "java(changeDateTimeFormatToS(obj.getEndDate()))")
    @Mapping(target = "startDate", expression = "java(changeDateTimeFormatToS(obj.getStartDate()))")
    @Mapping(target = "promoCodeId", source = "id")
    PromoCodeDto map(PromoCodeEntity obj);

    @Mapping(target = "endDate", expression = "java(changeDateTimeFormatToL(obj.getEndDate()))")
    @Mapping(target = "startDate", expression = "java(changeDateTimeFormatToL(obj.getStartDate()))")
    @Mapping(target = "id", source = "promoCodeId")
    @Mapping(target = "promoCodeId", source = "promoCodeId")
    PromoCodeEntity map(PromoCodeInput obj);

    PromoCodeEntity map(PromoCodeDto obj);

    @Mapping(target = "owner.accountId", source = "ownerId")
    @Mapping(target = "owner.id", source = "ownerId")
    @Mapping(target = "id", source = "cardId")
    @Mapping(target = "cardId", source = "cardId")
    CardEntity map(CardInputDto cardInputDto);

    CountryEntity map(CountryDto accountDto);

    CountryDto map(CountryEntity accountDto);

    AccountEntity map(AccountDto accountDto);

    @Mapping(target = "password", ignore = true)
    AccountDto map(AccountEntity accountEntity);

    AccountAddressDto map(AccountAddressEntity accountEntity);

    AccountAddressEntity map(AccountAddressDto accountEntity);

    @Mapping(target = "endDate", expression = "java(changeDateTimeFormatToL(actionDto.getEndDate()))")
    ActionEntity map(ActionDto actionDto);

    @Mapping(target = "endDate", expression = "java(changeDateTimeFormatToS(actionDto.getEndDate()))")
    ActionDto map(ActionEntity actionDto);

    default ImageDto getImageFromList(List<ImageEntity> list) {

        if (!list.isEmpty()) {

            return map(list.get(0));
        }
        return new ImageDto();
    }

    default String changeDateTimeFormatToS(LocalDate localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    default LocalDate changeDateTimeFormatToL(String s) {
        if (s == null) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(s, formatter);

    }

    PageDto map(PageEntity pageEntity);

    PageEntity map(PageDto pageDto);

    NewsDto map(NewsEntity newsEntity);

    NewsEntity map(NewsDto newsDto);

    ContactCompanyEntity map(ContactCompanyDto newsDto);

    ContactCompanyDto map(ContactCompanyEntity newsDto);

    PartnerDto map(PartnerEntity newsDto);

    PartnerEntity map(PartnerDto newsDto);


    AccountRoleEntity map(AccountRoleDto accountRoleDto);

    AccountRoleDto map(AccountRoleEntity accountRoleDto);

    LanguageEmb map(LanguageEmbDto languageEmb);

    LanguageEmbDto map(LanguageEmb languageEmb);

    AppEntity map(AppDto languageEmb);

    AppDto map(AppEntity languageEmb);

    BrandDto map(BrandEntity languageEmb);

    BrandEntity map(BrandDto languageEmb);

    BrandViewDto map(BrandViewEntity languageEmb);

    BrandViewDto mapBrandToView(BrandEntity obj);

    BrandEntity map(BrandViewDto obj);

    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "id", source = "brandId")
    BrandEntity map(BrandInputSaveDto obj);

    @Mapping(target = "brandId", source = "id")
    BrandInputSaveDto mapToBrandInput(BrandEntity obj);

    BucketEntity map(BucketDto languageEmb);

    BucketDto map(BucketEntity languageEmb);

    CategoryDto map(CategoryEntity languageEmb);

    CategoryEntity map(CategoryDto languageEmb);

    ImageEntity map(ImageDto languageEmb);

    ImageDto map(ImageEntity languageEmb);

    VideoDto map(VideoEntity videoEntity);

    VideoEntity map(VideoDto videoDto);

    ItemDto map(ItemEntity languageEmb);

    ItemEntity map(ItemDto languageEmb);

    @Mapping(target = "image", expression = "java(getImageFromList(obj.getImages()))")
    ItemShowListDto mapToItemShowList(ItemEntity obj);

    ItemComplectationsEntity map(ItemComplectationsDto obj);

    ItemComplectationsDto map(ItemComplectationsEntity obj);

    LinkOptionItemEntity map(LinkOptionItemDto languageEmb);

    LinkOptionItemDto map(LinkOptionItemEntity languageEmb);

    @Mapping(target = "optionId", source = "optionId")
    @Mapping(target = "id", source = "optionId")
    OptionEntity map(OptionDto obj);

    @Mapping(target = "optionId", source = "optionId")
    @Mapping(target = "id", source = "optionId")
    OptionEntity map(OptionInputDto obj);

    OptionDto map(OptionEntity languageEmb);

    @Mapping(target = "optionId", source = "id")
    OptionInputDto mapToInputDto(OptionEntity languageEmb);

    TypeMeasureEntity map(TypeMeasureDto languageEmb);

    TypeMeasureDto map(TypeMeasureEntity languageEmb);


    List<TypeMeasureEntity> mapToListToTypeMeasureEntity(List<TypeMeasureDto> accountDtos);

    List<TypeMeasureDto> mapToListToTypeMeasureDto(List<TypeMeasureEntity> accountDtos);

    List<OptionDto> mapToListToOptionDto(List<OptionEntity> accountDtos);

    List<OptionEntity> mapToListToOptionEntity(List<OptionDto> accountDtos);

    List<LinkOptionItemDto> mapToListToLinkOptionItemDto(List<LinkOptionItemEntity> accountDtos);

    List<LinkOptionItemEntity> mapToListToLinkOptionItemEntity(List<LinkOptionItemDto> accountDtos);

    List<ItemDto> mapToListToItemDto(List<ItemEntity> accountDtos);

    List<ItemEntity> mapToListToItemEntity(List<ItemDto> accountDtos);

    List<ImageDto> mapToListToImageDto(List<ImageEntity> accountDtos);

    List<ImageEntity> mapToListToImageEntity(List<ImageDto> accountDtos);

    List<CategoryEntity> mapToListToCategoryEntity(List<CategoryDto> accountDtos);

    List<CategoryDto> mapToListToCategoryDto(List<CategoryEntity> accountDtos);

    List<BucketEntity> mapToListToBucketEntity(List<BucketDto> accountDtos);

    List<BucketDto> mapToListToBucketDto(List<BucketEntity> accountDtos);

    List<BrandEntity> mapToListToBrandEntity(List<BrandDto> accountDtos);

    Set<BucketDto> mapToSetToBucketDto(Set<BucketEntity> accountDtos);

    List<BrandDto> mapToListToBrandDto(List<BrandEntity> lst);

    List<BrandViewDto> mapToListToBrandViewDto(List<BrandViewEntity> lst);

    List<AppDto> mapToListToAppDto(List<AppEntity> accountDtos);

    List<AppEntity> mapToListToAppEntity(List<AppDto> accountDtos);

    List<LanguageEmb> mapToListToLanguageEmb(List<LanguageEmbDto> accountDtos);

    List<LanguageEmbDto> mapToListToLanguageEmbDto(List<LanguageEmb> accountDtos);

    List<AccountEntity> mapToListToAccountEntity(List<AccountDto> accountDtos);

    List<AccountDto> mapToListToAccountDto(List<AccountEntity> accountEntities);

    List<AccountRoleDto> mapToListToAccountRoleDto(List<AccountRoleEntity> accountEntities);

    List<AccountRoleEntity> mapToListToAccountRoleEntity(List<AccountRoleDto> accountEntities);

    List<CountryDto> mapToListToCountryDto(List<CountryEntity> countryEntity);

    List<CountryEntity> mapToListToCountryEntity(List<CountryDto> countryDto);

    List<ActionDto> mapToListToActionDto(List<ActionEntity> countryDto);

    List<ActionEntity> mapToListToActionEntity(List<ActionDto> countryDto);

    List<NewsEntity> mapToListToNewsEntity(List<NewsDto> countryDto);

    List<NewsDto> mapToListToNewsDto(List<NewsEntity> countryDto);

    List<AccountAddressDto> mapToListToAccountAddressDto(List<AccountAddressEntity> accountEntity);

    List<AccountAddressEntity> mapToListToAccountAddressEntity(List<AccountAddressDto> accountEntity);

    Set<AccountAddressDto> mapToSetToAccountAddressDto(Set<AccountAddressEntity> accountEntity);

    Set<AccountAddressEntity> mapToSetToAccountAddressEntity(Set<AccountAddressDto> accountEntity);

    List<ContactCompanyDto> mapToListToContactCompanyDto(List<ContactCompanyEntity> newsDto);

    List<ContactCompanyEntity> mapToListToContactCompanyEntity(List<ContactCompanyDto> newsDto);

    List<PartnerEntity> mapToListToPartnerEntity(List<PartnerDto> newsDto);

    List<PartnerDto> mapToListToPartnerDto(List<PartnerEntity> newsDto);

    List<PageEntity> mapToListPageEntity(List<PageDto> pageDto);

    List<PageDto> mapToListPageDto(List<PageEntity> pageDto);

    List<CardDto> mapToListCardDto(List<CardEntity> pageDto);

    List<PromoCodeDto> mapToListPromoCodeDto(List<PromoCodeEntity> pageDto);

    List<CardEntity> mapToListCardEntity(List<CardDto> pageDto);

    List<CardEntity> mapToListCardEntityFromInput(List<CardInputDto> pageDto);

    List<VideoEntity> mapToListVideoEntity(List<VideoDto> pageDto);

    List<VideoDto> mapToListVideoDto(List<VideoEntity> pageDto);

    List<ContactDto> mapToListContactDto(List<ContactEntity> contactEntity);

    List<ContactEntity> mapToListContactEntity(List<ContactDto> contactEntity);

}
