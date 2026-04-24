package tr.com.huseyinaydin.infrastructure.mediator;

public interface Mediator {
    <R> R send(Request<R> request);
}
