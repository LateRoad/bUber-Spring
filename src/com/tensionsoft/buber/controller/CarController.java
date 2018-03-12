package com.tensionsoft.buber.controller;

import com.tensionsoft.buber.Constants;
import com.tensionsoft.buber.entity.Car;
import com.tensionsoft.buber.entity.Card;
import com.tensionsoft.buber.utils.BeansUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cars")
public class CarController {
    private HashMap<Long, Car> fakeCars = new HashMap<>();

    public CarController() {
        init();
    }

    private void init() {
        fakeCars.put(Constants.fakeCar0.getId(), Constants.fakeCar0);
        fakeCars.put(Constants.fakeCar1.getId(), Constants.fakeCar1);
        fakeCars.put(Constants.fakeCar2.getId(), Constants.fakeCar2);
    }

    @GetMapping("/{id}")
    public Object read(@PathVariable("id") Long id) {
        if (fakeCars.containsKey(id)) {
            return fakeCars.get(id);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public Object read(@RequestParam(value = "login", required = false) String login) {
        if (fakeCars.size() == 0) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        if (login != null) {
            return fakeCars.values()
                    .stream()
                    .filter(car -> login.equals(car.getLogin()))
                    .collect(Collectors.toList());
        } else {
            return fakeCars.values();
        }
    }


    @PostMapping
    public void create(@RequestParam(value = "cars") Car car) {
        if (car != null && car.getId() != null) {
            fakeCars.put(car.getId(), car);
        }
    }


    @PutMapping
    public void update(@RequestParam(value = "car") Car car) {
        if (car != null) {
            BeansUtil<Car> beansUtil = new BeansUtil<>();
            fakeCars.forEach((id, currentCar) -> beansUtil.copyNonNullProperties(currentCar, car));
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestParam(value = "car") Car car) {
        Car currentCar = fakeCars.get(id);
        if (car != null) {
            BeansUtil<Car> beansUtil = new BeansUtil<>();
            beansUtil.copyNonNullProperties(currentCar, car);
        }
    }


    @DeleteMapping
    public void delete() {
        fakeCars.clear();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (fakeCars.containsKey(id)) {
            fakeCars.remove(id);
        }
    }
}
