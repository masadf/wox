package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.exceptions.ReportException;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.bucket.BucketDto;
import md.koloritmarketplace.model.dto.report.BucketContentReportDto;
import md.koloritmarketplace.model.entity.AppEntity;
import md.koloritmarketplace.model.entity.bucket.BucketContentEntity;
import md.koloritmarketplace.model.entity.bucket.BucketEntity;
import md.koloritmarketplace.model.entity.item.ItemEntity;
import md.koloritmarketplace.model.enums.BuckeStatusEnum;
import md.koloritmarketplace.model.enums.ObjectTypeEnum;
import md.koloritmarketplace.repository.BucketRepository;
import md.koloritmarketplace.service.AccountService;
import md.koloritmarketplace.service.BucketService;
import md.koloritmarketplace.service.ItemService;
import md.koloritmarketplace.util.ReportFormatEnum;
import md.koloritmarketplace.util.ReportFormatFactory;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

import static md.koloritmarketplace.repository.specification.BucketSpec.*;

@Service
@RequiredArgsConstructor
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final AppMapper appMapper;
    private final ItemService itemService;
    private final AccountService accountService;

    @Override
    public BucketDto getBucketDto(Long bucketId) {
        BucketEntity bucketEntity = bucketRepository.findById(bucketId).get();
        return appMapper.map(bucketEntity);
    }


    @Override
    public BucketDto addBucketDto(BucketDto bucketDto) {
        BucketEntity bucketEntity = appMapper.map(bucketDto);
        bucketEntity.setOrderCode(generateCode());
        bucketEntity.setType(ObjectTypeEnum.BUCKET);
        Set<BucketContentEntity> set = bucketEntity.getContent();

        bucketEntity = bucketRepository.save(bucketEntity);
        if (!set.isEmpty()) {
            bucketEntity.setContent(set);
            bucketEntity = bucketRepository.save(bucketEntity);
        }


        return appMapper.map(bucketEntity);
    }

    @Override
    @Transactional
    public BucketDto updateBucketDto(BucketDto bucketDto) {

        BucketEntity bucketEntity = appMapper.map(bucketDto);
        bucketEntity.setType(ObjectTypeEnum.BUCKET);
        Long id = bucketEntity.getId();
        bucketEntity.getContent().stream().forEach(r -> {
            r.setBucketId(id);
        });
        bucketEntity = changeStatusOfBucket(bucketEntity);
        return appMapper.map(bucketEntity);
    }

    @Override
    public Page<BucketDto> listBucketDto(Pageable pageable, Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }

        String accountId = params.get("accountId");
        String bucketStatus = params.get("bucketStatus");
        String bucketId = params.get("bucketId");
        String accountName = params.get("accountName");
        String phoneNumber = params.get("phoneNumber");
        String minAmount = params.get("minAmount");
        String maxAmount = params.get("maxAmount");

        Specification<BucketEntity> specification = Specification
                .where(StringUtils.hasText(accountId) ? accountIdEqual(Long.parseLong(params.get("accountId"))) : null)
                .and(StringUtils.hasText(bucketStatus) ? bucketStatusEqual(BuckeStatusEnum.valueOf(bucketStatus)) : null)
                .and(StringUtils.hasText(bucketId) ? bucketIdEqual(Long.parseLong(bucketId)) : null)
                .and(StringUtils.hasText(accountName) ? likeFio(accountName.toUpperCase()) : null)
                .and(StringUtils.hasText(phoneNumber) ? likePhoneNumber(phoneNumber) : null)
                .and(StringUtils.hasText(minAmount) ? bucketGreaterAmount(Double.parseDouble(minAmount)) : null)
                .and(StringUtils.hasText(maxAmount) ? bucketLessAmount(Double.parseDouble(maxAmount)) : null);

        Page<BucketEntity> list = bucketRepository.findAll(specification, pageable);
        Page<BucketDto> pageDTOs =
                new PageImpl<>(appMapper.mapToListToBucketDto(list.getContent()), list.getPageable(), list.getTotalElements());
        return pageDTOs;
    }

    @Override
    public void generatePdf(HttpServletResponse response, Long id) throws ReportException {
        BucketEntity bucketEntity = bucketRepository.findById(id).get();
        Map<String, Object> params = getTitleParamsStatement(bucketEntity);

        List<BucketContentReportDto> list = new ArrayList<>();
        bucketEntity.getContent().stream().forEach(r -> {
            BucketContentReportDto bucketContentReportDto = BucketContentReportDto.builder()
                    .itemName(r.getItem().getName())
                    .countUnit(r.getCountUnit())
                    .amountUnit(r.getAmountUnit())
                    .amount(r.getAmount())
                    .build();
            list.add(bucketContentReportDto);
        });

        JRBeanCollectionDataSource reportDS = new JRBeanCollectionDataSource(list);
        if (list.size() != 0)
            params.put(JRParameter.REPORT_DATA_SOURCE, reportDS);
        params.put(JRParameter.REPORT_LOCALE, new Locale("ru", "RU"));

        params.put("date", formatDate(LocalDateTime.now()));

        ReportFormatFactory reportFormatFactory = new ReportFormatFactory(ReportFormatEnum.PDF_FORMAT, "Invoice", ("Invoice-" + bucketEntity.getOrderCode()), params, response);
        reportFormatFactory.getReport();

    }

    private String generateCode() {
        Integer min = 0;
        Integer max = 999999999;
        return String.valueOf(ThreadLocalRandom.current().nextLong(min, max + 1));
    }

    private Map<String, Object> getTitleParamsStatement(AppEntity appEntity) {
        BucketEntity bucketEntity = (BucketEntity) appEntity;

        Map<String, Object> params = new HashMap();
        params.put("accountName", bucketEntity.getBuyer().getFio());
        if (bucketEntity.getAddress() != null)
            params.put("address", bucketEntity.getAddress().getAdress());
        params.put("phoneNumber", bucketEntity.getAddress().getPhoneNumber());
        params.put("orderCode", bucketEntity.getOrderCode());
        params.put("typePay", getStatusPayTypeInLanguage(bucketEntity));
        params.put("deliveryType", getStatusDeliveryInLanguage(bucketEntity));
        if (bucketEntity.getBucketStatus() != null) {
            params.put("status", getStatusInLanguage(bucketEntity));

        }

        params.put("amount", bucketEntity.getAmount());

        return params;
    }

    private String formatDate(LocalDateTime localDateTime) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String text = date.format(formatter);
