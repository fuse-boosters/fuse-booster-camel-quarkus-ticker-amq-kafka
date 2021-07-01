# fuse-booster-camel-quarkus-ticker2log

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: [https://quarkus.io](https://quarkus.io).

## Camel Components 

* camel-quarkus-jackson
* camel-quarkus-log
* camel-quarkus-timer
* camel-quarkus-xchange

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw clean package
```

The application can now be run using `java -jar target/quarkus-app/quarkus-run.jar`.

## Building Docker images

The application can be packaged using:

```shell script
./mvnw clean package -Pdocker
```

## Running with Docker

```shell script
# Run all images
docker-compose -f docs/compose/ticker2log.yml up --detach \
	&& docker logs -f ticker2log

# Stop all images
docker-compose -f docs/compose/ticker2log.yml down
```

