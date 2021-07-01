package com.redhat.fuse.boosters;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.knowm.xchange.dto.marketdata.Ticker;

@ApplicationScoped
public class TickerRoute extends RouteBuilder {

	@ConfigProperty(name = "ticker2log.xchange.currencypair") 
	String pair;
	
	@Override
    public void configure() throws Exception {
        from("timer:ticker?period=2500")
	        .to("xchange:binance?service=marketdata&method=ticker&currencyPair=" + pair)
	        .process(ex -> {
	        	Ticker ticker = ex.getMessage().getBody(Ticker.class);
	        	Map<String, Object> map = new LinkedHashMap<>();
	        	map.put("pair", ticker.getInstrument());
	        	map.put("last", ticker.getLast());
	        	ex.getMessage().setBody(map);
	        })
	        .marshal().json()
	        .log("${body}");
    }
}