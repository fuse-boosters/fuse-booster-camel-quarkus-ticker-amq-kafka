package com.redhat.fuse.boosters;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.knowm.xchange.dto.marketdata.Ticker;

public class TickerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:ticker?period=2500")
	        .to("xchange:binance?service=marketdata&method=ticker&currencyPair=ada/usdt")
	        .process(ex -> {
	        	Ticker ticker = ex.getMessage().getBody(Ticker.class);
	        	Map<String, Object> map = new LinkedHashMap<>();
	        	map.put("pair", ticker.getInstrument());
	        	map.put("close", ticker.getLast());
	        	ex.getMessage().setBody(map);
	        })
	        .marshal().json()
	        .log("${body}");
    }
}