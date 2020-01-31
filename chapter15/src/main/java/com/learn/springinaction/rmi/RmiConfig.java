package com.learn.springinaction.rmi;

import com.learn.springinaction.service.SpitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@Configuration
public class RmiConfig {
    @Bean
    public RmiServiceExporter rmiServiceExporter(SpitterService spitterService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(spitterService);
        exporter.setServiceName("SpitterService");
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    @Bean
    public RmiProxyFactoryBean spitterService() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost/SpitterService");
        rmiProxyFactoryBean.setServiceInterface(SpitterService.class);
        return rmiProxyFactoryBean;
    }

    @Bean
    public HessianServiceExporter hessianServiceExporter(SpitterService spitterService) {
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(spitterService);
        hessianServiceExporter.setServiceInterface(SpitterService.class);
        return hessianServiceExporter;
    }

}

