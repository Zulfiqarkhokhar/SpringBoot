package com.example.MyDemoProject.service;

import com.example.MyDemoProject.dto.Todo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MyService {

    private final RestClient restClient;

    public MyService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Todo getData(){
        return restClient.get()
                .uri("https://jsonplaceholder.typicode.com/todos/1")
                .retrieve()
                .body(Todo.class);
    }
}
