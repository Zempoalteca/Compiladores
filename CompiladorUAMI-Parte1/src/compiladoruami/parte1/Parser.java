/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladoruami.parte1;

import java.io.IOException;

/**
 *
 * @author Zempoalteca
 */
public class Parser {

    String[] preanalisis = new String[2];

    void Inicio(Alex A1, Globales G1, Tabla_de_Simbolos T1) throws IOException {
        int pos = 0;
        //do {
            pos = A1.ALexico(G1, T1);
        //} while (pos < 0);
        preanalisis[0] = T1.Obtener_Lexema(pos);
        preanalisis[1] = T1.Obtener_Token(pos);
        Encabezado(A1, G1, T1);
        Secuencia(A1, G1, T1);
        Parea(A1, G1, T1, G1.HECHO);
    }

    private void Encabezado(Alex A1, Globales G1, Tabla_de_Simbolos T1) throws IOException {
        Parea(A1, G1, T1, G1.PROGRAMA);
        Parea(A1, G1, T1, G1.ID);
        Parea(A1, G1, T1, ";");
    }

    private void Secuencia(Alex A1, Globales G1, Tabla_de_Simbolos T1) throws IOException {
        Parea(A1, G1, T1, G1.COMIENZA);
        while (!preanalisis[1].equals(G1.HECHO) && !preanalisis[0].equals(G1.TERMINA)) {
            Asignacion(A1, G1, T1);
        }
        Parea(A1, G1, T1, G1.TERMINA);
    }

    private void Asignacion(Alex A1, Globales G1, Tabla_de_Simbolos T1) throws IOException {
        Parea(A1, G1, T1, G1.ID);
        Parea(A1, G1, T1, G1.ASG);
        Parea(A1, G1, T1, G1.NUM_ENT);
        Parea(A1, G1, T1, ";");
    }

    private void Parea(Alex A1, Globales G1, Tabla_de_Simbolos T1, String se_espera) throws IOException {
        if (preanalisis[0].equals(se_espera) || preanalisis[1].equals(se_espera)) { //preanalisis[0].equals(se_espera)
            int pos = A1.ALexico(G1, T1);
            if (pos >= 0) {
                preanalisis[0] = T1.Obtener_Lexema(pos);
                preanalisis[1] = T1.Obtener_Token(pos);
            }
        } else {
            UAMI.errores++;
            UAMI.wr2.append("Error " + UAMI.errores + " en la linea :" + UAMI.linea + ", se esperaba: \"" + se_espera + "\", tipo de error: ERROR SINTACTICO\n");
        }
    }

}
