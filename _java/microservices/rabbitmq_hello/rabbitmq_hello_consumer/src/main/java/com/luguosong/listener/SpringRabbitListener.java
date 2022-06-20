package com.luguosong.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author luguosong
 * @date 2022/6/18
 */
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listen(String msg){
        System.out.println(msg);
    }
}
