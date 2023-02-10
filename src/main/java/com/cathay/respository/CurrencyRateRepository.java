package com.cathay.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cathay.entity.CurrencyRate;

public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long>, JpaSpecificationExecutor<CurrencyRate> {
	
	@Query(value ="select * from CURRENCY_RATE  where currency = ?",nativeQuery = true)
	List<CurrencyRate> findByCurrency(String currency);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value  ="update CURRENCY_RATE  set rate = ?1, currency_name = ?2, memo= ?3 ,update_date=(LOCALTIMESTAMP - INTERVAL '0' MINUTE) ,update_by= ?4 where currency =  ?5")
	void updateByCurrency(float rate, String currencyName,String memo,String createdBy, String currency);
	

}
