package ru.tinkoff.edu.java.bot.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.controllers.dto.LinkUpdateRequest;

import java.util.HashMap;
import java.util.Map;


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
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.webclients.internal.dto.LinkUpdate", LinkUpdateRequest.class);
        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.webclients.internal.dto.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }

    //dead
    @Bean
    public Queue deadMessagesQueue() {
        return QueueBuilder
                .nonDurable(queueName + ".dlq")
                .build();
    }

    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadMessagesQueue()).to(deadExchange());
    }

    @Bean
    public FanoutExchange deadExchange() {
        return new FanoutExchange(exchangeName + ".dlx", false, false);
    }

}

