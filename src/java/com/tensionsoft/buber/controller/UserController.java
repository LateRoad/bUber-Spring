package com.tensionsoft.buber.controller;

import com.tensionsoft.buber.Constants;
import com.tensionsoft.buber.entity.user.User;
import com.tensionsoft.buber.utils.BeansUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/users")
public class UserController {
    private HashMap<String, User> fakeUsers = new HashMap<>();

    public UserController() {
        init();
    }

    private void init() {
        fakeUsers.put(Constants.fakeKatya.getUsername(), Constants.fakeKatya);
        fakeUsers.put(Constants.fakeYan.getUsername(), Constants.fakeYan);
        fakeUsers.put(Constants.fakeRoma.getUsername(), Constants.fakeRoma);
    }


    @PostMapping
    public void create(@RequestParam(value = "user") User user) {
        if (user != null && user.getUsername() != null) {
            fakeUsers.put(user.getUsername(), user);
        }
    }


    @GetMapping("/{login}")
    public Object read(@PathVariable("login") String login) {
        if (fakeUsers.containsKey(login)) {
            return fakeUsers.get(login);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public Object read() {
        if (fakeUsers.size() == 0) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return fakeUsers;
    }


    @PutMapping
    public void update(@RequestParam(value = "user") User user) {
        if (user != null) {
            BeansUtil<User> beansUtil = new BeansUtil<>();
            fakeUsers.forEach((login, currentUser) -> beansUtil.copyNonNullProperties(currentUser, user));
        }
    }

    @PutMapping("/{login}")
    public void update(@PathVariable("login") String login,
                       @RequestBody User user) {
        User currentUser = fakeUsers.get(login);
        if (user != null) {
            BeansUtil<User> beanUtil = new BeansUtil<>();
            beanUtil.copyNonNullProperties(currentUser, user);
        }
    }


    @DeleteMapping
    public void delete() {
        fakeUsers.clear();
    }

    @DeleteMapping("/{login}")
    public void delete(@PathVariable("login") String login) {
        if (fakeUsers.containsKey(login)) {
            fakeUsers.remove(login);
        }
    }
}
