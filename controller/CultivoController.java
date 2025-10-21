package com.myproyect.miproyect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myproyect.miproyect.service.CultivoService;
import com.myproyect.miproyect.model.CultivoModel;

@RestController
@RequestMapping("/cultivos")
public class CultivoController {
    private final CultivoService cultivoService;

    public CultivoController(CultivoService cultivoService) {
        this.cultivoService = cultivoService;
    }

    @GetMapping("/listarCultivos")
    public ResponseEntity<List<CultivoModel>> listarCultivos() {
        return ResponseEntity.status(HttpStatus.OK).body(cultivoService.listarCultivos());
    }

    @GetMapping("/obtenerCultivo/{id}")
    public ResponseEntity<CultivoModel> obtenerCultivo(@PathVariable int id) {
        CultivoModel cultivo = cultivoService.obtenerCultivo(id);
        if (cultivo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cultivo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/agregarCultivo")
    public ResponseEntity<CultivoModel> agregarCultivo(@RequestBody CultivoModel cultivo) {
        CultivoModel nuevoCultivo = cultivoService.agregarCultivo(cultivo);

        if (nuevoCultivo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCultivo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping("/eliminarCultivo/{id}")
    public ResponseEntity<String> eliminarCultivo(@PathVariable int id) {
        boolean eliminado = cultivoService.eliminarCultivo(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarCultivo/{id}")
    public ResponseEntity<?> actualizarCultivo(@PathVariable int id, @RequestBody CultivoModel cultivo) {
        cultivo.setIdCultivo(id);
        CultivoModel actualizado = cultivoService.actualizarCultivo(cultivo);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar el cultivo con ID: " + id);
        }
    }
}
