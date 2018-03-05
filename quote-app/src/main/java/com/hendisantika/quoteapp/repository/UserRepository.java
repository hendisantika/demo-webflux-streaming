package com.hendisantika.quoteapp.repository;

import com.hendisantika.quoteapp.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : demo-webflux-streaming
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 06/03/18
 * Time: 06.27
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findUserByGithub(String github);

}