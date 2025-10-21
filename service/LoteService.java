package com.myproyect.miproyect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproyect.miproyect.model.LoteModel;
import com.myproyect.miproyect.repository.LoteRepository;

@Service
public class LoteService {

    private final LoteRepository loteRepository;

    public LoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public List<LoteModel> listarLotes() {
        List<LoteModel> lotes = loteRepository.listarLotes();
        if (lotes != null && !lotes.isEmpty()) {
            return lotes;
        } else {
            System.out.println("*ERROR->Empty collection");
            return List.of();
        }
    }

    public LoteModel obtenerLote(int id) {
        if (id > 0) {
            return loteRepository.obtenerLote(id);
        } else {
            return null;
        }
    }

    public LoteModel agregarLote(LoteModel lote) {
        if (lote != null) {
            return loteRepository.agregarLote(lote);
        } else {
            return null;
        }
    }

    public boolean eliminarLote(int id) {
        if (id > 0) {
            return loteRepository.eliminarLote(id);
        } else {
            return false;
        }
    }

    public LoteModel actualizarLote(LoteModel lote) {
        if (lote != null) {
            return loteRepository.actualizarLote(lote);
        } else {
            return null;
        }
    }
}
