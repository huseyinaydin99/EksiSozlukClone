package tr.com.huseyinaydin.infrastructure.mediator;

public interface RequestHandler<T extends Request<R>, R> {
    R handle(T request);
}
