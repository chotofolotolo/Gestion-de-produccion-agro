//REPOSITORY->INTERACTUA CON LA BASE DE DATOS
package com.myproyect.miproyect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// la clase
public class User {
    private String name;

    private String email;

    private String password;
}
