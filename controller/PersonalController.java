package com.myproyect.miproyect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproyect.miproyect.model.PersonalModel;
import com.myproyect.miproyect.service.PersonalService;

@RestController
@RequestMapping("/personal")
public class PersonalController {
    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/listarPersonal")
    public ResponseEntity<List<PersonalModel>> listarPersonal() {
        List<PersonalModel> lista = personalService.listarPersonal();
        if (lista != null) {
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/obtenerPersonal/{id}")
    public ResponseEntity<PersonalModel> obtenerPersonal(@PathVariable int id) {
        PersonalModel p = personalService.obtenerPersonal(id);
        if (p != null) {
            return ResponseEntity.status(HttpStatus.OK).body(p);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/agregarPersonal")
    public ResponseEntity<PersonalModel> agregarPersonal(@RequestBody PersonalModel p) {
        PersonalModel nuevo = personalService.agregarPersonal(p);
        if (nuevo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/eliminarPersonal/{id}")
    public ResponseEntity<String> eliminarPersonal(@PathVariable int id) {
        boolean eliminado = personalService.eliminarPersonal(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarPersonal/{id}")
    public ResponseEntity<?> actualizarPersonal(@PathVariable int id, @RequestBody PersonalModel p) {
        p.setIdPersonal(id);
        PersonalModel actualizado = personalService.actualizarPersonal(p);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar el personal con ID: " + id);
        }
    }
}
