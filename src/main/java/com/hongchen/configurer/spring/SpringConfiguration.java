package com.hongchen.configurer.spring;

import com.hongchen.util.spring.Springs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lichongda on 2017/4/18.
 */
@Configuration
public class SpringConfiguration {
    /**
     * 实例化Springs
     * @return
     */
    @Bean
    public Springs getSprings(){
        Springs springs = new Springs();
        return springs;
    }
}
