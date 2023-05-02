package ru.tinkoff.edu.java.services.implementations.updatesenders;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate;


public class ScrapperQueueProducerServiceImpl implements UpdateSender {

    private final RabbitTemplate template;
    private final Queue queue;

    @Autowired
    public ScrapperQueueProducerServiceImpl(RabbitTemplate template, Queue queue) {
        this.template = template;
        this.queue = queue;
    }

    @Override
    public void sendUpdates(LinkUpdate linkUpdate) {
        template.convertAndSend(queue.getName(), linkUpdate);
    }
}
