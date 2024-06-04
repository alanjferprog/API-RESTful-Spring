package com.example.platzimarket.web.controller;

import com.example.platzimarket.domain.Product;
import com.example.platzimarket.domain.Purchase;
import com.example.platzimarket.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;

@RestController
@RequestMapping("/purchases")
public class PurchaseController
{
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    private ResponseEntity<List<Purchase>> getAll()
    {return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);}

    @GetMapping("/cliente/{id}")
    private ResponseEntity<List<Purchase>> getByClient(@PathVariable("id") String clientId)
    {return purchaseService.getByClient(clientId).filter(Predicate.not(List::isEmpty))
            .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    private ResponseEntity<Purchase> save(@RequestBody Purchase purchase)
    {return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);}


}
