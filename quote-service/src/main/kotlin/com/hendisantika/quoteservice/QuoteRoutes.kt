package com.hendisantika.quoteservice

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON
import org.springframework.http.MediaType.TEXT_EVENT_STREAM
import org.springframework.web.reactive.function.server.router

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 04/03/18
 * Time: 18.31
 * To change this template use File | Settings | File Templates.
 */
@Configuration
class QuoteRoutes(val quoteHandler: QuoteHandler) {

    @Bean
    fun quoteRouter() = router {
        GET("/quotes").nest {
            accept(TEXT_EVENT_STREAM, quoteHandler::fetchQuotesSSE)
            accept(APPLICATION_STREAM_JSON, quoteHandler::fetchQuotes)
        }
    }

}