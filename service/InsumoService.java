package com.myproyect.miproyect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproyect.miproyect.model.InsumoModel;
import com.myproyect.miproyect.repository.InsumoRepository;

@Service
public class InsumoService {
    private final InsumoRepository insumoRepository;

    public InsumoService(InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public List<InsumoModel> listarInsumos() {
        List<InsumoModel> insumos = insumoRepository.listarInsumos();
        if (insumos != null && !insumos.isEmpty()) {
            return insumos;
        } else {
            System.out.println("*ERROR->Empty collection");
            return List.of();
        }
    }

    public InsumoModel obtenerInsumo(int id) {
        if (id > 0) {

            return insumoRepository.obtenerInsumo(id);
        } else {
            return null;
        }
    }

    public InsumoModel agregarInsumo(InsumoModel insumo) {
        if (insumo != null) {
            return insumoRepository.agregarInsumo(insumo);
        } else {
            return null;
        }
    }

    public boolean eliminarInsumo(int id) {
        if (id > 0) {
            return insumoRepository.eliminarInsumo(id);
        } else {
            return false;
        }
    }

    public InsumoModel actualizarInsumo(InsumoModel insumo) {
        if (insumo != null) {
            return insumoRepository.actualizarInsumo(insumo);
        } else {
            return null;
        }
    }
}
