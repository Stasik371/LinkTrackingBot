package ru.tinkoff.edu.java.bot.update.senders;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;
import ru.tinkoff.edu.java.bot.telegram.bot.Bot;

@RabbitListener(queues = "${app.queue-name}")
public class ScrapperQueueListener implements UpdateReceiver {


    private final Bot bot;

    @Autowired
    public ScrapperQueueListener(Bot bot) {
        this.bot = bot;
    }

    @RabbitHandler
    @Override
    public void receiver(LinkUpdateRequest update) {
        bot.sendUpdates(update);
    }
}
