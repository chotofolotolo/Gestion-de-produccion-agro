package com.myproyect.miproyect.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalModel {
    private int idPersonal;// PK
    private String nombrePersonal;
    private String apellidoPersonal;
    private String dniPersonal;
    private String cargoPersonal;
    private String telefonoPersonal;
    private String direccionPersonal;
    private LocalDate fechaIngreso;

    public PersonalModel(int idPersonal, String nombrePersonal, String apellidoPersonal, String dniPersonal,
            String cargoPersonal,
            String telefonoPersonal, String direccionPersonal, LocalDate fechaIngreso) {
        this.idPersonal = idPersonal;
        this.nombrePersonal = nombrePersonal;
        this.apellidoPersonal = apellidoPersonal;
        this.dniPersonal = dniPersonal;
        this.cargoPersonal = cargoPersonal;
        this.telefonoPersonal = telefonoPersonal;
        this.direccionPersonal = direccionPersonal;
        this.fechaIngreso = fechaIngreso;
    }
}