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
import org.springframework.web.bind.annotation.RestController;

import com.myproyect.miproyect.model.InsumoModel;
import com.myproyect.miproyect.service.InsumoService;

@RestController
@RequestMapping("/insumos")
public class InsumoController {
    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @GetMapping("/listarInsumos")
    public ResponseEntity<List<InsumoModel>> listarInsumos() {
        return ResponseEntity.status(HttpStatus.OK).body(insumoService.listarInsumos());
    }

    @GetMapping("/obtenerInsumo/{id}")
    public ResponseEntity<InsumoModel> obtenerInsumo(@PathVariable int id) {
        InsumoModel insumo = insumoService.obtenerInsumo(id);
        if (insumo != null) {
            return ResponseEntity.status(HttpStatus.OK).body(insumo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/agregarInsumo")
    public ResponseEntity<InsumoModel> agregarInsumo(@RequestBody InsumoModel insumo) {
        InsumoModel nuevoInsumo = insumoService.agregarInsumo(insumo);

        if (nuevoInsumo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInsumo);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping("/eliminarInsumo/{id}")
    public ResponseEntity<String> eliminarInsumo(@PathVariable int id) {
        boolean eliminado = insumoService.eliminarInsumo(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizarInsumo/{id}")
    public ResponseEntity<?> actualizarInsumo(@PathVariable int id, @RequestBody InsumoModel insumo) {
        insumo.setIdInsumo(id);
        InsumoModel actualizado = insumoService.actualizarInsumo(insumo);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar el insumo con ID: " + id);
        }
    }
}
