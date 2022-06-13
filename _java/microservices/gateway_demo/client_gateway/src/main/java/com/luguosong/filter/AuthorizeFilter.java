package com.luguosong.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.AddRequestHeaderGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;

/**
 * Gateway自定义全局过滤器
 *
 * @author luguosong
 * @date 2022/6/11 18:18
 */
//@Order(0)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取请求参数列表
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //获取参数中的authorization参数
        String auth = queryParams.getFirst("authorization");
        //判断参数值是否等于admin
        if ("admin".equals(auth)){
            //是：放行
            return chain.filter(exchange);
        }

        //否：拦截
        //设置响应状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //拦截请求
        return exchange.getResponse().setComplete();
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