//        LocalDate parsedDate = LocalDate.parse(text, formatter);
        return text;
    }

    private BucketEntity changeStatusOfBucket(BucketEntity bucketEntity) {
        switch (bucketEntity.getBucketStatus()) {
            case SEND:
                return sendToOperator(bucketEntity);
            case PROCESS:
                return processOfOperator(bucketEntity);
            case DECLINE:
                return declineOrder(bucketEntity);
            default:
                return bucketRepository.save(bucketEntity);
        }
    }

    private BucketEntity sendToOperator(BucketEntity bucketEntity) {
        List<Long> ids = new ArrayList<>();
        bucketEntity.getContent().stream().forEach(r -> {
            ids.add(r.getItem().getItemId());
        });
        List<ItemEntity> itemEntities = itemService.findInIds(ids);
        itemEntities.stream().forEach(r -> {
            bucketEntity.getContent().stream().forEach(id -> {
                if (r.getId().equals(id.getItem().getItemId())) {
                    Long count = r.getCount() - id.getCountUnit();
                    if (count < 0) {
                        new RuntimeException("Не достаточно количества товара");
                    } else {
                        r.setCount(count);
                    }
                    id.setAmountUnit(r.getDisAmount() == null ? r.getAmount() : r.getDisAmount());
                    id.setAmount(id.getCountUnit() * id.getAmountUnit());
                }
            });
        });
        itemService.saveAll(itemEntities);
        AtomicReference<Double> amountOfBucket = new AtomicReference<>(0D);
        bucketEntity.getContent().stream().forEach(r -> {
            amountOfBucket.updateAndGet(v -> v + r.getAmount());
        });
        bucketEntity.setAmount(amountOfBucket.get());
        return bucketRepository.save(bucketEntity);
    }

    private BucketEntity processOfOperator(BucketEntity bucketEntity) {
        return bucketEntity;
    }

    private BucketEntity declineOrder(BucketEntity bucketEntity) {
        BucketEntity bucketEntityFromDb = bucketRepository.findById(bucketEntity.getId()).get();
        List<Long> ids = new ArrayList<>();
        bucketEntityFromDb.setBucketStatus(BuckeStatusEnum.DECLINE);

        bucketEntityFromDb.getContent().stream().forEach(r -> {
            ids.add(r.getItem().getItemId());
        });
        List<ItemEntity> itemEntities = itemService.findInIds(ids);
        itemEntities.stream().forEach(r -> {
            bucketEntityFromDb.getContent().stream().forEach(id -> {
                r.setCount(r.getCount() + id.getCountUnit());
            });
        });
        itemService.saveAll(itemEntities);
        bucketEntity = bucketRepository.save(bucketEntityFromDb);
        return bucketEntity;
    }

    private String getStatusInLanguage(BucketEntity bucketEntity) {
        switch (bucketEntity.getBucketStatus()) {
            case SEND:
                return "Отправлен на обработку";
            case PROCESS:
                return "На обработке";
            case DECLINE:
                return "Отменен";
            default:
                return "Другой статус";
        }
    }

    private String getStatusPayTypeInLanguage(BucketEntity bucketEntity) {
        switch (bucketEntity.getTypePay()) {
            case CARD:
                return "Оплата по карте";
            case BANK:
                return "Оплата банковским счетом";
            case CASH:
                return "Оплата наличными";
            default:
                return "Другой статус";
        }
    }

    private String getStatusDeliveryInLanguage(BucketEntity bucketEntity) {
        switch (bucketEntity.getTypeDelivery()) {
            case DELIVERY:
                return "Доставка";
            case PICKUP:
                return "Самовывоз";

            default:
                return "Другой статус";
        }
    }
}
