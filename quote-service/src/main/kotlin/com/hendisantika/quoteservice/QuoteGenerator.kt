package com.hendisantika.quoteservice

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.SynchronousSink
import java.math.BigDecimal
import java.math.MathContext
import java.time.Duration
import java.time.Instant
import java.util.*

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/03/18
 * Time: 07.12
 * To change this template use File | Settings | File Templates.
 */

@Component
class QuoteGenerator {
    val mathContext = MathContext(2)

    val random = Random()

    val prices = listOf(
            Quote("CTXS", BigDecimal(82.26, mathContext)),
            Quote("DELL", BigDecimal(63.74, mathContext)),
            Quote("GOOG", BigDecimal(847.24, mathContext)),
            Quote("MSFT", BigDecimal(65.11, mathContext)),
            Quote("ORCL", BigDecimal(45.71, mathContext)),
            Quote("RHT", BigDecimal(84.29, mathContext)),
            Quote("VMW", BigDecimal(92.21, mathContext))
    )


    fun fetchQuoteStream(period: Duration) = Flux.generate({ 0 },
            { index, sink: SynchronousSink<Quote> ->
                sink.next(updateQuote(prices[index]))
                (index + 1) % prices.size
            }).zipWith(Flux.interval(period))
            .map { it.t1.copy(instant = Instant.now()) }
            .share()
            .log()


    private fun updateQuote(quote: Quote) = quote.copy(
            price = quote.price.add(quote.price.multiply(
                    BigDecimal(0.05 * random.nextDouble()), mathContext))
    )

}