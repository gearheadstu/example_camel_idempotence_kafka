package org.kellyjones.examples.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("local")
@Component
public class KafkaIdempotenceConsumerRoute extends RouteBuilder {

    // What Kafka broker will we consume from?
    @Value("${application.kafka.broker}")
    private String kafkaBroker;

    // What Kafka topic is used for maintaining clustered
    // idempotence for this app?
    @Value("${application.kafka.topics.idempotence}")
    private String idempotenceTopic;

    @Override
    public void configure() throws Exception {
        from("kafka:" + idempotenceTopic + "?brokers=" + kafkaBroker)
                .routeId("idempotent-consumer")
                .log("======== BEGIN ======== ")
                .log("New message received from Kafka")
                .log("\ttopic: ${headers[kafka.TOPIC]}")
                .log("\tpartition: ${headers[kafka.PARTITION]}")
                .log("\toffset: ${headers[kafka.OFFSET]}")
                .log("\tkey: ${headers[kafka.KEY]}")
                .log("\tbody: ${body}")
                .log("========= END =========");
    }
}
