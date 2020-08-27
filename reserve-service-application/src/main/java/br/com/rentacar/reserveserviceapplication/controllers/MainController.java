package br.com.rentacar.reserveserviceapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    private String name;

    @GetMapping("/")
    @ResponseBody()
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("It's working"+ name);
    }
}
