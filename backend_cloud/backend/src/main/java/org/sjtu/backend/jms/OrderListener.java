package org.sjtu.backend.jms;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.sjtu.backend.entity.Order;
import org.sjtu.backend.service.OrderService;
import org.sjtu.backend.websocket.WebSocketServer;
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

    @Resource
    private WebSocketServer ws;

    @KafkaListener(topics = "topic1", groupId = "bookstore_topic_test")
    public void topic1Listener(ConsumerRecord<String, String> record) {
        String[] value = record.value().split(",");
        Order newOrder = orderService.generateOrder(value[0], value[1], value[2], value[3]);
        System.out.println("new Order buyer name:");
        System.out.println(value[0]);
        if(newOrder != null) {
            kafkaTemplate.send("topic2", "id", value[0]);
        }
        else {
            String to_send = "," + value[0];
            kafkaTemplate.send("topic2", "id", to_send);
        }
    }

    @KafkaListener(topics = "topic2", groupId = "bookstore_topic_test")
    public void topic2Listener(ConsumerRecord<String, String> record) {
        String value = record.value();
        System.out.println("topic2 get value: ");
        System.out.println(value);
        if(value.charAt(0) == ',') {
            ws.sendMessageToUser(value,"下单失败！");
        } else {
            ws.sendMessageToUser(value,"下单成功！");
        }
    }
}
