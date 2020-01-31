package com.learn.springinaction.service;

import com.learn.springinaction.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;

public class AlertServiceImpl implements AlertService {

    private JmsOperations jmsOperations;

    @Autowired
    public AlertServiceImpl(JmsOperations jmsOperations) {
        this.jmsOperations = jmsOperations;
    }

    @Override
    public void sendSpittleAlart(Spittle spittle) {
        jmsOperations.send("spittle.alert.queue", session -> session.createObjectMessage(spittle));
    }

    @Override
    public Spittle receiveSpittleAlert() {
        return (Spittle) jmsOperations.receiveAndConvert();
    }
}
