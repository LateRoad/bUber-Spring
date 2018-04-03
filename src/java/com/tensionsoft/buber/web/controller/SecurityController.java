package com.tensionsoft.buber.web.controller;

import com.tensionsoft.buber.persistence.model.User;
import com.tensionsoft.buber.security.ISecurityUserService;
import com.tensionsoft.buber.service.IUserService;
import com.tensionsoft.buber.web.dto.UserDto;
import com.tensionsoft.buber.web.util.GenericResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tensionsoft.buber.registration.OnRegistrationCompleteEvent;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class SecurityController {
    @Autowired
    private IUserService IUserService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISecurityUserService securityUserService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    @ResponseBody
    public GenericResponse registerUserAccount(@Valid final UserDto accountDto, final HttpServletRequest request) {
        logger.debug("Registering user account with information: {}", accountDto);

        final User registered = IUserService.registerNewUserAccount(accountDto);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        return new GenericResponse("success");
    }

// ============== NON-API ============

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}