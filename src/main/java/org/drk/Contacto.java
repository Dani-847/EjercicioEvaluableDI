package org.drk;

import lombok.Data;

/**
 * Clase que representa un contacto con correo, país y plataforma.
 */

@Data
public class Contacto {
    private String correo;
    private String pais;
    private String plataforma;
}
