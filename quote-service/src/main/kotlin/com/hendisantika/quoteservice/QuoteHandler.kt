package com.hendisantika.quoteservice

import org.omg.CORBA.ServerRequest
import org.springframework.http.MediaType.APPLICATION_STREAM_JSON
import org.springframework.http.MediaType.TEXT_EVENT_STREAM
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse.ok
import java.time.Duration.ofMillis

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/03/18
 * Time: 18.27
 * To change this template use File | Settings | File Templates.
 */

@Component
class QuoteHandler(val quoteGenerator: QuoteGenerator) {

    fun fetchQuotesSSE(req: ServerRequest) = ok()
            .contentType(TEXT_EVENT_STREAM)
            .body(quoteGenerator.fetchQuoteStream(ofMillis(200)), Quote::class.java)

    fun fetchQuotes(req: ServerRequest) = ok()
            .contentType(APPLICATION_STREAM_JSON)
            .body(quoteGenerator.fetchQuoteStream(ofMillis(200)), Quote::class.java)

}