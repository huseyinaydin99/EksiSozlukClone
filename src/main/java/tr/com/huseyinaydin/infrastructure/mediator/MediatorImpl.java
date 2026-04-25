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
        ResolvableType requestType = ResolvableType.forClass(request.getClass());
        ResolvableType responseType = ResolvableType.NONE;

        for (ResolvableType iface : requestType.getInterfaces()) {
            if (iface.getRawClass() == Request.class) {
                responseType = iface.getGeneric(0);
                break;
            }
        }

        if (responseType == ResolvableType.NONE) {
            throw new RuntimeException("Request interface not found on " + request.getClass().getName());
        }

        ResolvableType handlerType = ResolvableType.forClassWithGenerics(RequestHandler.class, requestType, responseType);
        String[] beanNames = applicationContext.getBeanNamesForType(handlerType);
        
        if (beanNames.length == 0) {
            throw new RuntimeException("No handler found for request: " + request.getClass().getName() + " with response type: " + responseType);
        }

        RequestHandler<Request<R>, R> handler = (RequestHandler<Request<R>, R>) applicationContext.getBean(beanNames[0]);
        return handler.handle(request);
    }

}
