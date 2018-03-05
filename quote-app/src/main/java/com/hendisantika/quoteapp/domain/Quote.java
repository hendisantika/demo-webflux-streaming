package com.hendisantika.quoteapp.domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Instant;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/03/18
 * Time: 06.28
 * To change this template use File | Settings | File Templates.
 */
public class Quote {
    private static final MathContext MATH_CONTEXT = new MathContext(2);

    private String ticker;

    private BigDecimal price;

    private Instant instant;

    public Quote() {
    }

    public Quote(String ticker, BigDecimal price) {
        this.ticker = ticker;
        this.price = price;
    }

    public Quote(String ticker, Double price) {
        this(ticker, new BigDecimal(price, MATH_CONTEXT));
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(long epoch) {
        this.instant = Instant.ofEpochSecond(epoch);
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                ", instant=" + instant +
                '}';
    }
}
