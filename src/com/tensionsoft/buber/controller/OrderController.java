package com.tensionsoft.buber.controller;

import com.tensionsoft.buber.Constants;
import com.tensionsoft.buber.entity.order.Order;
import com.tensionsoft.buber.entity.user.UserRole;
import com.tensionsoft.buber.utils.BeansUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/orders")
public class OrderController {
    private HashMap<Long, Order> fakeOrders = new HashMap<>();

    public OrderController() {
        init();
    }

    private void init() {
        fakeOrders.put(Constants.fakeOrder0.getId(), Constants.fakeOrder0);
        fakeOrders.put(Constants.fakeOrder1.getId(), Constants.fakeOrder1);
        fakeOrders.put(Constants.fakeOrder2.getId(), Constants.fakeOrder2);
    }


    @PostMapping
    public void create(@RequestParam(value = "order") Order order) {
        if (order != null && order.getId() != null) {
            fakeOrders.put(order.getId(), order);
        }
    }


    @GetMapping("/{id}")
    public Object read(@PathVariable("id") Long id) {
        if (fakeOrders.containsKey(id)) {
            return fakeOrders.get(id);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public Object read(@RequestParam(value = "login", required = false) String login,
                       @RequestParam(value = "role", required = false) UserRole role) {
        if (fakeOrders.size() == 0) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        if (login != null && role != null) {
            if (role.equals(UserRole.CLIENT)) {
                return fakeOrders.values()
                        .stream()
                        .filter(value -> login.equals(value.getClientLogin()))
                        .collect(Collectors.toList());

            } else if (role.equals(UserRole.DRIVER)) {
                return fakeOrders.values()
                        .stream()
                        .filter(order -> login.equals(order.getDriverLogin()))
                        .collect(Collectors.toList());
            }
        } else {
            return fakeOrders.values();
        }
        return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
    }


    @PutMapping
    public void update(@RequestParam(value = "order") Order order) {
        if (order != null) {
            BeansUtil<Order> beansUtil = new BeansUtil<>();
            fakeOrders.forEach((login, currentOrder) -> beansUtil.copyNonNullProperties(currentOrder, order));
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam(value = "order") Order order) {
        Order currentOrder = fakeOrders.get(id);
        if (order != null) {
            BeansUtil<Order> beansUtil = new BeansUtil<>();
            beansUtil.copyNonNullProperties(currentOrder, order);
        }
    }


    @DeleteMapping
    public void delete() {
        fakeOrders.clear();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (fakeOrders.containsKey(id)) {
            fakeOrders.remove(id);
        }
    }
}
