package md.koloritmarketplace.service;

import md.koloritmarketplace.model.dto.CountryDto;
import md.koloritmarketplace.model.dto.account.AccountDto;

import java.util.List;

public interface CountryService {

    CountryDto addCountryDto(CountryDto countryDto);

    CountryDto updateCountryDto(CountryDto countryDto);

    List<CountryDto> listCountryDto();
}
