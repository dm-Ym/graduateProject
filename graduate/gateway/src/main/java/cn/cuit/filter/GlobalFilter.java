package cn.cuit.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 过滤器执行顺序: default > 路由 > global
 *
 *
 * 全局过滤器
 */
public interface GlobalFilter {
	/**
	 * 处理请求, 多级请求时可通过{@link GatewayFilterChain}将请求交给下一个过滤器
	 *
	 * @param exchange 请求上下文,可以用来获取Request、Response等信息
	 * @param chain 用来把请求交给下一个过滤器
	 * @return {@code Mono<Void>} 返回标示当前过滤器业务结束
	 */
	Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain);
}
