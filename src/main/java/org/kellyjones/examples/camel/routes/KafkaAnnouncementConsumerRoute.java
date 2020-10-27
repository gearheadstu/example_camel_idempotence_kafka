package org.kellyjones.examples.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaAnnouncementConsumerRoute extends RouteBuilder {

    // What Kafka broker will we consume from?
    @Value("${application.kafka.broker}")
    private String kafkaBroker;

    // What Kafka topic is used for announcements generated by this app?
    @Value("${application.kafka.topics.announcements}")
    private String announcementTopic;

    @Override
    public void configure() throws Exception {

        from("kafka:" + announcementTopic + "?brokers=" + kafkaBroker)
                .routeId("announce-consumer")
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
