package org.kellyjones.examples.camel.routes;

import lombok.Getter;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Getter
@Profile("local")
@Component
public class KafkaIdempotenceConsumerRoute extends AbstractKafkaConsumerRoute {

    // What Kafka broker will we consume from?
    @Value("${application.kafka.broker}")
    private String kafkaBrokers;

    // What Kafka topic is used for maintaining clustered
    // idempotence for this app?
    @Value("${application.kafka.topics.idempotence}")
    private String kafkaTopic;

    // When we create the corresponding Camel route,
    // what will its ID be?
    @Value("${application.camel.routes.idempotence}")
    private String routeId;

}
