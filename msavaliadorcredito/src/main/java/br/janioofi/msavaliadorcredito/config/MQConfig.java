package br.janioofi.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MQConfig {
    @Value("${mq.queues.emissao-cartoes}")
    private String emissaoCartoesFila;

    public Queue queueEmissaoCartoes(){
        return new Queue(emissaoCartoesFila, true);
    }
}
