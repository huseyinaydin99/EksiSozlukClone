package tr.com.huseyinaydin.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import tr.com.huseyinaydin.application.mapper.EntryMapper;
import tr.com.huseyinaydin.infrastructure.mediator.Mediator;
import tr.com.huseyinaydin.service.interfaces.AuthService;
import tr.com.huseyinaydin.service.interfaces.EntryCommentService;
import tr.com.huseyinaydin.service.interfaces.EntryService;
import tr.com.huseyinaydin.service.interfaces.UserService;

public abstract class BaseMvcController {
    @Autowired
    protected Mediator mediator;

    @Autowired
    protected EntryMapper entryMapper;

    @Autowired
    protected EntryService entryService;

    @Autowired
    protected EntryCommentService entryCommentService;

    @Autowired
    protected AuthService authService;

    @Autowired
    protected UserService userService;
}
