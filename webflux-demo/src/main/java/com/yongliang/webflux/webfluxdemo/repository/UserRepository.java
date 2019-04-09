package com.yongliang.webflux.webfluxdemo.repository;

import com.yongliang.webflux.webfluxdemo.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * &lt;p&gt;Copyright: Copyright (c) 2011&lt;/p&gt;
 * &lt;p&gt;公司名称 : 中国电信甘肃万维公司&lt;/p&gt;
 * &lt;p&gt;项目名称 : ${project_name}&lt;/p&gt;
 * &lt;p&gt;创建时间 : ${date} ${time}&lt;/p&gt;
 * &lt;p&gt;类描述 :         &lt;/p&gt;
 *
 * @author &lt;a href=" "&gt;zhangyongliang${user}&lt;/a&gt;
 * @version 1.0.0
 */
public interface UserRepository  extends ReactiveCrudRepository<User,String> {
    Mono<User> findByUsername(String username);
    Mono<Long> deleteByUsername(String username);
}
