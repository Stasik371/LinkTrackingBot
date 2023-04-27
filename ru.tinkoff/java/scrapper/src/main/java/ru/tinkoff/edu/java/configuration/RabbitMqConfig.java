package ru.tinkoff.edu.java.configuration;


import jakarta.validation.constraints.NotNull;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.services.implementations.updatesenders.ScrapperQueueProducerServiceImpl;
import ru.tinkoff.edu.java.services.implementations.updatesenders.UpdateSender;

@ConditionalOnProperty(prefix = "app", name = "use-queue", havingValue = "true")
@Configuration
public class RabbitMqConfig {

    @Value("${app.exchange-name}")
    private String exchangeName;

    @Value("${app.queue-name}")
    private String queueName;

    @Bean
    public Queue queue() {
        return QueueBuilder
                .nonDurable(queueName)
                .withArgument("x-dead-letter-exchange", exchangeName + ".dlx")
                .build();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(queueName);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public @NotNull UpdateSender updateSender(RabbitTemplate rabbitTemplate) {
        return new ScrapperQueueProducerServiceImpl(rabbitTemplate, queue());
    }
}
