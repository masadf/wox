package md.koloritmarketplace.service.impl;

import lombok.RequiredArgsConstructor;
import md.koloritmarketplace.mapper.AppMapper;
import md.koloritmarketplace.model.dto.CountryDto;
import md.koloritmarketplace.model.dto.account.AccountDto;
import md.koloritmarketplace.model.entity.CountryEntity;
import md.koloritmarketplace.repository.CountryRepository;
import md.koloritmarketplace.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl  implements CountryService {
    private  final CountryRepository countryRepository;
    private final AppMapper appMapper;

    @Override
    public CountryDto addCountryDto(CountryDto countryDto) {
        CountryEntity countryEntity=appMapper.map(countryDto);
        countryEntity=countryRepository.save(countryEntity);
        return appMapper.map(countryEntity);
    }

    @Override
    public CountryDto updateCountryDto(CountryDto countryDto) {
        CountryEntity countryEntity=appMapper.map(countryDto);
        countryEntity=countryRepository.save(countryEntity);
        return appMapper.map(countryEntity);
    }

    @Override
    public List<CountryDto> listCountryDto() {
        return appMapper.mapToListToCountryDto(countryRepository.findAll());
    }
}
