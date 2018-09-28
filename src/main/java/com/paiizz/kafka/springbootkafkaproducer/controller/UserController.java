package com.paiizz.kafka.springbootkafkaproducer.controller;

//import com.paiizz.kafka.springbootkafkaproducer.model.User;
import com.paiizz.kafka.springbootkafkaproducer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
@Slf4j
public class UserController {
    @Autowired
    private KafkaTemplate<String, User> kafkaTemplateUser;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;

    private static final String TOPIC_STRIGN = "Kafka_Example_String";
    private static final String TOPIC_JSON = "Kafka_Example_Json";


    @PostMapping("/string")
    public String sendMessage(@RequestBody String msg) {
        kafkaTemplateString.send(TOPIC_STRIGN, msg);
        return "Published message " + msg + " successfully";
    }


    @PostMapping("/user")
    public ResponseEntity<String> sendUser(
            @RequestBody User user
    ) {
        kafkaTemplateUser.send(TOPIC_JSON, user);
        return new ResponseEntity<>("Published "+ user.toString() +" successfully", HttpStatus.OK);
    }
}
