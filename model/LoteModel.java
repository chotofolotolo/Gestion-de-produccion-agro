package com.myproyect.miproyect.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoteModel {
    private int idLote;// pk
    private String nombreLote;
    private BigDecimal longitudLote;
    private BigDecimal latitudLote;
    private String tipoDeCultivo;
}
