package com.tensionsoft.buber.controller;

import com.tensionsoft.buber.Constants;
import com.tensionsoft.buber.entity.Card;
import com.tensionsoft.buber.utils.BeansUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cards")
public class CardController {
    private HashMap<Long, Card> fakeCards = new HashMap<>();

    public CardController() {
        init();
    }

    private void init() {
        fakeCards.put(Constants.fakeCard0.getId(), Constants.fakeCard0);
        fakeCards.put(Constants.fakeCard1.getId(), Constants.fakeCard1);
        fakeCards.put(Constants.fakeCard2.getId(), Constants.fakeCard2);
    }


    @GetMapping("/{id}")
    public Object read(@PathVariable("id") Long id) {
        if (fakeCards.containsKey(id)) {
            return fakeCards.get(id);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public Object read(@RequestParam(value = "driverLogin", required = false) String login) {
        if (fakeCards.size() == 0) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        if (login != null) {
            return fakeCards.values()
                    .stream()
                    .filter(card -> login.equals(card.getLogin()))
                    .collect(Collectors.toList());

        } else {
            return fakeCards.values();
        }
    }


    @PostMapping
    public void create(@RequestParam(value = "card") Card card) {
        if (card != null && card.getId() != null) {
            fakeCards.put(card.getId(), card);
        }
    }


    @PutMapping
    public void update(@RequestParam(value = "card") Card card) {
        if (card != null) {
            BeansUtil<Card> beansUtil = new BeansUtil<>();
            fakeCards.forEach((id, currentCard) -> beansUtil.copyNonNullProperties(currentCard, card));
        }
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,
                       @RequestBody Card card) {
        if (card != null) {
            Card currentCard = fakeCards.get(id);
            BeansUtil<Card> beansUtil = new BeansUtil<>();
            beansUtil.copyNonNullProperties(currentCard, card);
        }
    }


    @DeleteMapping
    public void delete() {
        fakeCards.clear();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        if (fakeCards.containsKey(id)) {
            fakeCards.remove(id);
        }
    }
}

