package com.bullhorn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class AppController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/")
    public String returnMessagesPage(){
        return "messages";
    }

    @GetMapping("/add")
    public String returnAddPage(Message message){
        return "add-message";
    }

    @PostMapping("/addmessage")
    public String addMessage(Message message, Model model){

        //Generate date
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("EST"));

        String date = formatter.format(new Date());

        message.setPosteddate(date);

        messageRepository.save(message);
        model.addAttribute("messages", messageRepository.findAll());
        return "messages";
    }
}
