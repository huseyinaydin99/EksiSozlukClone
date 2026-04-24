package tr.com.huseyinaydin.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tr.com.huseyinaydin.application.mapper.EntryMapper;
import tr.com.huseyinaydin.infrastructure.mediator.Mediator;
import tr.com.huseyinaydin.service.interfaces.AuthService;
import tr.com.huseyinaydin.service.interfaces.EntryService;

@RestController
public abstract class BaseApiController {
    @Autowired
    protected Mediator mediator;

    @Autowired
    protected EntryMapper entryMapper;

    @Autowired
    protected EntryService entryService;

    @Autowired
    protected AuthService authService;
}
