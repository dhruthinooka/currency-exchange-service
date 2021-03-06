package com.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	Logger logger= LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable("from") String fromCurr, @PathVariable("to") String toCurr)
	{
		ExchangeValue exchangeValue = repository.findByFromAndTo(fromCurr, toCurr);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		logger.info("Exchange value is {}",exchangeValue);
		return exchangeValue;
		
	}
}
