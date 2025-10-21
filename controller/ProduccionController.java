package com.myproyect.miproyect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproyect.miproyect.model.ProduccionModel;
import com.myproyect.miproyect.service.ProduccionService;

@RestController
@RequestMapping("/produccion")
public class ProduccionController {
    private final ProduccionService produccionService;

    public ProduccionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    @GetMapping("/listarProducciones")
    public ResponseEntity<List<ProduccionModel>> listarProducciones() {
        return ResponseEntity.status(HttpStatus.OK).body(produccionService.listarProducciones());
    }

    @GetMapping("/obtenerProduccion/{id}")
    public ResponseEntity<ProduccionModel> obtenerProduccion(@PathVariable int id) {
        ProduccionModel produccion = produccionService.obtenerProduccion(id);
        if (produccion != null) {
            return ResponseEntity.status(HttpStatus.OK).body(produccion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/agregarProduccion")
    public ResponseEntity<ProduccionModel> agregarProduccion(@RequestBody ProduccionModel produccion) {
        ProduccionModel nueva = produccionService.agregarProduccion(produccion);
        if (nueva != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/eliminarProduccion/{id}")
    public ResponseEntity<String> eliminarProduccion(@PathVariable int id) {
        boolean eliminado = produccionService.eliminarProduccion(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarProduccion/{id}")
    public ResponseEntity<?> actualizarProduccion(@PathVariable int id, @RequestBody ProduccionModel produccion) {
        produccion.setIdProduccion(id);
        ProduccionModel actualizado = produccionService.actualizarProduccion(produccion);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar la producción con ID: " + id);
        }
    }
}
