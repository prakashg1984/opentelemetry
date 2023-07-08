## Purpose  
Example to show OpenTelemetry Collector to send Tracer, Logs and Metrics to different systems.

This example uses Jaeger and Datadog as exporters

## Flow  
spring-cloud-open-telemetry1 -> spring-cloud-open-telemetry2 -> spring-cloud-open-telemetry3

## Steps 

#Build each individual module and do a docker build

mvn clean install

docker build .

#Run all services from parent using docker-compose

docker-compose up

## URLs 
API http://localhost:8080/product/100003

Jaegar http://localhost:16686/

Grafana http://localhost:3000/

Prometheus http://localhost:9090/
