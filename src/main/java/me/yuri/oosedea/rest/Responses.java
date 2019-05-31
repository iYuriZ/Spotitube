package me.yuri.oosedea.rest;

import me.yuri.oosedea.exceptions.UnauthorizedUserException;
import me.yuri.oosedea.rest.dto.DTO;
import org.springframework.http.ResponseEntity;

abstract class Responses {

    ResponseEntity respondOk(DTO response) {
        return ResponseEntity.status(200).body(response);
    }

    ResponseEntity respondCreated(DTO response) {
        return ResponseEntity.status(201).body(response);
    }

    ResponseEntity respondUnauthorized(UnauthorizedUserException e) {
        return ResponseEntity.status(401).body(e);
    }

}
