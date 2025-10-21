package com.myproyect.miproyect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.myproyect.miproyect.model.LoteModel;
import com.myproyect.miproyect.service.LoteService;

@RestController
@RequestMapping("/lotes")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping("/listarLotes")
    public ResponseEntity<List<LoteModel>> listarLotes() {
        List<LoteModel> lotes = loteService.listarLotes();
        if (lotes != null && !lotes.isEmpty()) {
            return ResponseEntity.ok(lotes);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(List.of());
        }
    }

    @GetMapping("/obtenerLote/{id}")
    public ResponseEntity<LoteModel> obtenerLote(@PathVariable int id) {
        LoteModel lote = loteService.obtenerLote(id);
        if (lote != null) {
            return ResponseEntity.ok(lote);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/agregarLote")
    public ResponseEntity<LoteModel> agregarLote(@RequestBody LoteModel lote) {
        LoteModel nuevoLote = loteService.agregarLote(lote);
        if (nuevoLote != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLote);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/eliminarLote/{id}")
    public ResponseEntity<String> eliminarLote(@PathVariable int id) {
        boolean eliminado = loteService.eliminarLote(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el lote con ID: " + id);
        }
    }

    @PutMapping("/actualizarLote/{id}")
    public ResponseEntity<?> actualizarLote(@PathVariable int id, @RequestBody LoteModel lote) {
        lote.setIdLote(id);
        LoteModel actualizado = loteService.actualizarLote(lote);

        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se pudo actualizar el lote con ID: " + id);
        }
    }
}
