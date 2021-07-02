# fuse-booster-camel-quarkus-ticker2log

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: [https://quarkus.io](https://quarkus.io).

## Camel Components 

* camel-quarkus-jackson
* camel-quarkus-log
* camel-quarkus-timer
* camel-quarkus-xchange

## Packaging and running the application

The application can be packaged using

	mvnw clean package

The application can now be run using 

	java -jar target/quarkus-app/quarkus-run.jar

Supported application config parameters are

* ticker2log.xchange.currencypair

## Building Docker images

The application can be packaged using:

	mvnw clean package -Pdocker

## Running with Docker

	# Run all images
	docker-compose -f docs/compose/ticker2log.yml up --detach && docker logs -f ticker2log
	
	# Stop all images
	docker-compose -f docs/compose/ticker2log.yml down

## Deploy on OpenShift

You can build, deploy and run this example by pushing it to your [OpenShift Sandbox](https://developers.redhat.com/developer-sandbox) like this ...

    odo push

When you then view the log of the running Pod, you should see output like this ...

	...
	ODO_COMMAND_RUN is java -jar target/quarkus-app/quarkus-run.jar
	Executing command  java -jar target/quarkus-app/quarkus-run.jar
	time="2021-07-01T11:07:42Z" level=debug msg="wait program exit" program=devrun
	__  ____  __  _____   ___  __ ____  ______
	 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
	 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
	--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
	2021-07-01 11:07:43,381 INFO  [org.apa.cam.qua.cor.CamelBootstrapRecorder] (main) bootstrap runtime: org.apache.camel.quarkus.main.CamelMainRuntime
	2021-07-01 11:07:44,528 INFO  [org.kno.xch.bin.BinanceExchange] (main) Calling Remote Init...
	2021-07-01 11:07:47,850 INFO  [org.apa.cam.com.jac.JacksonDataFormat] (main) The option autoDiscoverObjectMapper is set to false, Camel won't search in the registry
	2021-07-01 11:07:47,865 INFO  [org.apa.cam.imp.eng.AbstractCamelContext] (main) Routes startup summary (total:1 started:1)
	2021-07-01 11:07:47,865 INFO  [org.apa.cam.imp.eng.AbstractCamelContext] (main)     Started route1 (timer://ticker)
	2021-07-01 11:07:47,866 INFO  [org.apa.cam.imp.eng.AbstractCamelContext] (main) Apache Camel 3.9.0 (camel-1) started in 4s139ms (build:0ms init:4s119ms start:20ms)
	2021-07-01 11:07:47,873 INFO  [io.quarkus] (main) fuse-booster-camel-quarkus-ticker2log 1.0.0-SNAPSHOT on JVM (powered by Quarkus 1.13.7.Final) started in 6.055s.
	2021-07-01 11:07:47,873 INFO  [io.quarkus] (main) Profile prod activated.
	2021-07-01 11:07:47,874 INFO  [io.quarkus] (main) Installed features: [camel-core, camel-jackson, camel-log, camel-support-common, camel-timer, camel-xchange, cdi]
	2021-07-01 11:07:49,167 INFO  [route1] (Camel (camel-1) thread #0 - timer://ticker) {"pair":"ADA/USDT","last":1.31710000}
	2021-07-01 11:07:51,533 INFO  [route1] (Camel (camel-1) thread #0 - timer://ticker) {"pair":"ADA/USDT","last":1.31710000}
	2021-07-01 11:07:54,031 INFO  [route1] (Camel (camel-1) thread #0 - timer://ticker) {"pair":"ADA/USDT","last":1.31700000}
