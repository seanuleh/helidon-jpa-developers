# Helidon MP Bare

Minimal Helidon MP project suitable to start from scratch.


## Quick Start
```
mvn package -Dmaven.test.skip=true; java -jar target/helidon-jpa.jar
```

Test with Included Postman Collection


## Build and run

With JDK11+
```bash
mvn package
java -jar target/helidon-jpa.jar
```

## Exercise the application

```
curl -X GET http://localhost:8080/greet
{"message":"Hello World!"}
```

## Try health and metrics

```
curl -s -X GET http://localhost:8090/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8090/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8090/metrics
{"base":...
. . .
```