package org.sjtu.backend.jms;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.sjtu.backend.entity.Order;
import org.sjtu.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderListener {
    @Resource
    private OrderService orderService;

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = "topic1", groupId = "bookstore_topic_test")
    public void topic1Listener(ConsumerRecord<String, String> record) {
        String[] value = record.value().split(",");
        Order newOrder = orderService.generateOrder(value[0], value[1], value[2], value[3]);
        kafkaTemplate.send("topic2",  "id", String.valueOf(newOrder.getId()));
    }

    @KafkaListener(topics = "topic2", groupId = "bookstore_topic_test")
    public void topic2Listener(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println(value);
    }
}
