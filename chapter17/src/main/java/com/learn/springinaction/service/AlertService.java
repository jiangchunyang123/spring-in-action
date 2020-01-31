package com.learn.springinaction.service;

import com.learn.springinaction.model.Spittle;

public interface AlertService {
    void sendSpittleAlart(Spittle spittle);

    Spittle receiveSpittleAlert();
}
