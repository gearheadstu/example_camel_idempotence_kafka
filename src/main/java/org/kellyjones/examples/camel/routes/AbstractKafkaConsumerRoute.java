package org.kellyjones.examples.camel.routes;

import org.apache.camel.builder.RouteBuilder;


public abstract class AbstractKafkaConsumerRoute extends RouteBuilder {

    protected abstract String getKafkaBrokers();

    protected abstract String getKafkaTopic();

    protected abstract String getRouteId();

    public void configure() throws Exception {
        from("kafka:" + getKafkaTopic() + "?brokers=" + getKafkaBrokers())
                .routeId(getRouteId())
                .log("New message received from Kafka")
                .log("\ttopic: ${headers[kafka.TOPIC]}")
                .log("\tpartition: ${headers[kafka.PARTITION]}")
                .log("\toffset: ${headers[kafka.OFFSET]}")
                .log("\tkey: ${headers[kafka.KEY]}")
                .log("\tbody: ${body}")
                .log("========= END =========");

    }

}
