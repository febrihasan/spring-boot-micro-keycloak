package io.febrihasan.gateway.v2.configuration.processor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.web.ZuulController;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;

/**
 * @author febrihasan
 */
public class ZuulPostProcessor implements BeanPostProcessor {

    /**
     * The path returned by ErrorController.getErrorPath() with Spring Boot < 2.5
     * (and no longer available on Spring Boot >= 2.5).
     */
    private static final String ERROR_PATH = "/error";

    private static final String METHOD = "lookupHandler";

    private RouteLocator routeLocator;

    private ZuulController zuulController;

    private boolean hasErrorController;

    public ZuulPostProcessor(RouteLocator routeLocator, ZuulController zuulController, ErrorController errorController) {
        this.routeLocator = routeLocator;
        this.zuulController = zuulController;
        this.hasErrorController = (errorController != null);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (hasErrorController && (bean instanceof ZuulHandlerMapping)) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(ZuulHandlerMapping.class);
            enhancer.setCallbackFilter(LookupHandlerCallbackFilter.INSTANCE);
            enhancer.setCallbacks(new Callback[]{ LookupHandlerMethodInterceptor.INSTANCE, NoOp.INSTANCE});
            Constructor<?> ctor = ZuulHandlerMapping.class.getConstructors()[0];
            return enhancer.create(ctor.getParameterTypes(), new Object[] {routeLocator, zuulController});
        }
        return bean;
    }

    private enum LookupHandlerCallbackFilter implements CallbackFilter {
        INSTANCE;

        @Override
        public int accept(Method method) {
            if (METHOD.equals(method.getName())) {
                return 0;
            }
            return 1;
        }
    }

    private enum LookupHandlerMethodInterceptor implements MethodInterceptor {
        INSTANCE;

        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            if (ERROR_PATH.equals(args[0])) {
                return null;
            }
            return methodProxy.invokeSuper(target, args);
        }
    }
}
