package com.Pulse.Websocket.Demo.Controller;

import com.Pulse.Websocket.Demo.Greeting;
import com.Pulse.Websocket.Demo.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;


    @PostMapping("/app/v1/add/points")
    @MessageMapping("/hello")
    // @SendTo("/topic/greetings")
    public Greeting greeting(@RequestBody HelloMessage message) throws Exception {
        Thread.sleep(1000);
        System.out.println("I am in greeting!!!!");
        //template.convertAndSendToUser("", "/topic/greetings", message);
        //template.send("/topic/greetings", MessageBuilder.withPayload(message.getName()).setHeader("status","OK").build());
        this.template.convertAndSend("/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
