package com.redhat.fuse.boosters;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.activemq.ActiveMQComponent;
import org.apache.camel.model.RoutesDefinition;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.knowm.xchange.dto.marketdata.Ticker;

@ApplicationScoped
public class TickerRoute extends RouteBuilder {

	@ConfigProperty(name = "camel.quarkus.ticker2log.xchange.currencypair") 
	String pair;
	@ConfigProperty(name = "camel.quarkus.ticker2log.port.61616.tcp.addr") 
	String artemisHost;
	@ConfigProperty(name = "camel.quarkus.ticker2log.port.9092.tcp.addr") 
	String kafkaHost;
	@ConfigProperty(name = "camel.quarkus.ticker2log.artemis.queue") 
	String artemisQueue;
	@ConfigProperty(name = "camel.quarkus.ticker2log.kafka.topic") 
	String kafkaTopic;
	
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
	        // .log("xchange: ${body}")
	        .to("activemq:queue:" + artemisQueue)
	        .to("kafka:" + kafkaTopic + "?brokers=" + kafkaHost + ":9092")
	        .log("kafka: ${body}");
	}
	
	@Override
	public RoutesDefinition configureRoutes(CamelContext context) throws Exception {
		ActiveMQComponent amq = context.getComponent("activemq", ActiveMQComponent.class);
		amq.setBrokerURL("tcp://" + artemisHost + ":61616");
		return super.configureRoutes(context);
	}
}