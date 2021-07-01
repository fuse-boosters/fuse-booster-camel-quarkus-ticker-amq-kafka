# fuse-booster-quarkus-amq-kafka project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: [https://quarkus.io](https://quarkus.io).


## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw clean package
```

The application can now be run using `java -jar target/quarkus-app/quarkus-run.jar`.

## Running with Docker

```shell script
# Run all images
docker-compose -f docs/compose/amq-kafka.yml up --detach \
	&& docker logs -f kafka2log

# Stop all images
docker-compose -f docs/compose/amq-kafka.yml down
```

