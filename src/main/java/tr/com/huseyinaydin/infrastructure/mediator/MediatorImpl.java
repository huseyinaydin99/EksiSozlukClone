package tr.com.huseyinaydin.infrastructure.mediator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediatorImpl implements Mediator {
    private final ApplicationContext applicationContext;

    @Override
    @SuppressWarnings("unchecked")
    public <R> R send(Request<R> request) {
        Class<?> responseType = getResponseType(request);
        ResolvableType handlerType = ResolvableType.forClassWithGenerics(RequestHandler.class, request.getClass(), responseType);
        
        String[] beanNames = applicationContext.getBeanNamesForType(handlerType);
        
        if (beanNames.length == 0) {
            throw new RuntimeException("No handler found for request: " + request.getClass().getName());
        }

        RequestHandler<Request<R>, R> handler = (RequestHandler<Request<R>, R>) applicationContext.getBean(beanNames[0]);
        return handler.handle(request);
    }

    private Class<?> getResponseType(Request<?> request) {
        for (java.lang.reflect.Type interfaceType : request.getClass().getGenericInterfaces()) {
            if (interfaceType instanceof java.lang.reflect.ParameterizedType) {
                java.lang.reflect.ParameterizedType parameterizedType = (java.lang.reflect.ParameterizedType) interfaceType;
                if (parameterizedType.getRawType().equals(Request.class)) {
                    return (Class<?>) parameterizedType.getActualTypeArguments()[0];
                }
            }
        }
        return Object.class;
    }
}
