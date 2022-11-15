package io.febrihasan.gateway.v2.configuration;

import io.febrihasan.gateway.v2.configuration.filter.CustomLocationRewriteFilter;
import io.febrihasan.gateway.v2.configuration.processor.ZuulPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author febrihasan
 */
@Configuration
public class ZuulProxyConfig {

    @Bean
    public ZuulPostProcessor zuulPostProcessor(@Autowired RouteLocator routeLocator, @Autowired ZuulController zuulController,
                                               @Autowired(required = false) ErrorController errorController) {
        return new ZuulPostProcessor(routeLocator, zuulController, errorController);
    }

    @Bean
    public CustomLocationRewriteFilter locationRewriteFilter() {
        return new CustomLocationRewriteFilter();
    }
}
