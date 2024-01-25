package br.janioofi.mscartoes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cartoes")
public class CartoesCotroller {

    @GetMapping("/status")
    public String status(){
        return "ok";
    }
}
