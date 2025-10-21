package com.myproyect.miproyect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproyect.miproyect.model.ProduccionModel;
import com.myproyect.miproyect.repository.ProduccionRepository;

@Service
public class ProduccionService {
    private final ProduccionRepository produccionRepository;

    public ProduccionService(ProduccionRepository produccionRepository) {
        this.produccionRepository = produccionRepository;
    }

    public List<ProduccionModel> listarProducciones() {
        List<ProduccionModel> producciones = produccionRepository.listarProducciones();
        if (producciones != null && !producciones.isEmpty()) {
            return producciones;
        } else {
            System.out.println("*ERROR->Empty collection");
            return null;
        }
    }

    public ProduccionModel obtenerProduccion(int id) {
        if (id > 0) {
            return produccionRepository.obtenerProduccion(id);
        } else {
            return null;
        }
    }

    public ProduccionModel agregarProduccion(ProduccionModel produccion) {
        if (produccion != null) {
            return produccionRepository.agregarProduccion(produccion);
        } else {
            return null;
        }
    }

    public boolean eliminarProduccion(int id) {
        if (id > 0) {
            return produccionRepository.eliminarProduccion(id);
        } else {
            return false;
        }
    }

    public ProduccionModel actualizarProduccion(ProduccionModel produccion) {
        if (produccion != null) {
            return produccionRepository.actualizarProduccion(produccion);
        } else {
            return null;
        }
    }
}
