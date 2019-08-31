package com.ku.client.controller;

import com.ku.client.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class ClientController {

    private List<User> users;

    private List<String> ids;

    private final WebClient webClient;

    public ClientController(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostConstruct
    public void init() {
        users = IntStream.range(0, 2000)
                .mapToObj(integer -> new User(String.valueOf(integer), "Taylan Erden " + integer,
                        "taylanerden-" + integer, String.valueOf(integer),
                        "terden-" + integer + "@ku.edu.te", "1q2w3e-" + integer))
                .collect(Collectors.toList());

        ids = IntStream.range(0, 2000)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }


    @GetMapping(value = "/add-user-non-blocking")
    public Mono<Void> addUserNonBlocking() {
        return Flux.fromStream(users.stream())
                .flatMap(user -> webClient.put()
                        .uri("/add-user")
                        .body(BodyInserters.fromObject(user))
                        .retrieve()
                        .bodyToMono(Void.class))
                .then();
    }

    @GetMapping(value = "/get-user-non-blocking")
    public Mono<Void> getUserNonBlocking() {
        return Flux.fromStream(ids.stream())
                .flatMap(id -> webClient.post()
                        .uri(uriBuilder -> uriBuilder.path("get-user")
                                .queryParam("user-id", id)
                                .build())
                        .retrieve()
                        .bodyToMono(User.class))
                //.doOnNext(user -> log.info(user.toString()))
                .then();
    }

    @GetMapping(value = "/add-user-blocking")
    public void addUserBlocking() {
        users.parallelStream()
                .forEach(user -> new RestTemplate()
                        .put("http://172.17.0.2:8082/add-user", user));
    }

    @GetMapping(value = "/get-user-blocking")
    public void getUserBlocking() {
        users.parallelStream()
                .forEach(user -> new RestTemplate()
                        .postForObject("http://172.17.0.2:8082/add-user", user, void.class));
    }

}
