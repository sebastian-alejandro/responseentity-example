package com.sebastianalejandro.responseentityexample.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ResponseEntityExample {

    @GetMapping("/hola")
    ResponseEntity<String> holaMundo() {
        return new ResponseEntity<>("Hola Mundo desde una respuesta HTTP!", HttpStatus.OK);
    }

    @GetMapping("/verificar/{correo}")
    ResponseEntity<String> verificarCorreo(@PathVariable String correo) {

        if (!EmailValidator.getInstance().isValid(correo)) {
            return new ResponseEntity<>("Formato debe ser: ejemplo@correo.dominio", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Su correo es: " + correo, HttpStatus.OK);
    }

    @GetMapping("/cabecera/{cliente}")
    ResponseEntity<String> cabeceraPersonalizada(@PathVariable String cliente) {

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add("Estado Cliente", "Cliente " + cliente + ": habilitado");

        return new ResponseEntity<>("Bienvenido " + cliente, cabecera, HttpStatus.OK);
    }

    @GetMapping("/holaMundo")
    ResponseEntity<String> holaMundo2() {
        return ResponseEntity.ok("Hola Mundo !");
    }

    @GetMapping("/verificarCorreo/{correo}")
    ResponseEntity<String> verificarCorreo2(@PathVariable String correo) {

        if (!EmailValidator.getInstance().isValid(correo)) {
            return ResponseEntity.badRequest().body("Error! Formato correcto: ejemplo@correo.dominio");
        }

        return ResponseEntity.status(200).body("Correo: " + correo);
    }

    @GetMapping("/cabeceraCustomizada/{cliente}")
    ResponseEntity<String> cabeceraPersonalizada2(@PathVariable String cliente) {

        return ResponseEntity.ok()
                .header("Estado Cliente", "Cliente " + cliente + ": habilitado")
                .body("Bienvenido cliente: " + cliente);
    }
}
