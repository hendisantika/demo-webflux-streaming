package com.hendisantika.quoteservice

import java.math.BigDecimal
import java.time.Instant

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 03/03/18
 * Time: 06.39
 * To change this template use File | Settings | File Templates.
 */
data class Quote(val ticker: String, val price: BigDecimal, val instant: Instant = Instant.now())