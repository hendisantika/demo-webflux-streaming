package com.hendisantika.quoteapp.controller;

import com.hendisantika.quoteapp.domain.Quote;
import com.hendisantika.quoteapp.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/03/18
 * Time: 06.29
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class QuoteController {
    private final UserRepository userRepository;

    private final WebClient webClient;

    private final Flux<Quote> quotesFeed;

    public QuoteController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.webClient = WebClient.create("http://localhost:8081");
        this.quotesFeed = this.webClient.get()
                .uri("/quotes")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class)
                .share()
                .log();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", this.userRepository.findAll());
        return "index";
    }

    @GetMapping("/quotes")
    public String quotes() {
        return "quotes";
    }

    @GetMapping(path = "/quotes/feed", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Quote> fetchQuotesStream() {
        return quotesFeed;
    }

}
