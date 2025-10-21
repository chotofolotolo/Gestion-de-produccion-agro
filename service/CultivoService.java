package com.myproyect.miproyect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproyect.miproyect.repository.CultivoRepository;
import com.myproyect.miproyect.model.CultivoModel;;

@Service
public class CultivoService {
    private final CultivoRepository cultivoRepository;

    public CultivoService(CultivoRepository cultivoRepository) {
        this.cultivoRepository = cultivoRepository;
    }

    public List<CultivoModel> listarCultivos() {
        List<CultivoModel> cultivos = cultivoRepository.listarCultivos();
        if (cultivos != null && !cultivos.isEmpty()) {
            return cultivos;
        } else {
            System.out.println("*ERROR->Empty collection");
            return null;
        }
    }

    public CultivoModel obtenerCultivo(int id) {
        if (id > 0) {

            return cultivoRepository.obtenerCultivo(id);
        } else {
            return null;
        }
    }

    public CultivoModel agregarCultivo(CultivoModel cultivo) {
        if (cultivo != null) {
            return cultivoRepository.agregarCultivo(cultivo);
        } else {
            return null;
        }
    }

    public boolean eliminarCultivo(int id) {
        if (id > 0) {
            return cultivoRepository.eliminarCultivo(id);
        } else {
            return false;
        }
    }

    public CultivoModel actualizarCultivo(CultivoModel cultivo) {
        if (cultivo != null) {
            return cultivoRepository.actualizarCultivo(cultivo);
        } else {
            return null;
        }
    }

}
