package com.example.MyDemoProject.controller;

import com.example.MyDemoProject.dto.OrderDto;
import com.example.MyDemoProject.records.OrderRecord;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController // to make class controller as well component to be scanned by spring
public class MyController {

    @GetMapping("/hello")// for get http method
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String sayHello(){
        return "hello";
    }

    @PostMapping("/post") // for post http method
    public String post(@RequestBody String message){ // requestbody is for acception the body sent from client to map with java objects
        return "Request Accepted and Message is :"+message;
    }

    @PostMapping("/post-order")
    public String post(@RequestBody OrderDto order){
        return "Request Accepted and Order is :"+order.toString();
    }

    @PostMapping("/post-order-record")
    public String postRecord(@RequestBody OrderRecord order){
        return "Request Accepted and Order is :"+order.toString();
    }

    @GetMapping("/hello/{userName}")
    public String pathVr(@PathVariable String userName){ //pathvariable is used to get name from the url passed by user
        return "my path value is :"+ userName;
    }

    @GetMapping("/hello/param")
    public String paramVr(@RequestParam String userName,@RequestParam String email){ //requestparam is used to get name from the url passed by user in param
        return "my param value is :"+ userName + " " +email;
    }



}
