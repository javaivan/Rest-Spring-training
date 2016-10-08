package it.discovery;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@EnableAutoConfiguration
public class RestApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RestApplication.class, args);
    }

    @ResponseBody
    @RequestMapping("/")
    String homeController() {
        return "Hello World!";
    }

    @ResponseBody
    @RequestMapping("/time")
    public String timeController(){
        return String.format("%s",System.currentTimeMillis());
    }

    @ResponseBody
    @RequestMapping("/date")
    public String dateController(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}