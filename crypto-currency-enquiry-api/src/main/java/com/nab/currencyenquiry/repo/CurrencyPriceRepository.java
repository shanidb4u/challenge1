package com.nab.currencyenquiry.repo;

import com.nab.currencyenquiry.domain.entity.CurrencyPriceEntity;
import com.nab.currencyenquiry.domain.entity.CurrencyPriceId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CurrencyPriceRepository extends CrudRepository<CurrencyPriceEntity, CurrencyPriceId> {

    @Query("select cpe FROM CurrencyPriceEntity cpe WHERE cpe.id.currencyName = :currencyName and cpe.id.currencyDate = :currencyDate order by cpe.id.currencyTime")
    List<CurrencyPriceEntity> findPricesByCurrencyAndDate(@Param("currencyName") String currencyName, @Param("currencyDate") LocalDate currencyDate);
}
