package com.myproyect.miproyect.model;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalModel {
    private int idPersonal;//PK
    private String nombrePersonal;
    private String apellidoPersonal;
    private String dniPersonal;
    private String cargoPersonal;
    private String telefonoPersonal;
    private String direccionPersonal;
    private LocalDate fechaIngreso;    
}