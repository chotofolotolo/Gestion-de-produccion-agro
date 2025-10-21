package com.myproyect.miproyect.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myproyect.miproyect.model.PersonalModel;
import com.myproyect.miproyect.repository.PersonalRepository;

@Service
public class PersonalService {
    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<PersonalModel> listarPersonal() {
        List<PersonalModel> lista = personalRepository.listarPersonal();
        if (lista != null && !lista.isEmpty()) {
            return lista;
        } else {
            System.out.println("*ERROR->Empty collection");
            return null;
        }
    }

    public PersonalModel obtenerPersonal(int id) {
        if (id > 0) {
            return personalRepository.obtenerPersonal(id);
        } else {
            return null;
        }
    }

    public PersonalModel agregarPersonal(PersonalModel p) {
        if (p != null) {
            return personalRepository.agregarPersonal(p);
        } else {
            return null;
        }
    }

    public boolean eliminarPersonal(int id) {
        if (id > 0) {
            return personalRepository.eliminarPersonal(id);
        } else {
            return false;
        }
    }

    public PersonalModel actualizarPersonal(PersonalModel p) {
        if (p != null) {
            return personalRepository.actualizarPersonal(p);
        } else {
            return null;
        }
    }
}
